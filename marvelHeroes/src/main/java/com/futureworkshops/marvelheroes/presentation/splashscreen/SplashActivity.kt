/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen


import android.os.Bundle
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.R.id.fillableLoader
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity
import com.futureworkshops.marvelheroes.presentation.common.MvpView
import com.futureworkshops.marvelheroes.presentation.splashscreen.dagger.DaggerSplashComponent
import com.futureworkshops.marvelheroes.presentation.splashscreen.dagger.SplashScreenModule
import timber.log.Timber
import javax.inject.Inject

interface SplashView : MvpView

class SplashActivity : BaseActivity(), SplashView {
    
    @Inject
    lateinit var splashscreenPresenter: SplashscreenPresenter
    
    override fun inject() {
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
