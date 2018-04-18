/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen.dagger;


import com.futureworkshops.marvelheroes.domain.dagger.ApplicationModule;
import com.futureworkshops.marvelheroes.domain.dagger.NetModule;
import com.futureworkshops.marvelheroes.domain.navigator.Navigator;
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashActivity;
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashscreenContract;
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashscreenPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * This is an example of a "screen-specific" module. Meaning a Module that provides dependencies that
 * are not already covered by our {@link ApplicationModule} and {@link NetModule}
 *
 * These dependencies have the scope of the activity.
 */
@Module()
public abstract class SplashscreenModule {
    
    @Provides
    static Navigator providesNavigator(SplashActivity activity) {
        return new Navigator(activity);
    }
    
    @Provides
    static SplashscreenContract.Presenter providesPresenter(Navigator navigator, SplashscreenContract.View view) {
        return new SplashscreenPresenter(navigator, view);
    }
    
    /**
     * This method binds the SplashActivity to SplashscreenContract.View. When the LoginContract.View is requested, the activity is returned.
     */
    @Binds
    abstract SplashscreenContract.View providesContractView(SplashActivity activity);
    
}
