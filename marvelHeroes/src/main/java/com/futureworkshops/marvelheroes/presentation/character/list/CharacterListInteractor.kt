/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list

import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter
import com.futureworkshops.marvelheroes.domain.repositories.character.MarvelCharacterRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by stelian on 04/04/2018.
 */

class CharacterListInteractor
@Inject
constructor(private val marvelCharacterRepository: MarvelCharacterRepository) {
    
    internal fun loadAvengersCharacters(): Single<List<MarvelCharacter>> {
        return marvelCharacterRepository.avengersCharacters
    }
}
