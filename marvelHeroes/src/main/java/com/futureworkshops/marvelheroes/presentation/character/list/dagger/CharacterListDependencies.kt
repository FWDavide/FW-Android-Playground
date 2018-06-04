/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.dagger

import android.app.Activity
import com.futureworkshops.marvelheroes.domain.dagger.AppComponent
import com.futureworkshops.marvelheroes.domain.dagger.FragmentScope
import com.futureworkshops.marvelheroes.domain.navigator.Navigator
import com.futureworkshops.marvelheroes.domain.repositories.character.MarvelCharacterRepository
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListInteractor
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListPresenter
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by dimitrios on 25/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */
@Module
class CharacterListModule(val fragment: CharacterListFragment) {
    
    @FragmentScope
    @Provides
    fun providesNavigator(): Navigator {
        return Navigator(fragment.activity as Activity)
    }
    
    @FragmentScope
    @Provides
    internal fun providesInteractor(newsRepository: MarvelCharacterRepository): CharacterListInteractor {
        return CharacterListInteractor(newsRepository)
    }
    
    @FragmentScope
    @Provides
    internal fun providesPresenter(interactor: CharacterListInteractor, navigator: Navigator, compositeDisposable: CompositeDisposable): CharacterListPresenter {
        return CharacterListPresenter(interactor, navigator, compositeDisposable)
    }
    
}

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [CharacterListModule::class])
interface CharacterListComponent {
    
    fun inject(fragment: CharacterListFragment)
    
}