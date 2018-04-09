/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.TransitionInflater;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.domain.image.ImageLoader;
import com.futureworkshops.marvelheroes.domain.model.Character;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CharacterDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterDetailFragment extends Fragment implements CharacterDetailContract.View, RequestListener {
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CHARACTER = "param1";
    private static final String ARG_TRANSITION_NAME = "param2";
    
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    
    @BindView(R.id.image)
    ImageView imageView;
    
    @BindView(R.id.title)
    TextView dtitleTv;
    
    @BindView(R.id.description)
    TextView descriptionTv;
    
    @BindView(R.id.fab)
    FloatingActionButton favoriteFab;
    
    @Inject
    ImageLoader imageLoader;
    
    
    private Character character;
    
    private String transitionName;
    
    
    public CharacterDetailFragment() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param character Parameter 1.
     * @param transitionName Parameter 2.
     * @return A new instance of fragment CharacterDetailFragment.
     */
    public static CharacterDetailFragment newInstance(Character character, String transitionName) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHARACTER, character);
        args.putString(ARG_TRANSITION_NAME, transitionName);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        
        // we target SDK > 21 so we don't need to check anything
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_character_detail, container, false);
        ButterKnife.bind(this, view);
        
        return view;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        if (getArguments() != null) {
            character = (Character) getArguments().getSerializable(ARG_CHARACTER);
            transitionName = getArguments().getString(ARG_TRANSITION_NAME);
            
            imageView.setTransitionName(transitionName);
            
            imageLoader.loadLandscapeImage(imageView, character.getLandscapeImageUrl(), this);
            
        }
    }
    
    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
        startPostponedEnterTransition();
        return false;
    }
    
    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
        startPostponedEnterTransition();
        return false;
    }
}
