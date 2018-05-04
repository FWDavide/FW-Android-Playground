/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list;

import android.support.annotation.NonNull;

import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter;

import java.util.List;

/**
 * Contract between the View and the Presenter.
 */

public class CharactersListContract {
    
    public interface View {
    
        void onCharactersLoaded(List<MarvelCharacter> characters);
        
        void showNoInternetConnection();
        
        void hideNoInternetConnection();
        
        void showCharacterEmptyView();
        
        void hideCharacterEmptyView();
        
        void showRefreshing();
        
        void hideRefreshing();
    
        void showError(String message);
    }
    
    public interface Presenter {
        
        void loadAvengerCharacters();
        
        void searchCharacterByName(@NonNull String query);
        
        void showCharacterDetaislScreen(int characterId);
        
    }
}
