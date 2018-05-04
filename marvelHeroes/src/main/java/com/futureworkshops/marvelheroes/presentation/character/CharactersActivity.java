/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemReselectedListener;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.futureworkshops.marvelheroes.R;
import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter;
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailFragment;
import com.futureworkshops.marvelheroes.presentation.character.favorite.FavoriteCharactersFragment;
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListFragment;
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListFragment.CharacterDetailListener;
import com.futureworkshops.marvelheroes.presentation.character.search.SearchCharacterFragment;
import com.futureworkshops.marvelheroes.presentation.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CharactersActivity extends BaseActivity implements OnNavigationItemSelectedListener,
    OnNavigationItemReselectedListener, CharacterDetailListener {
    
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    
    private CharacterListFragment characterListFragment;
    private FavoriteCharactersFragment favoriteCharactersFragment;
    private SearchCharacterFragment searchCharacterFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);
        
        initBottomNavigation();
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_characters:
                Timber.d("select characters");
                showCharactersFragment();
                break;
            case R.id.action_show_favorites:
                Timber.d("select favorites");
                showFavoritesFragment();
                break;
            case R.id.action_search:
                Timber.d("select search");
                showSearchFragment();
                break;
        }
        
        return true;
    }
    
    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_characters:
                // TODO: 09/04/2018 reset character list fragment to original state
                Timber.d("reselect characters");
                break;
            case R.id.action_show_favorites:
                Timber.d("reselect favorites");
                break;
            case R.id.action_search:
                Timber.d("reselect search");
                break;
        }
    }
    
    private void showSearchFragment() {
        if (searchCharacterFragment == null) {
            searchCharacterFragment = SearchCharacterFragment.newInstance();
        }
        
        replaceFragment(searchCharacterFragment, "SEARCH_ROOT");
    }
    
    private void showFavoritesFragment() {
        if (favoriteCharactersFragment == null) {
            favoriteCharactersFragment = FavoriteCharactersFragment.newInstance();
        }
        
        replaceFragment(favoriteCharactersFragment, "FAVORITES_ROOT");
    }
    
    private void showCharactersFragment() {
        if (characterListFragment == null) {
            characterListFragment = CharacterListFragment.newInstance();
            characterListFragment.setCharacterDetailListener(this);
        }
        
        replaceFragment(characterListFragment, "CHARACTERS_ROOT");
    }
    
    private void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, fragment, tag)
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
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        
    }
    
    @Override
    public void onShowCharacterDetail(@NonNull MarvelCharacter character, @NonNull ImageView thumbnail) {
        final String transitionName = ViewCompat.getTransitionName(thumbnail);
        
        CharacterDetailFragment characterDetailFragment = CharacterDetailFragment.newInstance(character);
        getSupportFragmentManager()
            .beginTransaction()
            .addSharedElement(thumbnail, transitionName)
            .addToBackStack(null)
            .add(R.id.fragmentContainer, characterDetailFragment)
            .commit();
        
    }
}
