/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.image;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.signature.ObjectKey;
import com.futureworkshops.marvelheroes.R;

/**
 * Created by stelian on 04/04/2018.
 */

public class ImageLoader {
    
    public void loadThumbnail(@NonNull ImageView target, @NonNull String imagePath) {
        GlideApp.with(target)
            .load(imagePath)
            .placeholder(R.drawable.default_thumbnail_placeholder)
            .signature(getThumbnailCacheKey(imagePath))
            .centerCrop()
            .into(target);
    }
    
    public void loadLandscapeImage(@NonNull ImageView target, @NonNull String imagePath, @NonNull RequestListener listener) {
        GlideApp.with(target)
            .load(imagePath)
            .listener(listener)
            .placeholder(R.drawable.default_thumbnail_placeholder)
            .signature(getLandscapeCacheKey(imagePath))
            .centerCrop()
            .into(target);
    }
    
    private Key getLandscapeCacheKey(String path) {
        return new ObjectKey("landscape_" + path);
    }
    
    private Key getThumbnailCacheKey(String path) {
        return new ObjectKey("thumb_" + path);
    }
    
}
