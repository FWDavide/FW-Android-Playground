/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.common;

import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.futureworkshops.marvelheroes.R;

import butterknife.BindView;

/**
 * Base activity that can handle {@link Toolbar} set up.
 */

public class BaseActivity extends AppCompatActivity {
    
    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adjustThemeColours();
        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    protected void setupToolbar(boolean showHomeAsUp) {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }
    
    /**
     * Pre-Marshmallow devices don't support styling the status bar text and icon colours in case
     * we have a really light background color for the status bar! To make status bar text and icons
     * visible on these devices we manually change the status bar background.
     */
    private void adjustThemeColours() {
        if (Build.VERSION.SDK_INT < VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            
            window.setStatusBarColor(getResources().getColor(R.color.textColorSecondary));
            
        }
    }
    
}
