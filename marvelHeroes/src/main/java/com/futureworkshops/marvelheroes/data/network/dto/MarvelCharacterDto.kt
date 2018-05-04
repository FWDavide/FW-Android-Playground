/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Simplified DTO for a Marvel character - this DTO ignores comics,events and stories;
 *
 * Created by stelian on 03/04/2018.
 */

data class MarvelCharacterDto(@SerializedName("id") val id: String?,
                              @SerializedName("name") val name: String?,
                              @SerializedName("description") val description: String?,
                              @SerializedName("modified") val modified: String?,
                              @SerializedName("resourceURI") val resourceUri: String?,
                              @SerializedName("thumbnail") val thumbnail: ImageDto?)
