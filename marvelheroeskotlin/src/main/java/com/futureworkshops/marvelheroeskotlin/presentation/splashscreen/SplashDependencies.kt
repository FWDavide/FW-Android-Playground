/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.splashscreen

import com.futureworkshops.marvelheroeskotlin.domain.ActivityScope
import com.futureworkshops.marvelheroeskotlin.domain.AppComponent
import com.futureworkshops.marvelheroeskotlin.domain.navigator.Navigator
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

@Module
class SplashScreenModule(private val activity: SplashActivity) {
    
    @ActivityScope
    @Provides
    fun providesNavigator(): Navigator {
        return Navigator(activity)
    }
    
    @ActivityScope
    @Provides
    fun providesPresenter(navigator: Navigator, compositeDisposable: CompositeDisposable): SplashscreenPresenter {
        return SplashscreenPresenter(navigator, compositeDisposable)
    }
    
}

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [SplashScreenModule::class])
interface SplashComponent {
    
    fun inject(splashActivity: SplashActivity)
    
}