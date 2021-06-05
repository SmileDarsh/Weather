package com.emiratesauction.weather.city.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emiratesauction.weather.helper.io
import com.emiratesauction.weather.helper.ui
import com.emiratesauction.weather.city.domain.interactor.GetWeathersInteractor
import com.emiratesauction.weather.city.presentation.viewModel.state.WeatherVS
import com.emiratesauction.weather.network.NO_INTERNET_FOUND
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
class WeatherViewModel(private val mGetWeathersInteractor: GetWeathersInteractor) : ViewModel(),
    KoinComponent {

    val viewState: LiveData<WeatherVS> get() = mViewState
    private val mViewState = MutableLiveData<WeatherVS>()

    fun weathers(text: String) {
        viewModelScope.launch {
            try {
                io {
                    mGetWeathersInteractor.execute(text).collect {
                        ui {
                            mViewState.value = if (it.isEmpty()) WeatherVS.Empty
                            else WeatherVS.AddWeathers(it)
                        }
                    }
                }
            } catch (e: Exception) {
                if (e.message != null && e.message == NO_INTERNET_FOUND)
                    mViewState.value = WeatherVS.InternetError
                else e.printStackTrace()
            }
        }
    }
}