/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by stelian on 03/04/2018.
 */

data class ImageDto(@SerializedName("path") val path: String?,
                    @SerializedName("extension") val extension: String?) {
    
    override fun toString(): String {
        return "ImageDto{" + "path='" + path + '\''.toString() + ", extension='" + extension + '\''.toString() + '}'.toString()
    }
}
