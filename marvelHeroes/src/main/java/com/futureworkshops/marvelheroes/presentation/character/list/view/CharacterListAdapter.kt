/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.view

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter
import java.util.*

/**
 * Created by stelian on 04/04/2018.
 */

class CharacterListAdapter() : RecyclerView.Adapter<CharacterViewHolder>() {
    
    private var characterClickListener: CharacterClickListener? = null
    private val items: MutableList<MarvelCharacter>
    
    interface CharacterClickListener {
        
        fun onCharacterClicked(character: MarvelCharacter, listImageView: ImageView?)
    }
    
    init {
        this.items = ArrayList()
    }
    
    internal fun setItems(items: List<MarvelCharacter>) {
        this.items.clear()
        this.items.addAll(items)
        
        notifyDataSetChanged()
    }
    
    internal fun setCharacterClickListener(listener: CharacterClickListener) {
        this.characterClickListener = listener
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.newInstance(parent)
    }
    
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = items[position]
        bindCharacter(holder, item)
    }
    
    override fun getItemCount(): Int {
        return items.size
    }
    
    private fun bindCharacter(viewHolder: CharacterViewHolder, character: MarvelCharacter) {
//        viewHolder.name!!.text = character.name
//
//        viewHolder.bind(character)
//        //TODO replace below with extension fun when we refactor the View Layer to Kotlin.
//        GlideApp.with(viewHolder.image!!)
//                .load(character.thumbnailUrl)
//                .placeholder(R.drawable.default_thumbnail_placeholder)
//                .centerCrop()
//                .into(viewHolder.image!!)
//
//        //        ViewCompat.setTransitionName(viewHolder.image, "transition23234");
//
//        viewHolder.itemView.setOnClickListener { v ->
//            v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
//            if (characterClickListener != null) {
//                characterClickListener!!.onCharacterClicked(character, viewHolder.image)
//            }
//        }
    }
    
}

class CharacterViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind(character: MarvelCharacter) {
    
    }
    
    companion object {
        fun newInstance(viewgroup: ViewGroup): CharacterViewHolder {
            return CharacterViewHolder(LayoutInflater.from(viewgroup.context).inflate(R.layout.list_item_character, viewgroup, false))
        }
    }
//
//    @BindView(R.id.characterImage)
//    var image: ImageView? = null
//
//    @BindView(R.id.characterName)
//    var name: TextView? = null
}
