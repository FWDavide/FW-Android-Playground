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

data class ApiResponse<T>(
        @SerializedName("code") val code: Int = 0,
        @SerializedName("status") val status: String? = null,
        @SerializedName("copyright") val copyright: String? = null,
        @SerializedName("attributionText") val attributionText: String? = null,
        @SerializedName("attributionHTML") val getAttributionHtml: String? = null,
        @SerializedName("etag") val etag: String? = null,
        @SerializedName("data") val response: T? = null)
