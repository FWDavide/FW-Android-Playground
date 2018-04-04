/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list;

import android.support.annotation.NonNull;

import com.futureworkshops.marvelheroes.domain.navigator.Navigator;

import javax.inject.Inject;


public class CharacterListPresenter implements CharactersListContract.Presenter {
    
    private Navigator navigator;
    private CharacterListInteractor characterListInteractor;
    private final CharactersListContract.View viewReference;
    
    @Inject
    public CharacterListPresenter(CharacterListInteractor interactor, Navigator navigator,
                                  CharactersListContract.View view) {
        this.characterListInteractor = interactor;
        this.navigator = navigator;
        this.viewReference = view;
    }
    
    
    @Override
    public void loadAvengerCharacters() {
    
    }
    
    @Override
    public void searchCharacterByName(@NonNull String query) {
    
    }
    
    @Override
    public void showCharacterDetaislScreen(int characterId) {
    
    }
}
