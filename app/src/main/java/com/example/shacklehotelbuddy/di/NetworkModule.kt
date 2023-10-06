package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.BuildConfig
import com.example.shacklehotelbuddy.network.PropertiesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        // Add Headers
        clientBuilder.addInterceptor(getHeaderInterceptor())

        // Add HttpLoggingInterceptor only for develop build variant
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): PropertiesApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PropertiesApi::class.java)
    }

    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .header("X-RapidAPI-Key", BuildConfig.TOKEN)
                    .header("X-RapidAPI-Host", "hotels4.p.rapidapi.com")
                    .header("Content-Type", "application/json")
                    .build()
            chain.proceed(request)
        }
    }
}