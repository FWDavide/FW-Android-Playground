/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.common

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

open class BasePresenter<VIEW : MvpView> constructor(val compositeDisposable: CompositeDisposable) : Presenter<VIEW> {
    
    lateinit var weakReference: WeakReference<VIEW>
    
    override fun bindView(view: VIEW) {
        weakReference = WeakReference(view)
    }
    
    override fun onUnsubscribe() {
        compositeDisposable.clear()
    }
    
    override fun onSubscribe() {
        //nothing for now
    }
    
    val view: VIEW?
        get() = weakReference.get()
    
}