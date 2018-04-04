/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list;

import android.support.annotation.NonNull;

import com.futureworkshops.marvelheroes.domain.model.Character;

import java.util.List;

/**
 * Contract between the View and the Presenter.
 */

public class CharactersListContract {
    
    public interface View {
    
        void onCharactersLoaded(List<Character> characters);
        
        void showNoInternetCOnnection();
        
        void hideNoInternetConnection();
        
        void showCharacterEmptyView();
        
        void hideCharacterEmptyView();
        
        void showRefreshing();
        
        void hideRefreshing();
    
    }
    
    public interface Presenter {
        
        void loadAvengerCharacters();
        
        void searchCharacterByName(@NonNull String query);
        
        void showCharacterDetaislScreen(int characterId);
        
    }
}
