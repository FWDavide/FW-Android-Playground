/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network

import com.futureworkshops.marvelheroes.data.network.dto.ApiCollection
import com.futureworkshops.marvelheroes.data.network.dto.ApiResponse
import com.futureworkshops.marvelheroes.data.network.dto.MarvelCharacterDto

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by stelian on 03/04/2018.
 */

const val API_VERSION = "v1/public"

interface RestApi {
    
    @GET("$API_VERSION/characters")
    fun getCharacters(@QueryMap characterFilter: @JvmSuppressWildcards Map<String, Any>): Single<ApiResponse<ApiCollection<List<MarvelCharacterDto>>>>
    
    @GET("$API_VERSION/characters/{id}")
    fun getCharacter(@Path("id") characterId: String): Single<ApiResponse<List<MarvelCharacterDto>>>
    
}
