/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network;

import com.futureworkshops.marvelheroes.data.network.dto.ApiResponse;
import com.futureworkshops.marvelheroes.data.network.dto.CharacterDto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by stelian on 03/04/2018.
 */

public interface RestApi {
    
    String API_VERSION = "v1/public";
    
    @GET("characters")
    Call<ApiResponse<List<CharacterDto>>> getCharacters(@QueryMap Map<String, Object> characterFilter);
    
    @GET(API_VERSION + "/characters/{id}")
    Call<ApiResponse<List<CharacterDto>>> getCharacter(@Path("id") String characterId);
    
}
