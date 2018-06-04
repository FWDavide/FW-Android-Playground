/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.detail.dagger

import com.futureworkshops.marvelheroes.domain.dagger.AppComponent
import com.futureworkshops.marvelheroes.domain.dagger.FragmentScope
import com.futureworkshops.marvelheroes.domain.repositories.character.MarvelCharacterRepository
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailFragment
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailInteractor
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailPresenter
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
