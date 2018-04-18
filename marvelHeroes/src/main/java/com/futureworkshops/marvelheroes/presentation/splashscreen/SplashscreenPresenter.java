/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen;

import com.futureworkshops.marvelheroes.domain.navigator.Navigator;

import javax.inject.Inject;


public class SplashscreenPresenter implements SplashscreenContract.Presenter {
    
    private Navigator navigator;
    private final SplashscreenContract.View viewReference;
    
    @Inject
    public SplashscreenPresenter(Navigator navigator, SplashscreenContract.View view) {
        this.navigator = navigator;
        this.viewReference = view;
    }
    
    @Override
    public void showCharacterScreen() {
        navigator.showCharacterScreen();
    }
}
