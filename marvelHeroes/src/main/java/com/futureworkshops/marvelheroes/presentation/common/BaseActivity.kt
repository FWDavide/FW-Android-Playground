/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.common

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.domain.dagger.AppComponent
import com.futureworkshops.marvelheroes.presentation.MarvelHeroesApp

/**
 * Created by dimitrios on 25/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

abstract class BaseActivity : AppCompatActivity(), MvpView {
    
    private var presenter: Presenter<*>? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
        adjustThemeColours()
    }
    
    override fun onResume() {
        super.onResume()
        presenter?.onSubscribe()
    }
    
    override fun onPause() {
        super.onPause()
        presenter?.onUnsubscribe()
    }
    
    abstract fun onActivityInject()
    
    fun getAppComponent(): AppComponent = MarvelHeroesApp.appComponent
    
    override fun setPresenter(presenter: Presenter<*>) {
        this.presenter = presenter
    }
    
    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDetachView()
        presenter = null
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    
    protected fun setupToolbar(toolbar: Toolbar, showHomeAsUp: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }
    
    /**
     * Pre-Marshmallow devices don't support styling the status bar text and icon colours in case
     * we have a really light background color for the status bar! To make status bar text and icons
     * visible on these devices we manually change the status bar background.
     */
    private fun adjustThemeColours() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window?.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = ContextCompat.getColor(this@BaseActivity, R.color.textColorSecondary)
            }
        }
    }
    
}