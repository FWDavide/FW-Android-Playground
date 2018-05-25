/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.dagger

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.futureworkshops.marvelheroes.data.network.RestManager
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Singleton
@Component(modules = [(ApplicationModule::class), (NetModule::class)])
interface AppComponent {
    
    fun context(): Context
    
    fun resources(): Resources
    
    fun sharedPrefs(): SharedPreferences
    
    fun compositeDisposable(): CompositeDisposable
    
    fun restManager(): RestManager
}
