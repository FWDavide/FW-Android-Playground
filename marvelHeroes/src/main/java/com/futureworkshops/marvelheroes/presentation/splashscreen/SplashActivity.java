/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen;

import android.os.Bundle;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashActivity extends BaseActivity implements SplashscreenContract.View {
    
    @Inject
    SplashscreenPresenter splashscreenPresenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
