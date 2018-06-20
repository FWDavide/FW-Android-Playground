/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.list

import android.content.Context
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.futureworkshops.marvelheroeskotlin.MarvelHeroesKotlinApp.Companion.appComponent
import com.futureworkshops.marvelheroeskotlin.R
import com.futureworkshops.marvelheroeskotlin.domain.model.MarvelCharacter
import com.futureworkshops.marvelheroeskotlin.presentation.character.detail.CharacterDetailFragment
import com.futureworkshops.marvelheroeskotlin.presentation.common.BaseFragment
import com.futureworkshops.marvelheroeskotlin.presentation.common.MvpView
import kotlinx.android.synthetic.main.fragment_character_list.*
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

interface CharacterListView : MvpView {
    
    fun onCharactersLoaded(characters: List<MarvelCharacter>)
    
    fun showNoInternetConnection()
    
    fun hideNoInternetConnection()
    
    fun showCharacterEmptyView()
    
    fun hideCharacterEmptyView()
    
    fun showRefreshing()
    
    fun hideRefreshing()
    
    fun showError(message: String)
}

interface CharacterDetailListener {
    
    fun onShowCharacterDetail(character: MarvelCharacter, thumbnail: ImageView)
    
}

class CharacterListFragment : BaseFragment(), CharacterListView, CharacterClickListener {
    
    @Inject
    lateinit var characterListPresenter: CharacterListPresenter
    
    private var characterListAdapter: CharacterListAdapter = CharacterListAdapter(this)
    
    private lateinit var characterDetailListener: CharacterDetailListener
    
    fun inject() {
        DaggerCharacterListComponent.builder()
                .appComponent(appComponent())
                .characterListModule(CharacterListModule(this@CharacterListFragment))
                .build().inject(this)
        characterListPresenter.bindView(this)
    }
    
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()
        characterDetailListener = context as CharacterDetailListener
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener { loadAvengersCharacters() }
        characterRv.layoutManager = GridLayoutManager(context, 2)
        characterRv.adapter = characterListAdapter
    }
    
    override fun onStart() {
        super.onStart()
        characterListPresenter.onSubscribe()
        loadAvengersCharacters()
    }
    
    override fun onStop() {
        super.onStop()
        characterListPresenter.onUnsubscribe()
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
        swipeRefreshLayout.isRefreshing = true
    }
    
    override fun hideRefreshing() {
        swipeRefreshLayout.isRefreshing = false
    }
    
    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
    
    override fun onCharacterClicked(character: MarvelCharacter, listImageView: ImageView) {
        characterDetailListener.onShowCharacterDetail(character, listImageView)
        val transitionName = ViewCompat.getTransitionName(listImageView)
        
        val characterDetailFragment = CharacterDetailFragment.newInstance(character)
        fragmentManager
                ?.beginTransaction()
                ?.addSharedElement(listImageView, transitionName)
                ?.addToBackStack(null)
                ?.add(R.id.fragmentContainer, characterDetailFragment)
                ?.commit()
        
    }
    
    companion object {
        
        fun newInstance(): CharacterListFragment {
            return CharacterListFragment()
        }
    }
}