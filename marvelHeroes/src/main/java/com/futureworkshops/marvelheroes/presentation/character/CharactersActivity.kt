/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character

import android.os.Bundle
import android.support.design.widget.BottomNavigationView.OnNavigationItemReselectedListener
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.MenuItem
import android.widget.ImageView
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailFragment
import com.futureworkshops.marvelheroes.presentation.character.favorite.FavoriteCharactersFragment
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterDetailListener
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListFragment
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity
import kotlinx.android.synthetic.main.activity_characters.*
import timber.log.Timber

class CharactersActivity : BaseActivity(), OnNavigationItemSelectedListener, OnNavigationItemReselectedListener, CharacterDetailListener {
    
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
