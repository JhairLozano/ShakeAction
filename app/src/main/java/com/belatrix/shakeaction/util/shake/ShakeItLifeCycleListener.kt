package com.belatrix.shakeaction.util.shake

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.hardware.Sensor
import android.hardware.SensorManager

class ShakeItLifeCycleListener(
    private val sensorManager: SensorManager,
    private val defaultLiceCycleCallBack: DefaultLiceCycleCallBack = DefaultLiceCycleCallBack()
) :
    ActivityLifecycleCallbacks by defaultLiceCycleCallBack {

    override fun onActivityResumed(activity: Activity?) {
        sensorManager.registerListener(
            ShakeAction,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        if (activity is OnShakeActionListener) {
            ShakeAction.registerForShakeEvent(activity)
        }
    }

    override fun onActivityPaused(activity: Activity?) {
        sensorManager.unregisterListener(
            ShakeAction,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        )
        if (activity is OnShakeActionListener) {
            ShakeAction.unRegisterForShakeEvent(activity)
        }
    }
}