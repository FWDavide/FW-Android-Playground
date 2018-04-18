/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen;

import android.os.Bundle;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity;
import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import timber.log.Timber;

public class SplashActivity extends BaseActivity implements SplashscreenContract.View {
    
    @BindView(R.id.fillableLoader)
    FillableLoader fillableLoader;
    
    @Inject
    SplashscreenPresenter splashscreenPresenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        ButterKnife.bind(this);
        
        setupFillableLoader();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        fillableLoader.postDelayed(() -> fillableLoader.start(), 200);
    }

//    @OnClick(R.id.fillableLoader)
//    void onLoaderClicked() {
//        fillableLoader.postDelayed(() -> {
//            fillableLoader.reset();
//            fillableLoader.start();
//        }, 200);
//    }
    
    private void setupFillableLoader() {
        fillableLoader.setSvgPath(getString(R.string.splash_svg_path));
        
        fillableLoader.setOnStateChangeListener(state -> {
            switch (state) {
                case State.NOT_STARTED:
                    Timber.d("fillable state: NOT STARTED ");
                    break;
                case State.STROKE_STARTED:
                    Timber.d("fillable state: STROKE STARTED ");
                    break;
                case State.FILL_STARTED:
                    Timber.d("fillable state: FILL STARTED ");
                    break;
                case State.FINISHED:
                    Timber.d("fillable state: FINISHED ");
                    splashscreenPresenter.showCharacterScreen();
                    break;
            }
        });
    }
}
