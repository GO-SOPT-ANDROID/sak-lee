package org.android.go.sopt

import android.app.Application
import org.android.go.sopt.util.PreferenceManager

class App : Application(){

    companion object{
      lateinit var prefs : PreferenceManager
    }

    override fun onCreate() {
        prefs = PreferenceManager(applicationContext)
        super.onCreate()
    }
}