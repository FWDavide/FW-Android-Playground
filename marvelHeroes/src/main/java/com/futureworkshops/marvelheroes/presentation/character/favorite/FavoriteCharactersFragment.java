/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.favorite;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureworkshops.marvelheroes.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteCharactersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteCharactersFragment extends Fragment {
    
    
    public static FavoriteCharactersFragment newInstance() {
        
        return new FavoriteCharactersFragment();
    }
    
    public FavoriteCharactersFragment() {
        // Required empty public constructor
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_favorite_characters, container, false);
       
        ButterKnife.bind(this, view);
        
        return view;
    }
    
}
