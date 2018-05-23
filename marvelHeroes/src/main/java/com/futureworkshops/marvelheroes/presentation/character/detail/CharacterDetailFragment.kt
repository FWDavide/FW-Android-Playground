/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.detail


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.futureworkshops.marvelheroes.R
import com.futureworkshops.marvelheroes.domain.image.GlideApp
import com.futureworkshops.marvelheroes.domain.model.MarvelCharacter
import kotlinx.android.synthetic.main.fragment_character_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterDetailFragment : Fragment(), CharacterDetailContract.View, RequestListener<Drawable> {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(android.R.transition.move)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        if (arguments != null) {
            
            initToolbars()
            GlideApp.with(collapsingImageView)
                    .load(characterArgs().imageUrl)
                    .fitCenter()
                    .dontAnimate()
                    .listener(this as RequestListener<Drawable>)
                    .placeholder(R.drawable.default_thumbnail_placeholder)
                    .into(collapsingImageView)
            
            cardTitleTextView.text = characterArgs().name
            cardDescTextView.text = characterArgs().description
            
        }
    }
    
    private fun initToolbars() {
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity!!.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar!!.setDisplayShowHomeEnabled(false)
        
        collapsingToolbar.title = characterArgs().name
        collapsingToolbar.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
    }
    
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
        startPostponedEnterTransition()
        return false
    }
    
    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        startPostponedEnterTransition()
        return false
    }
    
    private fun characterArgs(): MarvelCharacter {
        return arguments?.getSerializable(ARG_CHARACTER) as MarvelCharacter
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
