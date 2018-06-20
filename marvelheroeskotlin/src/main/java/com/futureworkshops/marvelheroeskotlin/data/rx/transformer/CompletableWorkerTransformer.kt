/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.rx.transformer

import com.futureworkshops.marvelheroeskotlin.data.rx.SchedulersProvider
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class CompletableWorkerTransformer(private val schedulersProvider: SchedulersProvider) : CompletableTransformer {
    
    override fun apply(upstream: Completable): CompletableSource {
        // subscribeOn will cause all upstream calls to run on an io thread.
        // observeOn will cause all the downstream calls to run on the main thread.
        return upstream.subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
    }
}