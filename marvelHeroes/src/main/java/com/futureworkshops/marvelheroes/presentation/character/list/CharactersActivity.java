/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list;

import android.os.Bundle;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.domain.model.Character;
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity;

import java.util.List;

import butterknife.ButterKnife;

public class CharactersActivity extends BaseActivity implements CharactersListContract.View {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        // TODO: 04/04/2018 request data
    }
    
    @Override
    public void onCharactersLoaded(List<Character> characters) {
    
    }
    
    @Override
    public void showNoInternetCOnnection() {
    
    }
    
    @Override
    public void hideNoInternetConnection() {
    
    }
    
    @Override
    public void showCharacterEmptyView() {
    
    }
    
    @Override
    public void hideCharacterEmptyView() {
    
    }
    
    @Override
    public void showRefreshing() {
    
    }
    
    @Override
    public void hideRefreshing() {
    
    }
}
