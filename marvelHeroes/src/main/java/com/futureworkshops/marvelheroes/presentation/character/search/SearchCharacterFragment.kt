/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.search


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.futureworkshops.marvelheroes.R

/**
 * A simple [Fragment] subclass.
 * Use the [SearchCharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchCharacterFragment : Fragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_character, container, false)
    }
    
    companion object {
        
        fun newInstance(): SearchCharacterFragment {
            return SearchCharacterFragment()
        }
    }
    
}