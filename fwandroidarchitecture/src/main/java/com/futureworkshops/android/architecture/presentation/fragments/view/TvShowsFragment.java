package com.futureworkshops.android.architecture.presentation.fragments.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureworkshops.android.architecture.R;
import com.futureworkshops.android.architecture.presentation.fragments.TvShowsContract;
import com.futureworkshops.android.architecture.presentation.fragments.TvShowsPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by davidperez on 15/09/2017.
 */

public class TvShowsFragment extends Fragment implements TvShowsContract.View{

    @Inject
    TvShowsPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tvshows, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static TvShowsFragment newInstance(){
        return new TvShowsFragment();
    }

}
