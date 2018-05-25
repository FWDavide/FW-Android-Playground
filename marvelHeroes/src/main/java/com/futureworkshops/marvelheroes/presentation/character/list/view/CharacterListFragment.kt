/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.list.view

import android.content.Context
import android.os.Bundle
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
import com.futureworkshops.marvelheroes.presentation.character.list.dagger.CharacterListModule
import com.futureworkshops.marvelheroes.presentation.character.list.dagger.DaggerCharacterListComponent
import com.futureworkshops.marvelheroes.presentation.common.BaseFragment
import com.futureworkshops.marvelheroes.presentation.common.MvpView
import kotlinx.android.synthetic.main.fragment_character_list.*
import javax.inject.Inject

/**
 * Created by stelian on 05/04/2018.
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
    
    override fun onFragmentInject() {
        DaggerCharacterListComponent.builder()
                .appComponent(appComponent())
                .characterListModule(CharacterListModule(this@CharacterListFragment))
                .build().inject(this)
        characterListPresenter.onAttachView(this)
    }
    
    override fun onAttach(context: Context?) {
        super.onAttach(context)
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
        loadAvengersCharacters()
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