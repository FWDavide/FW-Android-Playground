/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.rx.transformer

import com.futureworkshops.marvelheroeskotlin.data.rx.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class ObservableWorkerTransformer<T>(private val schedulersProvider: SchedulersProvider) : ObservableTransformer<T, T> {
    
    override fun apply(upstream: Observable<T>): Observable<T> {
        // subscribeOn will cause all upstream calls to run on an io thread.
        // observeOn will cause all the downstream calls to run on the main thread.
        return upstream.subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
    }
}