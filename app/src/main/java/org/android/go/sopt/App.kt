package org.android.go.sopt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.android.go.sopt.util.PreferenceManager

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        prefs = PreferenceManager(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var prefs: PreferenceManager
    }

}