/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.presentation.character.favorite.FavoriteCharactersFragment;
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListFragment;
import com.futureworkshops.marvelheroes.presentation.character.search.SearchCharacterFragment;
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharactersActivity extends BaseActivity implements OnNavigationItemSelectedListener {
    
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    
    private CharacterListFragment characterListFragment;
    private FavoriteCharactersFragment favoriteCharactersFragment;
    private SearchCharacterFragment searchCharacterFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);
        
        initBottomNavigation();
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_characters:
                showCharactersFragment();
                break;
            case R.id.action_show_favorites:
                showFavoritesFragment();
                break;
            case R.id.action_search:
                showSearchFragment();
                break;
        }
        
        return true;
    }
    
    private void showSearchFragment() {
        if (searchCharacterFragment == null) {
            searchCharacterFragment = SearchCharacterFragment.newInstance();
        }
        
        replaceFragment(searchCharacterFragment);
    }
    
    private void showFavoritesFragment() {
        if (favoriteCharactersFragment == null) {
            favoriteCharactersFragment = FavoriteCharactersFragment.newInstance();
        }
        
        replaceFragment(favoriteCharactersFragment);
    }
    
    private void showCharactersFragment() {
        if (characterListFragment == null) {
            characterListFragment = CharacterListFragment.newInstance();
        }
        
        replaceFragment(characterListFragment);
    }
    
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit();
    }
    
    private void initBottomNavigation() {
        final Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            final MenuItem item = menu.getItem(i);
            
            if (item.getItemId() == R.id.action_show_characters) {
                item.setChecked(true);
                bottomNavigationView.setSelectedItemId(item.getItemId());
                showCharactersFragment();
            }
        }
        
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        
        
    }
    
    
}
