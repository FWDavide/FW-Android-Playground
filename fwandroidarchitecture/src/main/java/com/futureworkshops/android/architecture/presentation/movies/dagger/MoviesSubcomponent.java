package com.futureworkshops.android.architecture.presentation.movies.dagger;

import com.futureworkshops.android.architecture.presentation.movies.view.MoviesActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by dimitrios on 15/09/2017.
 *
 * This is a sub-component. That means it is a Component, that lives below our
 * {@link com.futureworkshops.android.architecture.domain.dagger.AppComponent}
 */
@Subcomponent(modules = MoviesModule.class)
public interface MoviesSubcomponent extends AndroidInjector<MoviesActivity> {

    /**
     * In order for Dagger to generate our subcomponents, we need an abstract Builder class that extends
     * AndroidInjector.Builder<OUR-ACTIVITY> When we Build our project, Dagger will generate the implementation of
     * this Builder class and use it behind the scenes.
     */
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MoviesActivity> {}

}
