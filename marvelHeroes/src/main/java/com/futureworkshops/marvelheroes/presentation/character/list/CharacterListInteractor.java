/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list;

import com.futureworkshops.marvelheroes.domain.repositories.character.MarvelCharacterRepository;

import javax.inject.Inject;

/**
 * Created by stelian on 04/04/2018.
 */

public class CharacterListInteractor {
    
    private MarvelCharacterRepository marvelCharacterRepository;
    
    @Inject
    public CharacterListInteractor(MarvelCharacterRepository marvelCharacterRepository) {
        this.marvelCharacterRepository = marvelCharacterRepository;
    }
}
