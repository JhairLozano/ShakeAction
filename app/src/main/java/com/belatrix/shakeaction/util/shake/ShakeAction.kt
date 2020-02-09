package com.belatrix.shakeaction.util.shake

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

private const val SHAKE_THRESHOLD_GRAVITY = 4
private const val SHAKE_IGNORED_TIME_MS = 100
private const val SHAKE_COUNT_RESET_TIME_MS = 3000

object ShakeAction : SensorEventListener {

    private val mShakeListeners = mutableListOf<OnShakeActionListener>()

    private var shakeTimestamp: Long = 0
    private var shakeCount: Int = 0

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            val gForce = sqrt((gX * gX + gY * gY + gZ * gZ).toDouble()).toFloat()

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {

                val now = now()
                if (now - shakeTimestamp < SHAKE_IGNORED_TIME_MS) {
                    return
                }

                if (now - shakeTimestamp > SHAKE_COUNT_RESET_TIME_MS) {
                    shakeCount = 0
                }

                shakeTimestamp = now
                shakeCount++

                mShakeListeners?.forEach { it.onShakeAction(shakeCount) }
            }
        }
    }

    fun registerForShakeEvent(shakeListener: OnShakeActionListener) {
        if (!mShakeListeners.contains(shakeListener)) {
            mShakeListeners.add(shakeListener)
        }
    }

    fun unRegisterForShakeEvent(shakeListener: OnShakeActionListener) {
        if (mShakeListeners.contains(shakeListener)) {
            mShakeListeners.remove(shakeListener)
        }
    }

    private fun now(): Long {
        return System.currentTimeMillis()
    }
}