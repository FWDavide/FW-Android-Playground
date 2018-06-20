/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.network

import com.futureworkshops.marvelheroeskotlin.data.dto.ApiCollection
import com.futureworkshops.marvelheroeskotlin.data.dto.ApiResponse
import com.futureworkshops.marvelheroeskotlin.data.dto.MarvelCharacterDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

const val API_VERSION = "v1/public"

interface RestApi {
    
    @GET("$API_VERSION/characters")
    fun getCharacters(@QueryMap characterFilter: @JvmSuppressWildcards Map<String, Any>): Single<ApiResponse<ApiCollection<List<MarvelCharacterDto>>>>
    
    @GET("$API_VERSION/characters/{id}")
    fun getCharacter(@Path("id") characterId: String): Single<ApiResponse<List<MarvelCharacterDto>>>
    
}