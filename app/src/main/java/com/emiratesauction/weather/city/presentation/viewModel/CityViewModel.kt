package com.emiratesauction.weather.city.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emiratesauction.weather.city.domain.interactor.GetCityInteractor
import com.emiratesauction.weather.city.domain.interactor.RemoveCityInteractor
import com.emiratesauction.weather.city.domain.interactor.SaveCityInteractor
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.helper.io
import com.emiratesauction.weather.helper.ui
import com.emiratesauction.weather.city.presentation.viewModel.state.WeatherVS
import com.emiratesauction.weather.network.NO_INTERNET_FOUND
import com.emiratesauction.weather.network.notFound
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
class CityViewModel(
    private val mGetCityInteractor: GetCityInteractor,
    private val mSaveCityInteractor: SaveCityInteractor,
    private val mRemoveCityInteractor: RemoveCityInteractor
) : ViewModel(), KoinComponent {

    val viewState: LiveData<WeatherVS> get() = mViewState
    private val mViewState = MutableLiveData<WeatherVS>()

    fun city(text: String) {
        viewModelScope.launch {
            try {
                io {
                    mGetCityInteractor.execute(text).collect {
                        ui {
                            mViewState.value = if (it.id == null) WeatherVS.Empty
                            else WeatherVS.AddCity(it)
                        }
                    }
                }
            } catch (e: Exception) {
                val response = e.notFound()
                when {
                    e.message != null && e.message == NO_INTERNET_FOUND ->
                        mViewState.value = WeatherVS.InternetError
                    response != null -> mViewState.value = WeatherVS.Error(response.message ?: "")
                    else -> e.printStackTrace()
                }
            }
        }
    }

    fun saveCity(city: City) {
        viewModelScope.launch {
            try {
                io {
                    mSaveCityInteractor.execute(city).collect {
                        ui {
                            mViewState.value = WeatherVS.SaveCity(city)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeCity(city: City) {
        viewModelScope.launch {
            try {
                io {
                    mRemoveCityInteractor.execute(city).collect {
                        ui {
                            mViewState.value = WeatherVS.RemoveCity(city)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}