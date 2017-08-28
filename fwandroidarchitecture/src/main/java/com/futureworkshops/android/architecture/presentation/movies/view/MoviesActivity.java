package com.futureworkshops.android.architecture.presentation.movies.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.futureworkshops.android.architecture.R;
import com.futureworkshops.android.architecture.presentation.common.BaseActivity;

public class MoviesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
    }
}
