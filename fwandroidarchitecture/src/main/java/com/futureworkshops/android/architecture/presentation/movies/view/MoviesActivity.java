package com.futureworkshops.android.architecture.presentation.movies.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.futureworkshops.android.architecture.R;
import com.futureworkshops.android.architecture.presentation.common.BaseActivity;
import com.futureworkshops.android.architecture.presentation.fragments.view.TvShowsFragment;
import com.futureworkshops.android.architecture.presentation.movies.MoviesPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class MoviesActivity extends BaseActivity implements HasFragmentInjector{

    @BindView(R.id.tvShowsFragment)
    FrameLayout tvShowsFragment;

    @Inject
    MoviesPresenter mMoviesPresenter;

    /**
     * This must be added in order to inject components to an added fragment.
     */
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        addFragment();
    }

    private void addFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.tvShowsFragment, TvShowsFragment.newInstance());
        transaction.commit();
    }


    /**
     * This must be added in order to inject components to an added fragment.
     */
    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
