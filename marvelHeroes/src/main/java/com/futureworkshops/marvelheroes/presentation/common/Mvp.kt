/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.common

/**
 * Created by dimitrios on 25/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

interface MvpView {
    
    fun setPresenter(presenter : Presenter<*>)
}

interface Presenter<VIEW : MvpView> {
    
    fun onAttachView(view : VIEW)
    
    fun onSubscribe()
    
    fun onUnsubscribe()
    
    fun onDetachView()
}