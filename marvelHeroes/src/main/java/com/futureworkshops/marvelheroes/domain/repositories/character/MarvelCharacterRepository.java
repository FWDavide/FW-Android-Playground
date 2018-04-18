/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.repositories.character;

import android.support.annotation.NonNull;
import android.util.Log;

import com.futureworkshops.marvelheroes.data.network.RestManager;
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
    
    class Token {
        String token;
    }
    
    @Inject
    public MarvelCharacterRepository(RestManager restManager) {
        this.restManager = restManager;
    }
    
    public Single<List<Character>> getAvengersCharacters() {
        CharacterQuery filter = new CharacterQuery.Builder()
            .serie(AVENGERS__SERIES_ID)
            .orderByName(true)
            .limit(50)
            .build();
        
        return getCharactersWithQuery(filter);
        
    }
    
    public Single<List<Character>> getCharacterDetails(int characterId) {
        return restManager.getCharacterDetails(String.valueOf(characterId))
            .flatMap(response -> Single.just(mapDtoToModel(response)));
    }
    
    public Single<List<Character>> getAllCharacters(int offset, int limit) {
        CharacterQuery filter = new CharacterQuery.Builder()
            .offset(offset)
            .limit(limit)
            .build();
        
        
        
        
        return getCharactersWithQuery(filter);
    }
    
    
    private Single<List<Character>> getCharactersWithQuery(@NonNull CharacterQuery characterQuery) {
        return restManager.getCharactersWithQuery(characterQuery.toMap())
            .flatMap(response -> Single.just(mapDtoToModel(response)));
    }
    
    @NonNull
    private List<Character> mapDtoToModel(List<CharacterDto> characterDtos) {
        final List<Character> characters = new ArrayList<>();
        
        for (CharacterDto dto : characterDtos) {
            characters.add(CharacterMapper.dtoToCharacter(dto));
        }
        return characters;
    }
    
}
