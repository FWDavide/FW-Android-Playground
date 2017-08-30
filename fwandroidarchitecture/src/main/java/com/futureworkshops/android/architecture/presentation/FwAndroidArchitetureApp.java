package com.futureworkshops.android.architecture.presentation;


import com.futureworkshops.android.architecture.domain.dagger.AppComponent;
import com.futureworkshops.android.architecture.domain.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by stelian on 28/08/2017.
 */

public class FwAndroidArchitetureApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
