/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.domain.image.ImageLoader;
import com.futureworkshops.marvelheroes.domain.model.Character;
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListPresenter;
import com.futureworkshops.marvelheroes.presentation.character.list.CharactersListContract.View;
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListAdapter.CharacterClickListener;
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class CharactersActivity extends BaseActivity implements View, CharacterClickListener {
    
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    
    @BindView(R.id.characterRv)
    RecyclerView characterRv;
    
    @Inject
    CharacterListPresenter characterListPresenter;
    
    @Inject
    ImageLoader imageLoader;
    
    private CharacterListAdapter characterListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);
        
        initSwipeRefreshLayout();
        
        initRecyclerView();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        //  request data
        loadAvengersCharacters();
    }
    
    @Override
    public void onCharactersLoaded(List<Character> characters) {
        characterListAdapter.setItems(characters);
    }
    
    @Override
    public void showNoInternetConnection() {
    
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
        swipeRefreshLayout.setRefreshing(true);
    }
    
    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }
    
    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onCharacterClicked(Character character) {
    
    }
    
    private void initRecyclerView() {
        characterListAdapter = new CharacterListAdapter(this, imageLoader);
        characterListAdapter.setCharacterClickListener(this);
        
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        
        characterRv.setLayoutManager(layoutManager);
        characterRv.setAdapter(characterListAdapter);
    }
    
    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            loadAvengersCharacters();
        });
    }
    
    private void loadAvengersCharacters() {
        characterListPresenter.loadAvengerCharacters();
    }
}
