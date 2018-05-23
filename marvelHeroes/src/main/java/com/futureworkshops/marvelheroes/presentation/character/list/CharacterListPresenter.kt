/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list

import com.futureworkshops.marvelheroes.domain.navigator.Navigator
import timber.log.Timber
import javax.inject.Inject


class CharacterListPresenter
@Inject
constructor(private val characterListInteractor: CharacterListInteractor,
            private val navigator: Navigator,
            private val view: CharactersListContract.View) : CharactersListContract.Presenter {
    
    
    override fun loadAvengerCharacters() {
        view.showRefreshing()
        characterListInteractor.loadAvengersCharacters()
                .subscribe { characters, throwable ->
                    view.hideRefreshing()
                    if (throwable != null) {
                        Timber.e(throwable)
                        throwable.message?.let { view.showError(it) }
                    } else {
                        view.onCharactersLoaded(characters)
                    }
                }
    }
    
    override fun searchCharacterByName(query: String) {
    
    }
    
    override fun showCharacterDetaislScreen(characterId: Int) {
    
    }
}
