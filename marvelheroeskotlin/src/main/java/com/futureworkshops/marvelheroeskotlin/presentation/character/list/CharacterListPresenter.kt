/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.list

import com.futureworkshops.marvelheroeskotlin.domain.navigator.Navigator
import com.futureworkshops.marvelheroeskotlin.presentation.common.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class CharacterListPresenter
@Inject
constructor(private val characterListInteractor: CharacterListInteractor,
            private val navigator: Navigator,
            compositeDisposable: CompositeDisposable) : BasePresenter<CharacterListView>(compositeDisposable) {
    
    
    fun loadAvengerCharacters() {
        view?.showRefreshing()
        characterListInteractor.loadAvengersCharacters()
                .subscribe { characters, throwable ->
                    view?.hideRefreshing()
                    if (throwable != null) {
                        Timber.e(throwable)
                        throwable.message?.let { view?.showError(it) }
                    } else {
                        view?.onCharactersLoaded(characters)
                    }
                }
    }
    
    fun searchCharacterByName(query: String) {
        
    }
    
    fun showCharacterDetaislScreen(characterId: Int) {
        
    }
}