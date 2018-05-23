/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter
import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailFragment
import com.futureworkshops.marvelheroes.presentation.character.list.CharacterListPresenter
import com.futureworkshops.marvelheroes.presentation.character.list.CharactersListContract
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListAdapter.CharacterClickListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_character_list.*
import javax.inject.Inject

/**
 * Created by stelian on 05/04/2018.
 */

class CharacterListFragment : Fragment(), CharactersListContract.View, CharacterClickListener {
    
    @Inject
    lateinit var characterListPresenter: CharacterListPresenter
    private val characterListAdapter = CharacterListAdapter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_character_list, container, false)
        initSwipeRefreshLayout()
        initRecyclerView()
        return view
    }
    
    override fun onStart() {
        super.onStart()
        loadAvengersCharacters()
    }
    
    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener { loadAvengersCharacters() }
    }
    
    private fun initRecyclerView() {
        characterListAdapter.setCharacterClickListener(this)
        characterRv.layoutManager = GridLayoutManager(context, 2)
        characterRv.adapter = characterListAdapter
    }
    
    private fun loadAvengersCharacters() {
        characterListPresenter.loadAvengerCharacters()
    }
    
    override fun onCharactersLoaded(characters: List<MarvelCharacter>) {
        characterListAdapter.setItems(characters)
    }
    
    override fun showNoInternetConnection() {
    
    }
    
    override fun hideNoInternetConnection() {
    
    }
    
    override fun showCharacterEmptyView() {
    
    }
    
    override fun hideCharacterEmptyView() {
    
    }
    
    override fun showRefreshing() {
        swipeRefreshLayout!!.isRefreshing = true
    }
    
    override fun hideRefreshing() {
        swipeRefreshLayout!!.isRefreshing = false
    }
    
    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
    
    override fun onCharacterClicked(character: MarvelCharacter, listImageView: ImageView?) {
        val transitionName = ViewCompat.getTransitionName(listImageView)
        val characterDetailFragment = CharacterDetailFragment.newInstance(character)
        fragmentManager!!
                .beginTransaction()
                .addSharedElement(listImageView, transitionName)
                .addToBackStack(null)
                .add(R.id.fragmentContainer, characterDetailFragment)
                .commit()
        
    }
    
    interface CharacterDetailListener {
        
        fun onShowCharacterDetail(character: MarvelCharacter, thumbnail: ImageView)
        
    }
    
    companion object {
        
        fun newInstance(): CharacterListFragment {
            return CharacterListFragment()
        }
    }
}