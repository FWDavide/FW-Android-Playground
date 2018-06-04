/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen.dagger

import com.futureworkshops.marvelheroes.domain.dagger.ActivityScope
import com.futureworkshops.marvelheroes.domain.dagger.AppComponent
import com.futureworkshops.marvelheroes.domain.navigator.Navigator
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashActivity
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashscreenPresenter
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by dimitrios on 25/05/2018.
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