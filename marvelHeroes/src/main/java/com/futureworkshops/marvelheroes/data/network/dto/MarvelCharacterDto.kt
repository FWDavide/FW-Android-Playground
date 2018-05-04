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

class MarvelCharacterDto {
    
    @SerializedName("id")
    var id: String? = null
    
    @SerializedName("name")
    var name: String? = null
    
    @SerializedName("description")
    var description: String? = null
    
    @SerializedName("modified")
    var modified: String? = null
    
    @SerializedName("resourceURI")
    var resourceUri: String? = null
    
    @SerializedName("thumbnail")
    var thumbnail: ImageDto? = null
}
