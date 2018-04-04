/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.image;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.signature.ObjectKey;
import com.futureworkshops.marvelheroes.R;

/**
 * Created by stelian on 04/04/2018.
 */

public class ImageLoader {
    
    public void loadThumbnail(@NonNull ImageView target, @NonNull String imagePath) {
        GlideApp.with(target)
            .load(imagePath)
            .signature(getThumbnailCacheKey(imagePath))
            .centerCrop()
            .placeholder(R.drawable.default_thumbnail_placeholder)
            .into(target);
    }
    
    public void loadLandscapeImage(@NonNull ImageView target, @NonNull String imagePath) {
        GlideApp.with(target)
            .load(imagePath)
            .signature(getLandscapeCacheKey(imagePath))
            .centerCrop()
            .placeholder(R.drawable.default_thumbnail_placeholder)
            .into(target);
    }
    
    private Key getLandscapeCacheKey(String path) {
        return new ObjectKey("landscape_" + path);
    }
    
    private Key getThumbnailCacheKey(String path) {
        return new ObjectKey("thumb_" + path);
    }
    
}
