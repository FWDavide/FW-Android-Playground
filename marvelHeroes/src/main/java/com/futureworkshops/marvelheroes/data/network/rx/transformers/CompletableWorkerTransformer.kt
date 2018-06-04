/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.rx.transformers


import com.futureworkshops.marvelheroes.data.network.rx.scheduler.SchedulersProvider

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer

/**
 * Created by stelian on 04/02/2018.
 */

class CompletableWorkerTransformer(private val schedulersProvider: SchedulersProvider) : CompletableTransformer {
    
    override fun apply(upstream: Completable): CompletableSource {
        // subscribeOn will cause all upstream calls to run on an io thread.
        // observeOn will cause all the downstream calls to run on the main thread.
        return upstream.subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
    }
}
