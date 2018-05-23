/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen

import com.futureworkshops.marvelheroes.domain.navigator.Navigator

import javax.inject.Inject


class SplashScreenPresenter
@Inject
constructor(private val navigator: Navigator, private val viewReference: SplashScreenContract.View) : SplashScreenContract.Presenter {
    
    override fun showCharacterScreen() {
        navigator.showCharactersScreen()
    }
}
