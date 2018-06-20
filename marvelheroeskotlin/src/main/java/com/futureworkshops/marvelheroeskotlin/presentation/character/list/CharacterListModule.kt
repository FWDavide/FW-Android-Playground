/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.list

import android.app.Activity
import com.futureworkshops.marvelheroeskotlin.domain.AppComponent
import com.futureworkshops.marvelheroeskotlin.domain.FragmentScope
import com.futureworkshops.marvelheroeskotlin.domain.navigator.Navigator
import com.futureworkshops.marvelheroeskotlin.domain.repository.MarvelCharacterRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by dimitrios on 20/06/2018.
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