package com.example.shacklehotelbuddy.di

import android.content.Context
import androidx.room.Room
import com.example.shacklehotelbuddy.data.database.SearchHistoryDao
import com.example.shacklehotelbuddy.data.database.ShackleDatabase
import com.example.shacklehotelbuddy.data.database.ShackleDatabase.Companion.DATABASE_NAME
import com.example.shacklehotelbuddy.data.repository.PropertiesRemoteRepository
import com.example.shacklehotelbuddy.data.repository.SearchHistoryLocalRepository
import com.example.shacklehotelbuddy.domain.repository.PropertiesRepository
import com.example.shacklehotelbuddy.domain.repository.SearchHistoryRepository
import com.example.shacklehotelbuddy.network.NetworkErrorHandler
import com.example.shacklehotelbuddy.network.PropertiesApi
import com.example.shacklehotelbuddy.network.ResourceProvider
import com.example.shacklehotelbuddy.network.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    @Singleton
    fun providePropertiesRepository(
        api: PropertiesApi,
        errorHandler: NetworkErrorHandler
    ): PropertiesRepository {
        return PropertiesRemoteRepository(api, errorHandler)
    }

    @Provides
    @Singleton
    fun provideSearchHistoryRepository(
        dao: SearchHistoryDao
    ): SearchHistoryRepository {
        return SearchHistoryLocalRepository(dao)
    }


    @Provides
    @Singleton
    fun provideErrorHandler(resProvider: ResourceProvider): NetworkErrorHandler {
        return NetworkErrorHandler(resProvider)
    }

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext appContext: Context): ResourceProvider {
        return ResourceProviderImpl(appContext)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ShackleDatabase =
        Room.databaseBuilder(
            context,
            ShackleDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideSearchHistoryDao(db: ShackleDatabase): SearchHistoryDao = db.searchHistoryDao()

}