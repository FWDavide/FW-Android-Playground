/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.dagger


import com.futureworkshops.marvelheroes.presentation.character.detail.CharacterDetailFragment
import com.futureworkshops.marvelheroes.presentation.character.detail.dagger.CharacterDetailsModule
import com.futureworkshops.marvelheroes.presentation.character.list.dagger.CharacterListModule
import com.futureworkshops.marvelheroes.presentation.character.list.view.CharacterListFragment
import com.futureworkshops.marvelheroes.presentation.splashscreen.SplashActivity
import com.futureworkshops.marvelheroes.presentation.splashscreen.dagger.SplashscreenModule

import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by dimitrios on 24/08/2017.
 *
 * This Module's purpose, is to define which Activity depends on which Module.
 * By providing this Module to our [AppComponent]
 * we are allowing Dagger to generate SubComponents and inject our activities.
 * The benefit of this approach, is we don't have to define Dagger Components for our Modules, with
 * the exception of our top-level AppComponent.
 * Subcomponents are components that live below the AppComponent in our graph.
 */
@Module
abstract class ActivityComponentBindModule {
    
    @ContributesAndroidInjector(modules = [(SplashscreenModule::class)])
    internal abstract fun bindSplashActivity(): SplashActivity
    
    @ContributesAndroidInjector(modules = [(CharacterListModule::class)])
    internal abstract fun bindCharactersListFragment(): CharacterListFragment
    
    
    @ContributesAndroidInjector(modules = [(CharacterDetailsModule::class)])
    internal abstract fun bindCharactersDetailFragment(): CharacterDetailFragment
    
}
