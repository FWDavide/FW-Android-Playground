/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.list

import android.support.v7.widget.RecyclerView
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.futureworkshops.marvelheroeskotlin.R
import com.futureworkshops.marvelheroeskotlin.domain.model.MarvelCharacter
import com.futureworkshops.marvelheroeskotlin.extension.loadThumbnail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_character.*

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
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

class CharacterViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    
    fun bind(character: MarvelCharacter, characterClickListener: CharacterClickListener) {
        characterName.text = character.name
        
        character.thumbnailUrl?.let {
            characterImage.loadThumbnail(it)
        }
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