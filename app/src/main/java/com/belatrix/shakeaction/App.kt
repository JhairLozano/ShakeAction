package com.belatrix.shakeaction

import android.app.Application
import com.belatrix.shakeaction.util.shake.ShakeConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ShakeConfig.init(this)
    }

}