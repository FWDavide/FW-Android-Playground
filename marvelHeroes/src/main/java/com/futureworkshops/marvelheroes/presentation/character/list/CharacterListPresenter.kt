/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list

import com.futureworkshops.marvelheroes.domain.navigator.Navigator
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListView
import com.futureworkshops.marvelheroes.presentation.common.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject


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
