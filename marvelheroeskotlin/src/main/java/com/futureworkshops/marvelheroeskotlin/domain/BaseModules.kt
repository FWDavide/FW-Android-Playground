/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.domain

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.futureworkshops.marvelheroeskotlin.data.network.NetworkConfig
import com.futureworkshops.marvelheroeskotlin.data.network.RestManager
import com.futureworkshops.marvelheroeskotlin.data.rx.SchedulersProvider
import com.futureworkshops.marvelheroeskotlin.data.rx.WorkerSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

@Module
class ApplicationModule(val app: Application) {
    
    @Singleton
    @Provides
    fun bindContext(): Context = app
    
    @Singleton
    @Provides
    fun provideSharedPrefs(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
    
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()
    
    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulersProvider = WorkerSchedulerProvider()
}

/**
 * This Module provides the Networking components of our App.
 */
@Module
class NetModule {
    
    @Singleton
    @Provides
    fun providesRestManager(schedulersProvider: SchedulersProvider,
                            networkConfig: NetworkConfig): RestManager {
        return RestManager(schedulersProvider, networkConfig)
    }
    
}