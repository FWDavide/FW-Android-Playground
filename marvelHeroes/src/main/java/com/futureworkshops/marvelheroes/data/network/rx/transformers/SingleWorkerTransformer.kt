/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.rx.transformers

import com.futureworkshops.marvelheroes.data.network.rx.scheduler.SchedulersProvider

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer


class SingleWorkerTransformer<T>(private val schedulersProvider: SchedulersProvider) : SingleTransformer<T, T> {
    
    override fun apply(upstream: Single<T>): SingleSource<T> {
        // subscribeOn will cause all upstream calls to run on an io thread.
        // observeOn will cause all the downstream calls to run on the main thread.
        return upstream.subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
    }
}
