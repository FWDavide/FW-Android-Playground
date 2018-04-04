/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.repositories.character;

import android.support.annotation.NonNull;

import com.futureworkshops.marvelheroes.data.network.RestManager;
import com.futureworkshops.marvelheroes.data.network.dto.ApiResponse;
import com.futureworkshops.marvelheroes.data.network.dto.CharacterDto;
import com.futureworkshops.marvelheroes.domain.model.Character;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by stelian on 03/04/2018.
 */

public class MarvelCharacterRepository {
    
    public static final int AVENGERS__SERIES_ID = 3621;
    
    private RestManager restManager;
    
    @Inject
    public MarvelCharacterRepository(RestManager restManager) {
        this.restManager = restManager;
    }
    
    public Single<List<Character>> getAvengersCharacters() {
        CharacterQuery filter = new CharacterQuery.Builder()
            .serie(AVENGERS__SERIES_ID)
            .orderByName(true)
            .build();
    
        return getCharactersWithQuery(filter)
            .flatMap(response -> {
                final List<CharacterDto> characterDtoList = response.getResponse();
                final List<Character> characters = new ArrayList<>();
            
                for (CharacterDto dto : characterDtoList) {
                    characters.add(CharacterMapper.dtoToCharacter(dto));
                }
            
                return Single.just(characters);
            
            });
    }
    
    public Single<List<Character>> getCharacterDetails(int characterId) {
        return restManager.getCharacterDetails(String.valueOf(characterId))
            .flatMap(response -> {
                final List<CharacterDto> characterDtoList = response.getResponse();
                final List<Character> characters = new ArrayList<>();
                
                for (CharacterDto dto : characterDtoList) {
                    characters.add(CharacterMapper.dtoToCharacter(dto));
                }
                
                return Single.just(characters);
                
            });
    }
    
    public Single<List<Character>> getAllCharacters(int offset, int limit) {
        CharacterQuery filter = new CharacterQuery.Builder()
            .offset(offset)
            .limit(limit)
            .build();
        
        return getCharactersWithQuery(filter)
            .flatMap(response -> {
                final List<CharacterDto> characterDtoList = response.getResponse();
                final List<Character> characters = new ArrayList<>();
                
                for (CharacterDto dto : characterDtoList) {
                    characters.add(CharacterMapper.dtoToCharacter(dto));
                }
                
                return Single.just(characters);
                
            });
    }
    
    
    private Single<ApiResponse<List<CharacterDto>>> getCharactersWithQuery(@NonNull CharacterQuery characterQuery) {
        return restManager.getCharactersWithQuery(characterQuery.toMap());
    }
    
}
