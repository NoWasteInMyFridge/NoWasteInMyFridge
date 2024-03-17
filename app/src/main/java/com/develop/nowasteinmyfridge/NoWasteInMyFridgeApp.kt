package com.develop.nowasteinmyfridge

import android.app.Application
import com.develop.nowasteinmyfridge.workers.ExpirationCheckScheduler
import dagger.hilt.android.HiltAndroidApp
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