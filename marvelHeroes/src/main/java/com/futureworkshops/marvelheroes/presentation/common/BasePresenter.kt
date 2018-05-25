/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.common

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by dimitrios on 25/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

open class BasePresenter<VIEW : MvpView> constructor(val compositeDisposable: CompositeDisposable) : Presenter<VIEW> {
    
    private var weakReference: WeakReference<VIEW>? = null
    
    override fun onAttachView(view: VIEW) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
            view.attachPresenter(this)
        }
    }
    
    override fun onUnsubscribe() {
        compositeDisposable.clear()
    }
    
    override fun onSubscribe() {
        //nothing for now
    }
    
    override fun onDetachView() {
        weakReference?.clear()
        weakReference = null
        compositeDisposable.clear()
    }
    
    val view: VIEW?
        get() = weakReference?.get()
    
    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null
}