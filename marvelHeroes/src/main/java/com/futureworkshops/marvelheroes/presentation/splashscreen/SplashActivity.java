/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen;

import android.os.Bundle;
import android.util.Log;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity;
import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class SplashActivity extends BaseActivity implements SplashscreenContract.View {
    
    private static final String TAG = SplashActivity.class.getSimpleName();
    
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
                    Log.d(TAG, "fillable state: NOT STARTED ");
                    break;
                case State.STROKE_STARTED:
                    Log.d(TAG, "fillable state: STROKE STARTED ");
                    break;
                case State.FILL_STARTED:
                    Log.d(TAG, "fillable state: FILL STARTED ");
                    break;
                case State.FINISHED:
                    Log.d(TAG, "fillable state: FINISHED ");
                    splashscreenPresenter.showCharacterScreen();
                    break;
            }
        });
    }
}
