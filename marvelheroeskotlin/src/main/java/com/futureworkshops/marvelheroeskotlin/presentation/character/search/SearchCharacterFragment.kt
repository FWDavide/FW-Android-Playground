/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.futureworkshops.marvelheroeskotlin.R

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
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