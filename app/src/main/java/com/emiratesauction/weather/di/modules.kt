package com.emiratesauction.weather.di

import com.emiratesauction.weather.network.*
import org.koin.dsl.module

private val networkModule = module {
    single { iApiClient(get()) }
    single { provideRetrofit() }
}

val modules = listOf(networkModule)