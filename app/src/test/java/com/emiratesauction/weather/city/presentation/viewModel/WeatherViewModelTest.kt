package com.emiratesauction.weather.city.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emiratesauction.weather.city.domain.interactor.GetWeathersInteractor
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.city.presentation.viewModel.state.WeatherVS
import com.emiratesauction.weather.network.NO_INTERNET_FOUND
import com.emiratesauction.weather.utils.assertLiveData
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.setMain
import org.junit.*
import java.lang.Exception

class WeatherViewModelTest {
    private val mCityName = "Cairo"
    private val testList: MutableList<City> = mutableListOf(
        City(id = 52212, dt = 1622937600, dt_txt = "2021-06-06 00:00:00")
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val useCaseMock = mockk<GetWeathersInteractor>()
    private lateinit var viewModel: WeatherViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Default)
        viewModel = WeatherViewModel(useCaseMock)
    }

    @Test
    fun `retrieve posts successful`() {
        coEvery { useCaseMock.execute(mCityName) } returns flow { testList }
        viewModel.weathers(mCityName)
        viewModel.viewState.assertLiveData { WeatherVS.AddCity(testList.first()) }
    }

    @Test
    fun `retrieve posts failure`() {
        coEvery { useCaseMock.execute(mCityName) } returns flow { mutableListOf<City>() }
        viewModel.weathers(mCityName)
        viewModel.viewState.assertLiveData { WeatherVS.Empty }
    }

    @Test
    fun `retrieve posts with exception`() {
        coEvery { useCaseMock.execute(mCityName) } throws Exception(NO_INTERNET_FOUND)
        viewModel.weathers(mCityName)
        viewModel.viewState.assertLiveData { WeatherVS.InternetError }
    }
}
