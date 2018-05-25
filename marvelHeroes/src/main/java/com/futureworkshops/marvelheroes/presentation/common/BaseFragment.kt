/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.common

import android.content.Context
import android.support.v4.app.Fragment
import com.futureworkshops.marvelheroes.domain.dagger.AppComponent
import com.futureworkshops.marvelheroes.presentation.MarvelHeroesApp

/**
 * Created by dimitrios on 25/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

abstract class BaseFragment : Fragment(), MvpView {
    
    private var presenter: Presenter<*>? = null
    
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()
    }
    
    abstract fun inject()
    
    fun appComponent(): AppComponent = MarvelHeroesApp.appComponent
    
    override fun attachPresenter(presenter: Presenter<*>) {
        this.presenter = presenter
    }
    
    
    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDetachView()
        presenter = null
    }
    
}