/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation;

import android.util.Log;

import com.futureworkshops.marvelheroes.BuildConfig;
import com.futureworkshops.marvelheroes.data.network.NetworkConfig;
import com.futureworkshops.marvelheroes.data.network.rx.scheduler.WorkerSchedulerProvider;
import com.futureworkshops.marvelheroes.domain.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

/**
 * Created by stelian on 03/04/2018.
 */

public class MarvelHeroesApp extends DaggerApplication {
    
    
    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
    }
    
    
    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
    }
    
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
            .application(this)
            .schedulerProvider(new WorkerSchedulerProvider())
            .networkConfiguration(new NetworkConfig())
            .build();
    }
    
    public class ReleaseLogTree extends Timber.Tree {
        
        private static final int MAX_LOG_LENGTH = 4000;
        
        @Override
        protected boolean isLoggable(String tag, int priority) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return false;
            }
            
            return true;
        }
        
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (isLoggable(tag, priority)) {
                if (message.length() < MAX_LOG_LENGTH) {
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, message);
                    } else {
                        Log.println(priority, tag, message);
                    }
                    return;
                }
                
                int length = message.length();
                for (int i = 0; i < length; i++) {
                    int newLine = message.indexOf('\n', i);
                    newLine = newLine != -1 ? newLine : length;
                    do {
                        int end = Math.min(newLine, i + MAX_LOG_LENGTH);
                        String part = message.substring(i, end);
                        if (priority == Log.ASSERT) {
                            Log.wtf(tag, part);
                        } else {
                            Log.println(priority, tag, part);
                        }
                        i = end;
                    } while (i < newLine);
                }
            }
        }
    }
    
}
