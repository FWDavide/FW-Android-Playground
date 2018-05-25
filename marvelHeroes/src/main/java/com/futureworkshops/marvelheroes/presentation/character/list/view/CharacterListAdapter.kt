/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.view

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.domain.image.GlideApp
import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_character.*
import java.util.*

/**
 * Created by stelian on 04/04/2018.
 */

interface CharacterClickListener {
    
    fun onCharacterClicked(character: MarvelCharacter, listImageView: ImageView)
}

class CharacterListAdapter(private val characterListener: CharacterClickListener) : RecyclerView.Adapter<CharacterViewHolder>() {
    
    private val items: MutableList<MarvelCharacter> = ArrayList()
    
    internal fun setItems(items: List<MarvelCharacter>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.newInstance(parent)
    }
    
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position], characterListener)
    }
    
    override fun getItemCount(): Int {
        return items.size
    }
    
}

class CharacterViewHolder(override val containerView: View?) : ViewHolder(containerView), LayoutContainer {
    
    fun bind(character: MarvelCharacter, characterClickListener: CharacterClickListener) {
        characterName.text = character.name
        
        // load character thumbnail
        //TODO replace below with extension fun when we refactor the View Layer to Kotlin.
        GlideApp.with(itemView)
                .load(character.thumbnailUrl)
                .placeholder(R.drawable.default_thumbnail_placeholder)
                .centerCrop()
                .into(characterImage)
        
        //        ViewCompat.setTransitionName(viewHolder.image, "transition23234");
        
        itemView.setOnClickListener { v ->
            v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            characterClickListener.onCharacterClicked(character, characterImage)
        }
    }
    
    companion object {
        
        fun newInstance(viewGroup: ViewGroup): CharacterViewHolder {
            return CharacterViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_character, viewGroup, false))
        }
    }
}