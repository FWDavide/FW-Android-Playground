/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list;

import android.support.annotation.NonNull;

import com.futureworkshops.marvelheroes.domain.navigator.Navigator;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;


public class CharacterListPresenter implements CharactersListContract.Presenter {
    
    private Navigator navigator;
    private CharacterListInteractor characterListInteractor;
    private final CharactersListContract.View view;
    
    @Inject
    public CharacterListPresenter(CharacterListInteractor interactor, Navigator navigator,
                                  CharactersListContract.View view) {
        this.characterListInteractor = interactor;
        this.navigator = navigator;
        this.view = view;
    }
    
    
    @Override
    public void loadAvengerCharacters() {
        view.showRefreshing();
        characterListInteractor.loadAvengersCharacters()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe((characters, throwable) -> {
                view.hideRefreshing();
                if (throwable != null) {
                    Timber.e(throwable);
                    view.showError(throwable.getMessage());
                } else {
                    view.onCharactersLoaded(characters);
                }
            });
    }
    
    @Override
    public void searchCharacterByName(@NonNull String query) {
    
    }
    
    @Override
    public void showCharacterDetaislScreen(int characterId) {
    
    }
}
