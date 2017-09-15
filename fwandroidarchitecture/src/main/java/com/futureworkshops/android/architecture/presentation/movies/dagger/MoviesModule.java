package com.futureworkshops.android.architecture.presentation.movies.dagger;

import com.futureworkshops.android.architecture.presentation.movies.MoviesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dimitrios on 15/09/2017.
 */
@Module
public class MoviesModule {

    @Provides
    public MoviesPresenter providesMoviesPresenter() {
        return new MoviesPresenter();
    }

}
