/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.MenuItem
import android.widget.ImageView
import com.futureworkshops.marvelheroeskotlin.R
import com.futureworkshops.marvelheroeskotlin.domain.model.MarvelCharacter
import com.futureworkshops.marvelheroeskotlin.presentation.FavoriteCharactersFragment
import com.futureworkshops.marvelheroeskotlin.presentation.character.detail.CharacterDetailFragment
import com.futureworkshops.marvelheroeskotlin.presentation.character.list.CharacterDetailListener
import com.futureworkshops.marvelheroeskotlin.presentation.character.list.CharacterListFragment
import com.futureworkshops.marvelheroeskotlin.presentation.common.BaseActivity
import kotlinx.android.synthetic.main.activity_characters.*
import timber.log.Timber

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class CharactersActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener, CharacterDetailListener {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        setContentView(R.layout.activity_characters)
        initBottomNavigation()
    }
    
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_show_characters -> {
                showCharactersFragment()
            }
            R.id.action_show_favorites -> {
                showFavoritesFragment()
            }
            R.id.action_search -> {
                showSearchFragment()
            }
        }
        return true
    }
    
    override fun onNavigationItemReselected(item: MenuItem) {
        when (item.itemId) {
            R.id.action_show_characters ->
                // TODO: 09/04/2018 reset character list fragment to original state
                Timber.d("reselect characters")
            R.id.action_show_favorites -> Timber.d("reselect favorites")
            R.id.action_search -> Timber.d("reselect search")
        }
    }
    
    private fun showSearchFragment() {
        replaceFragment(CharacterListFragment.newInstance(), "SEARCH_ROOT")
    }
    
    private fun showFavoritesFragment() {
        replaceFragment(FavoriteCharactersFragment.newInstance(), "FAVORITES_ROOT")
    }
    
    private fun showCharactersFragment() {
        replaceFragment(CharacterListFragment.newInstance(), "CHARACTERS_ROOT")
    }
    
    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, tag)
                .commit()
    }
    
    private fun initBottomNavigation() {
        val menu = bottomNavigation.menu
        for (i in 0 until menu.size()) {
            val item = menu.getItem(i)
            
            if (item.itemId == R.id.action_show_characters) {
                item.isChecked = true
                bottomNavigation.selectedItemId = item.itemId
                showCharactersFragment()
            }
        }
        
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        bottomNavigation.setOnNavigationItemReselectedListener(this)
        
    }
    
    override fun onShowCharacterDetail(character: MarvelCharacter, thumbnail: ImageView) {
        val transitionName = ViewCompat.getTransitionName(thumbnail)
        
        val characterDetailFragment = CharacterDetailFragment.newInstance(character)
        supportFragmentManager
                .beginTransaction()
                .addSharedElement(thumbnail, transitionName)
                .addToBackStack(null)
                .add(R.id.fragmentContainer, characterDetailFragment)
                .commit()
        
    }
}