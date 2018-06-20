/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.list

import com.futureworkshops.marvelheroeskotlin.domain.model.MarvelCharacter
import com.futureworkshops.marvelheroeskotlin.domain.repository.MarvelCharacterRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class CharacterListInteractor
@Inject
constructor(private val marvelCharacterRepository: MarvelCharacterRepository) {
    
    internal fun loadAvengersCharacters(): Single<List<MarvelCharacter>> {
        return marvelCharacterRepository.avengersCharacters
    }
}