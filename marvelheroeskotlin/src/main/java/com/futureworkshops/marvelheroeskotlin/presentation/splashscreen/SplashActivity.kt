/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.splashscreen

import android.os.Bundle
import com.futureworkshops.marvelheroeskotlin.R
import com.futureworkshops.marvelheroeskotlin.presentation.common.BaseActivity
import com.futureworkshops.marvelheroeskotlin.presentation.common.MvpView
import kotlinx.android.synthetic.main.activity_splash.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

interface SplashView : MvpView

class SplashActivity : BaseActivity(), SplashView {
    
    @Inject
    lateinit var splashscreenPresenter: SplashscreenPresenter
    
    fun inject() {
        DaggerSplashComponent.builder()
                .appComponent(appComponent())
                .splashScreenModule(SplashScreenModule(this@SplashActivity))
                .build()
                .inject(this)
        splashscreenPresenter.bindView(this)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        inject()
        setupFillableLoader()
    }
    
    override fun onStart() {
        super.onStart()
        fillableLoader.postDelayed({ fillableLoader.start() }, 200)
    }
    
    //    @OnClick(R.id.fillableLoader)
    //    void onLoaderClicked() {
    //        fillableLoader.postDelayed(() -> {
    //            fillableLoader.reset();
    //            fillableLoader.start();
    //        }, 200);
    //    }
    
    private fun setupFillableLoader() {
        fillableLoader.setSvgPath(getString(R.string.splash_svg_path))
        fillableLoader.setOnStateChangeListener({ state ->
            when (state) {
                com.github.jorgecastillo.State.NOT_STARTED -> Timber.d("fillable state: NOT STARTED ")
                com.github.jorgecastillo.State.STROKE_STARTED -> Timber.d("fillable state: STROKE STARTED ")
                com.github.jorgecastillo.State.FILL_STARTED -> Timber.d("fillable state: FILL STARTED ")
                com.github.jorgecastillo.State.FINISHED -> {
                    splashscreenPresenter.showCharacterScreen()
                }
            }
        })
    }
}