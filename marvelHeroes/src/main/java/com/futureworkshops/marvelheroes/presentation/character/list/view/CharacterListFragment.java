/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter;
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailFragment;
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListPresenter;
import com.futureworkshops.marvelheroes.presentation.character.list.CharactersListContract;
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListAdapter.CharacterClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by stelian on 05/04/2018.
 */

public class CharacterListFragment extends Fragment implements CharactersListContract.View, CharacterClickListener {
    
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.characterRv)
    RecyclerView characterRv;
    @Inject
    CharacterListPresenter characterListPresenter;
    private CharacterListAdapter characterListAdapter;
    private CharacterDetailListener characterDetailListener;
    
    public static CharacterListFragment newInstance() {
        return new CharacterListFragment();
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_character_list, container, false);
        ButterKnife.bind(this, view);
        
        initSwipeRefreshLayout();
        
        initRecyclerView();
        
        return view;
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
        loadAvengersCharacters();
    }
    
    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            loadAvengersCharacters();
        });
    }
    
    private void initRecyclerView() {
        characterListAdapter = new CharacterListAdapter(getContext());
        characterListAdapter.setCharacterClickListener(this);
        
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        
        characterRv.setLayoutManager(layoutManager);
        characterRv.setAdapter(characterListAdapter);
    }
    
    private void loadAvengersCharacters() {
        characterListPresenter.loadAvengerCharacters();
    }
    
    public void setCharacterDetailListener(CharacterDetailListener characterDetailListener) {
        this.characterDetailListener = characterDetailListener;
    }
    
    @Override
    public void onCharactersLoaded(List<MarvelCharacter> characters) {
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onCharacterClicked(MarvelCharacter character, ImageView listImageView) {
        if (characterDetailListener != null) {
            characterDetailListener.onShowCharacterDetail(character, listImageView);
        }
        
        final String transitionName = ViewCompat.getTransitionName(listImageView);
        
        CharacterDetailFragment characterDetailFragment = CharacterDetailFragment.newInstance(character);
        getFragmentManager()
            .beginTransaction()
            .addSharedElement(listImageView, transitionName)
            .addToBackStack(null)
            .add(R.id.fragmentContainer, characterDetailFragment)
            .commit();
        
    }
    
    public interface CharacterDetailListener {
        
        void onShowCharacterDetail(@NonNull MarvelCharacter character, @NonNull ImageView thumbnail);
        
    }
}