/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.domain.image.GlideApp;
import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter;
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListAdapter.CharacterViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stelian on 04/04/2018.
 */

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    
    public interface CharacterClickListener {
        
        void onCharacterClicked(MarvelCharacter character, ImageView listImageView);
    }
    
    
    private CharacterClickListener characterClickListener;
    private List<MarvelCharacter> items;
    private LayoutInflater layoutInflater;
    
    public CharacterListAdapter(@NonNull Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
    }
    
    void setItems(List<MarvelCharacter> items) {
        this.items.clear();
        this.items.addAll(items);
        
        notifyDataSetChanged();
    }
    
    void setCharacterClickListener(CharacterClickListener listener) {
        this.characterClickListener = listener;
    }
    
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CharacterViewHolder(layoutInflater.inflate(R.layout.list_item_character, parent, false));
    }
    
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        final MarvelCharacter item = items.get(position);
        bindCharacter(holder, item);
    }
    
    @Override
    public int getItemCount() {
        return items.size();
    }
    
    private void bindCharacter(CharacterViewHolder viewHolder, MarvelCharacter character) {
        viewHolder.name.setText(character.getName());
        
        // load character thumbnail
        //TODO replace below with extension fun when we refactor the View Layer to Kotlin.
        GlideApp.with(viewHolder.image)
            .load(character.getThumbnailUrl())
            .placeholder(R.drawable.default_thumbnail_placeholder)
            .centerCrop()
            .into(viewHolder.image);

//        ViewCompat.setTransitionName(viewHolder.image, "transition23234");
        
        viewHolder.itemView.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
            if (characterClickListener != null) {
                characterClickListener.onCharacterClicked(character, viewHolder.image);
            }
        });
    }
    
    
    
    class CharacterViewHolder extends ViewHolder {
        
        @BindView(R.id.characterImage)
        ImageView image;
        
        @BindView(R.id.characterName)
        TextView name;
        
        public CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            
        }
    }
}
