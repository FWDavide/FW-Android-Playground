/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin

import android.app.Application
import android.util.Log
import com.futureworkshops.marvelheroeskotlin.domain.AppComponent
import com.futureworkshops.marvelheroeskotlin.domain.ApplicationModule
import com.futureworkshops.marvelheroeskotlin.domain.DaggerAppComponent
import com.futureworkshops.marvelheroeskotlin.domain.NetModule
import timber.log.Timber

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class MarvelHeroesKotlinApp : Application() {
    
    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }
    
    override fun onCreate() {
        super.onCreate()
        initTimber()
        buildApplicationComponent()
    }
    
    private fun buildApplicationComponent() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this))
                .netModule(NetModule())
                .build()
    }
    
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseLogTree())
        }
    }
    
    
}

private const val MAX_LOG_LENGTH = 4000

class ReleaseLogTree : Timber.Tree() {
    
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority)) {
            if (message.length < MAX_LOG_LENGTH) {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message)
                } else {
                    Log.println(priority, tag, message)
                }
                return
            }
            
            val length = message.length
            var i = 0
            while (i < length) {
                var newLine = message.indexOf('\n', i)
                newLine = if (newLine != -1) newLine else length
                do {
                    val end = Math.min(newLine, i + MAX_LOG_LENGTH)
                    val part = message.substring(i, end)
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, part)
                    } else {
                        Log.println(priority, tag, part)
                    }
                    i = end
                } while (i < newLine)
                i
            }
        }
    }
    
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.WARN
    }
}