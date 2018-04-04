/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.dagger;

import com.futureworkshops.marvelheroes.domain.image.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stelian on 04/04/2018.
 */

@Module
public class ImageModule {
    
    /**
     * Use a single instance of {@link ImageLoader} to load images in the app.
     * @return
     */
    @Provides
    @Singleton
    ImageLoader provideImageLoader() {
        return new ImageLoader();
    }
}
