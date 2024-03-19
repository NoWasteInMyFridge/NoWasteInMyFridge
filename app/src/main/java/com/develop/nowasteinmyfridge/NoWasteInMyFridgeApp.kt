package com.develop.nowasteinmyfridge

import android.app.Application
import com.develop.nowasteinmyfridge.data.repository.AuthRepositoryImpl
import com.develop.nowasteinmyfridge.workers.ExpirationCheckScheduler
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltAndroidApp
class NoWasteInMyFridgeApp : Application() {
    @Inject
    lateinit var expirationCheckScheduler: ExpirationCheckScheduler

    override fun onCreate() {
        super.onCreate()
        expirationCheckScheduler.scheduleDailyCheck()
    }

}