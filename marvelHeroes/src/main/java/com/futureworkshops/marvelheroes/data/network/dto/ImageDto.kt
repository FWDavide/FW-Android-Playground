/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by stelian on 03/04/2018.
 */

class ImageDto {
    
    @SerializedName("path")
    var path: String? = null
    
    @SerializedName("extension")
    var extension: String? = null
    
    override fun toString(): String {
        return "ImageDto{" + "path='" + path + '\''.toString() + ", extension='" + extension + '\''.toString() + '}'.toString()
    }
}
