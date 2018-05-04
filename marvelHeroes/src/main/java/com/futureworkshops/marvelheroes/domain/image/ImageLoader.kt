/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.image

import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.bumptech.glide.load.Key
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.signature.ObjectKey
import com.futureworkshops.marvelheroes.R

/**
 * Created by stelian on 04/04/2018.
 */

class ImageLoader {
    
    fun loadThumbnail(target: ImageView, imagePath: String) {
        GlideApp.with(target)
                .load(imagePath)
                .placeholder(R.drawable.default_thumbnail_placeholder)
                .signature(getThumbnailCacheKey(imagePath))
                .centerCrop()
                .into(target)
    }
    
    fun loadLandscapeImage(target: ImageView, imagePath: String, listener: RequestListener<Drawable>) {
        GlideApp.with(target)
                .load(imagePath)
                .fitCenter()
                .signature(getLandscapeCacheKey(imagePath))
                .dontAnimate()
                .listener(listener)
                .placeholder(R.drawable.default_thumbnail_placeholder)
                .into(target)
    }
    
    private fun getLandscapeCacheKey(path: String): Key {
        return ObjectKey("landscape_$path")
    }
    
    private fun getThumbnailCacheKey(path: String): Key {
        return ObjectKey("thumb_$path")
    }
    
}
