/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

data class MarvelCharacterDto(@SerializedName("id") val id: String?,
                              @SerializedName("name") val name: String?,
                              @SerializedName("description") val description: String?,
                              @SerializedName("modified") val modified: String?,
                              @SerializedName("resourceURI") val resourceUri: String?,
                              @SerializedName("thumbnail") val thumbnail: ImageDto?)