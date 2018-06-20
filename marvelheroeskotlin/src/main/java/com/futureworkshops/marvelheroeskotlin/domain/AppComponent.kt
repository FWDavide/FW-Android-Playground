/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.domain

import android.content.Context
import android.content.SharedPreferences
import com.futureworkshops.marvelheroeskotlin.data.network.RestManager
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

@Singleton
@Component(modules = [(ApplicationModule::class), (NetModule::class)])
interface AppComponent {
    
    fun context(): Context
    
    fun sharedPrefs(): SharedPreferences
    
    fun compositeDisposable(): CompositeDisposable
    
    fun restManager(): RestManager
}