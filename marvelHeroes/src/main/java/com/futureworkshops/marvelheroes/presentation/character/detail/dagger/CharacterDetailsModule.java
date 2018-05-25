/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.detail.dagger;

import com.futureworkshops.marvelheroes.domain.repositories.character.MarvelCharacterRepository;
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailContract;
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailFragment;
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailInteractor;
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by stelian on 04/04/2018.
 */
@Module()
public abstract class CharacterDetailsModule {
    
    @Binds
    abstract CharacterDetailContract.View providesContractView(CharacterDetailFragment fragment);
    
    @Provides
    static CharacterDetailInteractor providesInteractor(MarvelCharacterRepository newsRepository) {
        return new CharacterDetailInteractor(newsRepository);
    }
    
    @Provides
    static CharacterDetailPresenter providesPresenter(CharacterDetailInteractor interactor,
                                                      CharacterDetailContract.View view) {
        return new CharacterDetailPresenter(interactor, view);
    }
}
