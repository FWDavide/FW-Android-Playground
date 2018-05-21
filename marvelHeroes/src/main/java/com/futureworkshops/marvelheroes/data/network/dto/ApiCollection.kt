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

data class ApiCollection<T>(
        @SerializedName("offset") val offset: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("results") val response: T?)
