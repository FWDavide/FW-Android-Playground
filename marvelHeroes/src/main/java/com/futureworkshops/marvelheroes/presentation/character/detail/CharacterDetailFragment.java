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
import android.support.v7.app.AppCompatActivity;
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
import com.futureworkshops.marvelheroes.domain.image.GlideApp;
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
    
    private static final String ARG_CHARACTER = "character";
    
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    
    @BindView(R.id.image)
    ImageView imageView;
    
    @BindView(R.id.title)
    TextView titleTv;
    
    @BindView(R.id.description)
    TextView descriptionTv;

    @BindView(R.id.fab)
    FloatingActionButton favoriteFab;
//
//    @Inject
//    ImageLoader imageLoader;
    
    
    private Character character;
    
    public CharacterDetailFragment() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param character Parameter 1.
     * @return A new instance of fragment CharacterDetailFragment.
     */
    public static CharacterDetailFragment newInstance(Character character) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHARACTER, character);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        
        postponeEnterTransition();
    
        setSharedElementEnterTransition(
            TransitionInflater.from(getContext())
                .inflateTransition(android.R.transition.move));
        // we target SDK > 21 so we don't need to check anything
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      return  inflater.inflate(R.layout.fragment_character_detail, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    
        ButterKnife.bind(this, view);
        
        if (getArguments() != null) {
            character = (Character) getArguments().getSerializable(ARG_CHARACTER);
            
            initToolbars();
            
//            imageView.setTransitionName("transition23234");
//
//            imageLoader.loadLandscapeImage(imageView, character.getLandscapeImageUrl(), this);
    
    
            GlideApp.with(imageView)
                .load(character.getImageUrl())
                .fitCenter()
                .dontAnimate()
                .listener(this)
                .placeholder(R.drawable.default_thumbnail_placeholder)
                .into(imageView);
            
            titleTv.setText(character.getName());
            descriptionTv.setText(character.getDescription());
            
        }
    }
    
    private void initToolbars() {
        final AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(false);

        collapsingToolbarLayout.setTitle(character.getName());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
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
