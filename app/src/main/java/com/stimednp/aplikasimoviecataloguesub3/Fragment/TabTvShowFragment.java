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

import com.stimednp.aplikasimoviecataloguesub3.Adapter.TvShowItemsAdapter;
import com.stimednp.aplikasimoviecataloguesub3.Model.MainViewModel;
import com.stimednp.aplikasimoviecataloguesub3.Model.MovieItems;
import com.stimednp.aplikasimoviecataloguesub3.Model.TvShowItems;
import com.stimednp.aplikasimoviecataloguesub3.MyOtherAddingMethod.AllMyStrings;
import com.stimednp.aplikasimoviecataloguesub3.MyOtherAddingMethod.CheckNetwork;
import com.stimednp.aplikasimoviecataloguesub3.R;

import android.arch.lifecycle.ViewModelProviders;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import com.stimednp.aplikasimoviecataloguesub3.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabTvShowFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = TabMoviesFragment.class.getSimpleName();
    private RecyclerView recyclerViewMovie;
    private MainViewModel mainViewModel;
    private TvShowItemsAdapter movieItemsAdapter;
    SwipeRefreshLayout refreshLayoutMovie;
    FrameLayout frameLayoutMovie;

    public TabTvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayoutMovie = view.findViewById(R.id.swipe_scroll_tvshow);
        refreshLayoutMovie.setOnRefreshListener(this);
        frameLayoutMovie = view.findViewById(R.id.framel_tvshow);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getListTvShow().observe(getActivity(), getTvShow);

        recyclerViewMovie = view.findViewById(R.id.rv_tab_tvshow);
        recyclerViewMovie.setHasFixedSize(true);

        movieItemsAdapter = new TvShowItemsAdapter(getActivity());
        showRecyclerList();
    }
    Observer<ArrayList<TvShowItems>> getTvShow = new Observer<ArrayList<TvShowItems>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShowItems> movieItems) {
            if (movieItems != null) {
                movieItemsAdapter.setTvShowData(movieItems);
                if (refreshLayoutMovie.isRefreshing()){
                    timeRecyclerLoadFalse();
                }
            }
        }
    };

    private void checkingNetwork() {
        AllMyStrings myStr = new AllMyStrings();
        String noInternet = myStr.getNoInet(getContext());
        String tryAgain = myStr.getTryAgain(getContext());
        String reconnect = myStr.getRecon(getContext());
        String wrongNet = myStr.getWrongNet(getContext());
        String wrongError = myStr.getWrongErr(getContext());
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
        mainViewModel.setListTvShow(getActivity());
        movieItemsAdapter.notifyDataSetChanged();

        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMovie.setAdapter(movieItemsAdapter);
        timeRecyclerLoadFalse();
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
        }, 1000);
    }
}
