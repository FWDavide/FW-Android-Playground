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

class ApiResponse<T> {
    
    @SerializedName("code")
    var code: Int = 0
    
    @SerializedName("status")
    var status: String? = null
    
    @SerializedName("copyright")
    var copyright: String? = null
    
    @SerializedName("attributionText")
    var attributionText: String? = null
    
    @SerializedName("attributionHTML")
    var getAttributionHtml: String? = null
    
    @SerializedName("etag")
    var etag: String? = null
    
    @SerializedName("data")
    var response: T? = null
    
    constructor() {}
    
    constructor(apiResponse: ApiResponse<*>) {
        this.code = apiResponse.code
        this.status = apiResponse.status
        this.copyright = apiResponse.copyright
        this.attributionText = apiResponse.attributionText
        this.getAttributionHtml = apiResponse.getAttributionHtml
        this.etag = apiResponse.etag
    }
}
