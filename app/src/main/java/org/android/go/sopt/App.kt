package org.android.go.sopt

import android.app.Application
import org.android.go.sopt.util.PreferenceManager

class App : Application(){
    override fun onCreate() {
        prefs = PreferenceManager(applicationContext)
        super.onCreate()
    }

    companion object{
        lateinit var prefs : PreferenceManager
    }

}