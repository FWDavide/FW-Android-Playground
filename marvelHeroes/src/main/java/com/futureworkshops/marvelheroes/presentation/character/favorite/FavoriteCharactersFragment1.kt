/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.futureworkshops.marvelheroes.R

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteCharactersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteCharactersFragment : Fragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_characters, container, false)
    }
    
    companion object {
        
        fun newInstance(): FavoriteCharactersFragment {
            return FavoriteCharactersFragment()
        }
    }
    
}