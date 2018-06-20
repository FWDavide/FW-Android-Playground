/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.detail

import com.futureworkshops.marvelheroeskotlin.domain.AppComponent
import com.futureworkshops.marvelheroeskotlin.domain.FragmentScope
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
class CharacterDetailsModule {
    
    @FragmentScope
    @Provides
    fun providesInteractor(newsRepository: MarvelCharacterRepository): CharacterDetailInteractor {
        return CharacterDetailInteractor(newsRepository)
    }
    
    @FragmentScope
    @Provides
    fun providesPresenter(interactor: CharacterDetailInteractor, compositeDisposable: CompositeDisposable): CharacterDetailPresenter {
        return CharacterDetailPresenter(interactor, compositeDisposable)
    }
}

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(CharacterDetailsModule::class)])
interface CharacterDetailsComponent {
    
    fun inject(fragment: CharacterDetailFragment)
}