/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.common

import android.support.v4.app.Fragment
import com.futureworkshops.marvelheroeskotlin.MarvelHeroesKotlinApp
import com.futureworkshops.marvelheroeskotlin.domain.AppComponent

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

abstract class BaseFragment : Fragment(), MvpView {
    
    fun appComponent(): AppComponent = MarvelHeroesKotlinApp.appComponent
    
}