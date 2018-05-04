/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list;

import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter;
import com.futureworkshops.marvelheroes.domain.repositories.character.MarvelCharacterRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by stelian on 04/04/2018.
 */

public class CharacterListInteractor {
    
    private MarvelCharacterRepository marvelCharacterRepository;
    
    @Inject
    public CharacterListInteractor(MarvelCharacterRepository marvelCharacterRepository) {
        this.marvelCharacterRepository = marvelCharacterRepository;
    }
    
    Single<List<MarvelCharacter>> loadAvengersCharacters() {
        return marvelCharacterRepository.getAvengersCharacters();
    }
}
