package com.develop.nowasteinmyfridge.di

import com.develop.nowasteinmyfridge.api.NoWasteInMyFridgeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNoWasteInMyFridgeService():NoWasteInMyFridgeService{
        return NoWasteInMyFridgeService.create()
    }
}