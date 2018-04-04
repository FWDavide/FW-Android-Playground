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
import com.futureworkshops.marvelheroes.domain.image.ImageLoader;
import com.futureworkshops.marvelheroes.domain.model.Character;
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListAdapter.CharacterViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stelian on 04/04/2018.
 */

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    
    /**
     * Specialised interface to listen for Character click events.
     */
    public interface CharacterClickListener {
        
        void onCharacterClicked(Character character);
    }
    
    
    private CharacterClickListener characterClickListener;
    private List<Character> items;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    
    public CharacterListAdapter(@NonNull Context context, ImageLoader loader) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
        this.imageLoader = loader;
    }
    
    void setItems(List<Character> items) {
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
        final Character item = items.get(position);
        bindCharacter(holder, item);
    }
    
    @Override
    public int getItemCount() {
        return items.size();
    }
    
    private void bindCharacter(CharacterViewHolder viewHolder, Character character) {
        viewHolder.name.setText(character.getName());
        
        // load character thumbnail
        imageLoader.loadThumbnail(viewHolder.image, character.getThumbnailUrl());
        
        viewHolder.itemView.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
            if (characterClickListener != null) {
                characterClickListener.onCharacterClicked(character);
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
