package com.futureworkshops.android.architecture.presentation.movies.view;

import com.futureworkshops.android.architecture.model.Movie;

/**
 * Created by stelian on 28/08/2017.
 */

public class MovieItem {

    public static final int STATE_LOADING = 1;
    public static final int STATE_LOADED = 1;

    private int mState;
    private Movie mMovie;

    public static MovieItem newLoadingMovie(Movie movie) {
        MovieItem item = new MovieItem();

        item.setMovie(movie);
        item.setState(STATE_LOADING);

        return item;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }

    public Movie getMovie() {
        return mMovie;
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }
}
