/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.dagger

import android.app.Application

import com.futureworkshops.marvelheroes.data.network.NetworkConfig
import com.futureworkshops.marvelheroes.data.network.rx.scheduler.SchedulersProvider
import com.futureworkshops.marvelheroes.presentation.MarvelHeroesApp

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component

import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication


/**
 * Created by dimitrios on 22/08/2017.
 *
 *
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 *
 *
 * This is our top-level (top of the dependency graph) component and the only component we need to define.
 *
 *
 * Here we can find 2 new Modules that weren't used previous to Dagger v2.11.
 *
 *
 * [ActivityComponentBindModule]: Defines a mapping between Modules and Activities/Fragments who use them.
 * This way Dagger can generate subcomponents that will be added in the graph below their parent-component. In our
 * case, the AppComponent.
 *
 *
 * [AndroidInjectionModule]: is the module from Dagger.Android that helps with the generation
 * and location of subcomponents.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetModule::class, ActivityComponentBindModule::class, AndroidInjectionModule::class, AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<DaggerApplication> {
    
    fun inject(application: MarvelHeroesApp)
    
    /**
     * This interface is used to provide parameters for modules.
     *
     *  Every method annotated with [BindsInstance] will link the method return type
     * to the method input type for the entire dependency graph - this means that we can't have
     * 2 methods with [BindsInstance] that accept the same type of parameters !!
     */
    @Component.Builder
    interface Builder {
        
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        
        /**
         * Specify the [SchedulersProvider] to be used across the AppComponent graph.
         */
        @BindsInstance
        fun schedulerProvider(schedulersProvider: SchedulersProvider): AppComponent.Builder
        
        
        /**
         * Specify the [NetworkConfig] to be used across the AppComponent graph.
         */
        @BindsInstance
        fun networkConfiguration(networkConfig: NetworkConfig): AppComponent.Builder
        
        
        fun build(): AppComponent
    }
}
