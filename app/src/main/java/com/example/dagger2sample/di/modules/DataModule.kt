package com.example.dagger2sample.di.modules

import dagger.Module
import dagger.Provides
import com.example.dagger2sample.api.ApiService
import com.example.dagger2sample.data.repository.Repository
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @Provides
    fun providesRepository(apiService: ApiService): Repository =
        Repository(apiService)
}