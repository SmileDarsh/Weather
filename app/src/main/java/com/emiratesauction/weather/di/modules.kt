package com.emiratesauction.weather.di

import com.emiratesauction.weather.network.*
import com.emiratesauction.weather.city.data.repository.WeatherRepositoryImpl
import com.emiratesauction.weather.city.data.local.WeatherLocalDataStore
import com.emiratesauction.weather.city.data.rest.WeatherRestDataStore
import com.emiratesauction.weather.city.domain.interactor.*
import com.emiratesauction.weather.city.domain.repository.WeatherRepository
import com.emiratesauction.weather.city.presentation.viewModel.*
import com.emiratesauction.weather.room.*
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val weatherModule = module {
    viewModel { CityViewModel(get(), get(), get()) }
    viewModel { WeatherViewModel(get()) }
    single { RemoveCityInteractor(get()) }
    single { SaveCityInteractor(get()) }
    single { GetCityInteractor(get()) }
    single { GetWeathersInteractor(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), androidContext()) }
    single { WeatherLocalDataStore(get()) }
    single { WeatherRestDataStore(get()) }
}

private val databaseModule = module {
    single { provideDatabase(androidApplication()) }
}

private val networkModule = module {
    single { iApiClient(get()) }
    single { provideRetrofit() }
}

val modules = listOf(weatherModule, databaseModule, networkModule)