package com.example.activities.core.di

import com.example.activities.core.data.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    /*@Provides
    @Singleton*/
    /*fun providesBeersRepository(apiservice: APIService): DataRepository {
        return DataRepositoryImpl(apiservice)
    }*/
}