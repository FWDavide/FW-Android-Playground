/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.common

import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import com.futureworkshops.marvelheroes.R

/**
 * Base activity that can handle [Toolbar] set up.
 */

open class BaseActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustThemeColours()
        
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
        if (Build.VERSION.SDK_INT < VERSION_CODES.M) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.textColorSecondary)
        }
    }
    
}
