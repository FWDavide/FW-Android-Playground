package com.futureworkshops.android.architecture.presentation;

import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by stelian on 28/08/2017.
 */

public class FwAndroidArchitetureApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return null;
    }
}
