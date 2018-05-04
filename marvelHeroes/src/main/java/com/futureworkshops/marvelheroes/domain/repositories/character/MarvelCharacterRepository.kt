/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.repositories.character

import com.futureworkshops.marvelheroes.data.network.RestManager
import com.futureworkshops.marvelheroes.data.network.dto.CharacterDto
import com.futureworkshops.marvelheroes.domain.model.Character
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

/**
 * Created by stelian on 03/04/2018.
 */

class MarvelCharacterRepository @Inject
constructor(private val restManager: RestManager) {
    
    val avengersCharacters: Single<List<Character>>
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
    
    fun getCharacterDetails(characterId: Int): Single<List<Character>> {
        return restManager.getCharacterDetails(characterId.toString())
                .flatMap { response -> Single.just(mapDtoToModel(response)) }
    }
    
    fun getAllCharacters(offset: Int, limit: Int): Single<List<Character>> {
        val filter = CharacterQuery.Builder()
                .offset(offset)
                .limit(limit)
                .build()
        return getCharactersWithQuery(filter)
    }
    
    
    private fun getCharactersWithQuery(characterQuery: CharacterQuery): Single<List<Character>> {
        return restManager.getCharactersWithQuery(characterQuery.toMap())
                .flatMap { response -> Single.just(mapDtoToModel(response)) }
    }
    
    private fun mapDtoToModel(characterDtos: List<CharacterDto>): List<Character> {
        val characters = ArrayList<Character>()
        
        for (dto in characterDtos) {
            characters.add(CharacterMapper.dtoToCharacter(dto))
        }
        return characters
    }
    
    companion object {
        const val AVENGERS__SERIES_ID = 3621
    }
    
}
