/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO used to parse the response from the Marvel API and extract only the relevant information
 * without polluting all the DTOs with redundant information.
 *
 * Created by stelian on 03/04/2018.
 */

class ApiCollection<T> {
    
    @SerializedName("offset")
    var offset: Int = 0
    
    @SerializedName("limit")
    var limit: Int = 0
    
    @SerializedName("total")
    var total: Int = 0
    
    @SerializedName("count")
    var count: Int = 0
    
    @SerializedName("results")
    var response: T? = null
    
    constructor(apiCollection: ApiCollection<*>) {
        this.offset = apiCollection.offset
        this.limit = apiCollection.limit
        this.total = apiCollection.total
        this.count = apiCollection.count
    }
}
