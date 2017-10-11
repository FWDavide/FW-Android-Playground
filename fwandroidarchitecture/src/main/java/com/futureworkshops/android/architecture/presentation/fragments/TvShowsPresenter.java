package com.futureworkshops.android.architecture.presentation.fragments;

import java.lang.ref.WeakReference;

/**
 * Created by David on 06/10/2017.
 */

public class TvShowsPresenter implements TvShowsContract.Presenter {

    private final TvShowsInteractor createProfileInteractor;
    private final WeakReference<TvShowsContract.View> viewWeakReference;

    public TvShowsPresenter(TvShowsInteractor createProfileInteractor, TvShowsContract.View view) {
        this.createProfileInteractor = createProfileInteractor;
        this.viewWeakReference = new WeakReference<>(view);
    }
}