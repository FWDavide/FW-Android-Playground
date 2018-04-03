/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation;

import android.app.Application;

import com.futureworkshops.marvelheroes.data.network.NetworkConfig;
import com.futureworkshops.marvelheroes.domain.dagger.AppComponent;
import com.futureworkshops.marvelheroes.domain.dagger.DaggerAppComponent;
import com.futureworkshops.marvelheroes.domain.rx.scheduler.WorkerSchedulerProvider;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by stelian on 03/04/2018.
 */

public class MarvelHeroesApp extends DaggerApplication {
    
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder()
            .application(this)
            .schedulerProvider(new WorkerSchedulerProvider())
            .networkConfiguration(new NetworkConfig())
            .build();
        appComponent.inject(this);
        return appComponent;
    }
}
