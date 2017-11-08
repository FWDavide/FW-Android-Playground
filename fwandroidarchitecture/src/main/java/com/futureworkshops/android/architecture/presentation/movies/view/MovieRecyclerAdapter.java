package com.futureworkshops.android.architecture.presentation.movies.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.futureworkshops.android.architecture.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stelian on 28/08/2017.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieItem> mMovieItems;

    public MovieRecyclerAdapter() {
        mMovieItems = new ArrayList<>();
    }

    public void setMovieItems(List<Movie> movies) {
        if (!mMovieItems.isEmpty()) {
            final int size = mMovieItems.size();
            mMovieItems.clear();
            notifyItemRangeRemoved(0, size);
        }

        for (Movie movie : movies) {
            mMovieItems.add(MovieItem.newLoadingMovie(movie));
        }

        notifyItemRangeInserted(0, movies.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mMovieItems.size();
    }
}
