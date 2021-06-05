package com.emiratesauction.weather.network

import com.emiratesauction.weather.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CONNECTION_TIMEOUT: Long = 180L
const val READ_TIMEOUT: Long = 180L
const val WRITE_TIMEOUT: Long = 180L

fun provideRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) interceptor.level = HttpLoggingInterceptor.Level.BODY

    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor {
        val original = it.request()
        val request = original.newBuilder()
            .addHeader("x-rapidapi-key", "2491b40eaemshe6a68dfb306439fp126153jsn96e52cc3d6ff")
            .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
            .method(original.method, original.body)
            .build()
        it.proceed(request)
    }

    val client = httpClient
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    val retrofitBuilder = Retrofit.Builder().apply {
        baseUrl(BuildConfig.URL_SERVER)
        client(client)
        addConverterFactory(GsonConverterFactory.create())
    }

    return retrofitBuilder.build()
}

fun iApiClient(retrofit: Retrofit): IApiClient = retrofit.create(IApiClient::class.java)