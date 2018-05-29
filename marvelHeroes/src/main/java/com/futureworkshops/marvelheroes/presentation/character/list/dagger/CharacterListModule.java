/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.dagger;

import com.futureworkshops.marvelheroes.data.network.rx.scheduler.SchedulersProvider;
import com.futureworkshops.marvelheroes.domain.navigator.Navigator;
import com.futureworkshops.marvelheroes.domain.repositories.character.MarvelCharacterRepository;
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListInteractor;
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListPresenter;
import com.futureworkshops.marvelheroes.presentation.character.list.CharactersListContract;
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by stelian on 04/04/2018.
 */
@Module()
public abstract class CharacterListModule {
    
    @Provides
    static Navigator providesNavigator(CharacterListFragment fragment) {
        return new Navigator(fragment.getActivity());
    }
    
    @Binds
    abstract CharactersListContract.View providesContractView(CharacterListFragment fragment);
    
    @Provides
    static CharacterListInteractor providesInteractor(MarvelCharacterRepository newsRepository) {
        return new CharacterListInteractor(newsRepository);
    }
    
    @Provides
    static CharacterListPresenter providesPresenter(CharacterListInteractor interactor, Navigator navigator,
                                                    CharactersListContract.View view, SchedulersProvider schedulersProvider) {
        return new CharacterListPresenter(interactor, navigator, view, schedulersProvider);
    }
}
