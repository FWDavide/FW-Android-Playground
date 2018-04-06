/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureworkshops.marvelheroes.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchCharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchCharacterFragment extends Fragment {
    
    
    public static SearchCharacterFragment newInstance() {
        return new SearchCharacterFragment();
    }
    
    public SearchCharacterFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_search_character, container, false);
        
        ButterKnife.bind(this, view);
        
        return view;
    }
    
}
