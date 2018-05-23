/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen.dagger


import com.futureworkshops.marvelheroes.domain.dagger.ApplicationModule
import com.futureworkshops.marvelheroes.domain.dagger.NetModule
import com.futureworkshops.marvelheroes.domain.navigator.Navigator
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashActivity
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashScreenContract
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashScreenPresenter

import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * This is an example of a "screen-specific" module. Meaning a Module that provides dependencies that
 * are not already covered by our [ApplicationModule] and [NetModule]
 *
 * These dependencies have the scope of the activity.
 */
@Module
abstract class SplashscreenModule {
    
    /**
     * This method binds the SplashActivity to SplashScreenContract.View. When the LoginContract.View is requested, the activity is returned.
     */
    @Binds
    internal abstract fun providesContractView(activity: SplashActivity): SplashScreenContract.View
    
    @Provides
    internal fun providesNavigator(activity: SplashActivity): Navigator {
        return Navigator(activity)
    }
    
    @Provides
    internal fun providesPresenter(navigator: Navigator, view: SplashScreenContract.View): SplashScreenContract.Presenter {
        return SplashScreenPresenter(navigator, view)
    }
    
}
