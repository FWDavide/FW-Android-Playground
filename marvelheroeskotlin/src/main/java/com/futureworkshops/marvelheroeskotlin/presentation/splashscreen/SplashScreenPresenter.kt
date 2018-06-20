/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.splashscreen

import com.futureworkshops.marvelheroeskotlin.domain.navigator.Navigator
import com.futureworkshops.marvelheroeskotlin.presentation.common.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class SplashscreenPresenter
@Inject
constructor(private val navigator: Navigator, compositeDisposable: CompositeDisposable) : BasePresenter<SplashView>(compositeDisposable) {
    
    fun showCharacterScreen() {
        navigator.showCharactersScreen()
    }
}