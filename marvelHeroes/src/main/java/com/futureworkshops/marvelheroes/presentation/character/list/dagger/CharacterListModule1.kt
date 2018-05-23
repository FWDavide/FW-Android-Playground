/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.dagger

import com.futureworkshops.marvelheroes.domain.navigator.Navigator
import com.futureworkshops.marvelheroes.domain.repositories.character.MarvelCharacterRepository
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListInteractor
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListPresenter
import com.futureworkshops.marvelheroes.presentation.character.list.CharactersListContract
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListFragment

import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by stelian on 04/04/2018.
 */
@Module
abstract class CharacterListModule {
    
    @Binds
    abstract fun providesContractView(fragment: CharacterListFragment): CharactersListContract.View
    
    @Provides
    fun providesNavigator(fragment: CharacterListFragment): Navigator {
        return Navigator(fragment.activity!!)
    }
    
    @Provides
    fun providesInteractor(newsRepository: MarvelCharacterRepository): CharacterListInteractor {
        return CharacterListInteractor(newsRepository)
    }
    
    @Provides
    fun providesPresenter(interactor: CharacterListInteractor, navigator: Navigator,
                                   view: CharactersListContract.View): CharacterListPresenter {
        return CharacterListPresenter(interactor, navigator, view)
    }
}
