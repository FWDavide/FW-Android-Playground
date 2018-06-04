/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen

import com.futureworkshops.marvelheroes.domain.navigator.Navigator
import com.futureworkshops.marvelheroes.presentation.common.BasePresenter
import io.reactivex.disposables.CompositeDisposable

import javax.inject.Inject


class SplashscreenPresenter
@Inject
constructor(private val navigator: Navigator, compositeDisposable: CompositeDisposable) : BasePresenter<SplashView>(compositeDisposable) {
    
    fun showCharacterScreen() {
        navigator.showCharactersScreen()
    }
}
