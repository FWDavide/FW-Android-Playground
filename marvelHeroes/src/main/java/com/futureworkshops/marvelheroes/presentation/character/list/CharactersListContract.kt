/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list

import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter

/**
 * Contract between the View and the Presenter.
 */

class CharactersListContract {
    
    interface View {
        
        fun onCharactersLoaded(characters: List<MarvelCharacter>)
        
        fun showNoInternetConnection()
        
        fun hideNoInternetConnection()
        
        fun showCharacterEmptyView()
        
        fun hideCharacterEmptyView()
        
        fun showRefreshing()
        
        fun hideRefreshing()
        
        fun showError(message: String)
    }
    
    interface Presenter {
        
        fun loadAvengerCharacters()
        
        fun searchCharacterByName(query: String)
        
        fun showCharacterDetaislScreen(characterId: Int)
        
    }
}
