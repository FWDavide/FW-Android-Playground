/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.common

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import com.futureworkshops.marvelheroeskotlin.MarvelHeroesKotlinApp
import com.futureworkshops.marvelheroeskotlin.R
import com.futureworkshops.marvelheroeskotlin.domain.AppComponent

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

abstract class BaseActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustThemeColours()
    }
    
    fun appComponent(): AppComponent = MarvelHeroesKotlinApp.appComponent
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    fun setupToolbar(toolbar: Toolbar, showHomeAsUp: Boolean) {
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