/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.futureworkshops.marvelheroes.data.network.NetworkConfig
import com.futureworkshops.marvelheroes.data.network.RestManager
import com.futureworkshops.marvelheroes.data.network.rx.scheduler.SchedulersProvider
import com.futureworkshops.marvelheroes.data.network.rx.scheduler.WorkerSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by dimitrios on 25/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

/**
 * This Module provides the common Android-Framework dependencies that other components will use.
 */
@Module
class ApplicationModule(val app: Application) {
    
    @Singleton
    @Provides
    fun bindContext(): Context = app
    
    @Singleton
    @Provides
    fun provideRes(): Resources = app.resources
    
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