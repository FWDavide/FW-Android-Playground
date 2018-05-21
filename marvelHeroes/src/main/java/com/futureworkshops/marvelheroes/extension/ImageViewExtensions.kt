/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.Key
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.signature.ObjectKey
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.domain.image.GlideApp

/**
 * Created by dimitrios on 21/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

fun ImageView.loadThumbnail(imagePath: String) {
    GlideApp.with(this)
            .load(imagePath)
            .placeholder(R.drawable.default_thumbnail_placeholder)
            .signature(getThumbnailCacheKey(imagePath))
            .centerCrop()
            .into(this)
}

fun ImageView.loadLandscaperImage(imagePath: String, listener: RequestListener<Drawable>) {
    GlideApp.with(this)
            .load(imagePath)
            .fitCenter()
            .signature(getLandscapeCacheKey(imagePath))
            .dontAnimate()
            .listener(listener)
            .placeholder(R.drawable.default_thumbnail_placeholder)
            .into(this)
}

private fun getLandscapeCacheKey(path: String): Key {
    return ObjectKey("landscape_$path")
}

private fun getThumbnailCacheKey(path: String): Key {
    return ObjectKey("thumb_$path")
}