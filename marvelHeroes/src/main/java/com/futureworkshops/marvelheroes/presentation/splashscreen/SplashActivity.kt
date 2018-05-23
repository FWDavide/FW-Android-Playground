/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.splashscreen

import android.os.Bundle
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity
import com.github.jorgecastillo.State
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.*
import timber.log.Timber
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashScreenContract.View {
    
    
    @Inject
    private var splashScreenPresenter: SplashScreenPresenter? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
        fillableLoader.apply {
            setSvgPath(getString(R.string.splash_svg_path))
            setOnStateChangeListener { state ->
                when (state) {
                    State.NOT_STARTED -> Timber.d("fillable state: NOT STARTED ")
                    State.STROKE_STARTED -> Timber.d("fillable state: STROKE STARTED ")
                    State.FILL_STARTED -> Timber.d("fillable state: FILL STARTED ")
                    State.FINISHED -> {
                        Timber.d("fillable state: FINISHED ")
                        splashScreenPresenter?.showCharacterScreen()
                    }
                }
            }
        }
        
    }
}
