/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.domain.repository

import com.futureworkshops.marvelheroeskotlin.data.dto.MarvelCharacterDto
import com.futureworkshops.marvelheroeskotlin.data.network.RestManager
import com.futureworkshops.marvelheroeskotlin.domain.model.MarvelCharacter
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class MarvelCharacterRepository
@Inject
constructor(private val restManager: RestManager) {
    
    val avengersCharacters: Single<List<MarvelCharacter>>
        get() {
            val filter = CharacterQuery.Builder()
                    .serie(AVENGERS__SERIES_ID)
                    .orderByName(true)
                    .limit(50)
                    .build()
            
            return getCharactersWithQuery(filter)
            
        }
    
    internal inner class Token {
        var token: String? = null
    }
    
    fun getCharacterDetails(characterId: Int): Single<List<MarvelCharacter>> {
        return restManager.getCharacterDetails(characterId.toString())
                .flatMap { response -> Single.just(mapDtoToModel(response)) }
    }
    
    fun getAllCharacters(offset: Int, limit: Int): Single<List<MarvelCharacter>> {
        val filter = CharacterQuery.Builder()
                .offset(offset)
                .limit(limit)
                .build()
        return getCharactersWithQuery(filter)
    }
    
    
    private fun getCharactersWithQuery(characterQuery: CharacterQuery): Single<List<MarvelCharacter>> {
        return restManager.getCharactersWithQuery(characterQuery.toMap())
                .flatMap { response -> Single.just(mapDtoToModel(response)) }
    }
    
    private fun mapDtoToModel(characterDtos: List<MarvelCharacterDto>): List<MarvelCharacter> {
        val characters = ArrayList<MarvelCharacter>()
        
        for (dto in characterDtos) {
            characters.add(CharacterMapper.dtoToCharacter(dto))
        }
        return characters
    }
    
    companion object {
        const val AVENGERS__SERIES_ID = 3621
    }
    
}
