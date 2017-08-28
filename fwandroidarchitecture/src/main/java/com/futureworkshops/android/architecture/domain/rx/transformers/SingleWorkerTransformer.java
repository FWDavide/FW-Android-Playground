package com.futureworkshops.android.architecture.domain.rx.transformers;


import com.futureworkshops.android.architecture.domain.rx.scheduler.SchedulersProvider;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;


public class SingleWorkerTransformer<T> implements SingleTransformer<T, T> {

    private final SchedulersProvider schedulersProvider;

    public SingleWorkerTransformer(SchedulersProvider schedulersProvider) {
        this.schedulersProvider = schedulersProvider;
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream.subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui());
    }
}
