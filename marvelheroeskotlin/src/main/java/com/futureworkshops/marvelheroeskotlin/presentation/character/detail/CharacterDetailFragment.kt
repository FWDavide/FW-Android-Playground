/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.detail

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.futureworkshops.marvelheroeskotlin.R
import com.futureworkshops.marvelheroeskotlin.domain.image.GlideApp
import com.futureworkshops.marvelheroeskotlin.domain.model.MarvelCharacter
import com.futureworkshops.marvelheroeskotlin.presentation.common.BaseActivity
import com.futureworkshops.marvelheroeskotlin.presentation.common.BaseFragment
import com.futureworkshops.marvelheroeskotlin.presentation.common.MvpView
import kotlinx.android.synthetic.main.fragment_character_detail.*
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

interface CharacterDetailView : MvpView

class CharacterDetailFragment : BaseFragment(), CharacterDetailView, RequestListener<Drawable> {
    
    @Inject
    lateinit var characterDetailPresenter: CharacterDetailPresenter
    
    fun inject() {
        DaggerCharacterDetailsComponent.builder()
                .appComponent(appComponent())
                .characterDetailsModule(CharacterDetailsModule())
                .build().inject(this)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(android.R.transition.move)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        characterFromBundle()?.let {
            
            initToolbars()
            
            //            imageView.setTransitionName("transition23234");
            //
            //            imageLoader.loadLandscapeImage(imageView, character.getLandscapeImageUrl(), this);
            
            
            GlideApp.with(collapsingImageView)
                    .load(it.imageUrl)
                    .fitCenter()
                    .dontAnimate()
                    .listener(this@CharacterDetailFragment)
                    .placeholder(R.drawable.default_thumbnail_placeholder)
                    .into(collapsingImageView)
            
            cardTitleTextView.text = it.name
            cardDescriptionTextView.text = it.description
        }
    }
    
    private fun characterFromBundle(): MarvelCharacter? {
        return arguments?.takeIf { it.containsKey(ARG_CHARACTER) }?.getSerializable(ARG_CHARACTER) as MarvelCharacter
    }
    
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
        startPostponedEnterTransition()
        return false
    }
    
    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        startPostponedEnterTransition()
        return false
    }
    
    private fun initToolbars() {
        (activity as BaseActivity).setupToolbar(toolbar, true)
        collapsingToolbar.title = characterFromBundle()?.name
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(activity as Context, android.R.color.transparent))
    }
    
    companion object {
        
        private const val ARG_CHARACTER = "character"
        
        fun newInstance(character: MarvelCharacter): CharacterDetailFragment {
            val fragment = CharacterDetailFragment()
            val args = Bundle()
            args.putSerializable(ARG_CHARACTER, character)
            fragment.arguments = args
            return fragment
        }
    }
}