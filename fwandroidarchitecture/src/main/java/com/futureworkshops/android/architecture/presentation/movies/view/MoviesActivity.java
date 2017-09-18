package com.futureworkshops.android.architecture.presentation.movies.view;

import android.os.Bundle;

import com.futureworkshops.android.architecture.R;
import com.futureworkshops.android.architecture.presentation.common.BaseActivity;
import com.futureworkshops.android.architecture.presentation.movies.MoviesPresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MoviesActivity extends BaseActivity {

    @Inject
    MoviesPresenter mMoviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
    }
}
