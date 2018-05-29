/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.common

import android.support.v4.app.Fragment
import com.futureworkshops.marvelheroes.domain.dagger.AppComponent
import com.futureworkshops.marvelheroes.presentation.MarvelHeroesApp

/**
 * Created by dimitrios on 25/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

abstract class BaseFragment : Fragment(), MvpView {
    
    fun appComponent(): AppComponent = MarvelHeroesApp.appComponent
    
}