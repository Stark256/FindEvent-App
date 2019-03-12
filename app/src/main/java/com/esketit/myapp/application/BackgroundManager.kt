package com.esketit.myapp.application

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import timber.log.Timber

import java.util.ArrayList

class BackgroundManager private constructor(application: Application) : Application.ActivityLifecycleCallbacks {

    var isInBackground = true
        private set
    private val listeners = ArrayList<Listener>()
    private val mBackgroundDelayHandler = Handler()
    private var mBackgroundTransition: Runnable? = null

    interface Listener {
        fun onBecameForeground()
        fun onBecameBackground()
    }

    init {
        application.registerActivityLifecycleCallbacks(this)
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        if (mBackgroundTransition != null) {
            mBackgroundDelayHandler.removeCallbacks(mBackgroundTransition)
            mBackgroundTransition = null
        }

        if (isInBackground) {
            isInBackground = false
            notifyOnBecameForeground()
            Timber.i("%sApplication went to foreground", LOG_TAG)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (!isInBackground && mBackgroundTransition == null) {
            mBackgroundTransition = Runnable {
                isInBackground = true
                mBackgroundTransition = null
                notifyOnBecameBackground()
               // Timber.i("%sApplication went to background", LOG_TAG)
            }
            mBackgroundDelayHandler.postDelayed(mBackgroundTransition, BACKGROUND_DELAY)
        }
    }

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {}

    override fun onActivityDestroyed(activity: Activity) {}

    private fun notifyOnBecameForeground() {
        for (listener in listeners) {
            try {
                listener.onBecameForeground()
            } catch (e: Exception) {
                Timber.e(LOG_TAG + "Listener threw exception!" + e)
            }

        }
    }

    private fun notifyOnBecameBackground() {
        for (listener in listeners) {
            try {
                listener.onBecameBackground()
            } catch (e: Exception) {
                Timber.e(LOG_TAG + "Listener threw exception!" + e)
            }

        }
    }

    companion object {

        private val LOG_TAG = "BackgroundManagerTag"

        val BACKGROUND_DELAY: Long = 500

        private var sInstance: BackgroundManager? = null

        fun getBackgroundManager(application: Application): BackgroundManager {
            if (sInstance == null) {
                sInstance = BackgroundManager(application)
            }
            return sInstance!!
        }
    }
}
