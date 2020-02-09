package com.belatrix.shakeaction.util.shake

import android.app.Application
import android.content.Context
import android.hardware.SensorManager

object ShakeConfig {

    fun init(application: Application) {
        val shakeItLifeCycleListener =
            ShakeItLifeCycleListener(application.getSystemService(Context.SENSOR_SERVICE) as SensorManager)
        application.registerActivityLifecycleCallbacks(shakeItLifeCycleListener)
    }
}