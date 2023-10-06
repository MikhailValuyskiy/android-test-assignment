package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.domain.repository.PropertiesRepository
import com.example.shacklehotelbuddy.domain.usecase.GetPropertiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun providePropertiesUseCase(
        repository: PropertiesRepository
    ): GetPropertiesUseCase {
        return GetPropertiesUseCase(repository)
    }

}