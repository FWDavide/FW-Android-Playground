/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen;

import android.os.Bundle;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity;

public class SplashActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
