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

data class ApiResponse<T>(
        @SerializedName("code") val code: Int = 0,
        @SerializedName("status") val status: String? = null,
        @SerializedName("copyright") val copyright: String? = null,
        @SerializedName("attributionText") val attributionText: String? = null,
        @SerializedName("attributionHTML") val getAttributionHtml: String? = null,
        @SerializedName("etag") val etag: String? = null,
        @SerializedName("data") val response: T? = null)
