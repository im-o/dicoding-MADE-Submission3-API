package com.stimednp.aplikasimoviecataloguesub3.Fragment;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stimednp.aplikasimoviecataloguesub3.Adapter.MovieItemsAdapter;
import com.stimednp.aplikasimoviecataloguesub3.Model.MainViewModel;
import com.stimednp.aplikasimoviecataloguesub3.Model.MovieItems;
import com.stimednp.aplikasimoviecataloguesub3.MyOtherAddingMethod.CheckNetwork;
import com.stimednp.aplikasimoviecataloguesub3.R;

import android.arch.lifecycle.ViewModelProviders;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabMoviesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = TabMoviesFragment.class.getSimpleName();
    private RecyclerView recyclerViewMovie;
    private MainViewModel mainViewModel;
    private MovieItemsAdapter movieItemsAdapter;
    SwipeRefreshLayout refreshLayoutMovie;
    FrameLayout frameLayoutMovie;

    public TabMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshLayoutMovie = view.findViewById(R.id.swipe_scroll_movie);
        frameLayoutMovie = view.findViewById(R.id.framel_movie);
        refreshLayoutMovie.setOnRefreshListener(this);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getListMovies().observe(getActivity(), getMovie);
        movieItemsAdapter = new MovieItemsAdapter(getActivity());

        recyclerViewMovie = view.findViewById(R.id.rv_tab_movies);

        checkingNetwork();
    }

    Observer<ArrayList<MovieItems>> getMovie = new Observer<ArrayList<MovieItems>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieItems> movieItems) {
            if (movieItems != null) {
                if (movieItemsAdapter != null){
                    movieItemsAdapter.setMoviesData(movieItems);
                }
                if (refreshLayoutMovie.isRefreshing()){
                    timeRecyclerLoadFalse();
                }
            }
        }
    };

    private void checkingNetwork() {
        refreshLayoutMovie.setRefreshing(true);
        String noInternet = getResources().getText(R.string.str_no_internet).toString();
        String tryAgain = getResources().getText(R.string.str_try_again).toString();
        String reconnect = getResources().getText(R.string.str_reconnect).toString();
        String wrongNet = getResources().getText(R.string.str_wrong_net).toString();
        String wrongError = getResources().getText(R.string.str_error_wrong).toString();
        if (CheckNetwork.isInternetAvailable(getContext())) {
            int status = CheckNetwork.statusInternet;
            if (status == 0) {//disconnect
                timeRecyclerLoadFalse();
                Snackbar snackbar = Snackbar.make(frameLayoutMovie, noInternet,
                        Snackbar.LENGTH_LONG).setAction(tryAgain, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkingNetwork();
                    }
                });
                snackbar.setActionTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
                snackbar.show();
            } else if (status == 1) {//connected
                showRecyclerList();
            } else if (status == 2) {//reconnection
                Toast.makeText(getContext(), reconnect, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), wrongNet, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), wrongError, Toast.LENGTH_SHORT).show();
        }
    }

    private void showRecyclerList() {
        refreshLayoutMovie.setRefreshing(true);
        if (movieItemsAdapter != null){
            recyclerViewMovie.setHasFixedSize(true);
            mainViewModel.setListMovies(getActivity());
            movieItemsAdapter.notifyDataSetChanged();
            recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewMovie.setAdapter(movieItemsAdapter);
        }
    }

    @Override
    public void onRefresh() {
        checkingNetwork();
    }

    private void timeRecyclerLoadFalse() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayoutMovie.isRefreshing()) {
                    refreshLayoutMovie.setRefreshing(false);
                }
            }
        }, 0);
    }
}
