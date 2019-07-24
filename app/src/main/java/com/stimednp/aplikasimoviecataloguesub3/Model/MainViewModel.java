package com.stimednp.aplikasimoviecataloguesub3.Model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.stimednp.aplikasimoviecataloguesub3.API.APIClientMovieTv;
import com.stimednp.aplikasimoviecataloguesub3.API.APIMovieTv;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rivaldy on 7/23/2019.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();
    private static final String API_KEY = "8b904530a7aced766995fa063ed27355";
    private static final String LANGUAGE = "en-US";
    private MutableLiveData<ArrayList<MovieItems>> listMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShowItems>> listTvShow = new MutableLiveData<>();

    public void setListMovies(final Context context) {
        APIMovieTv apiMovieTv = APIClientMovieTv.getClient().create(APIMovieTv.class);
        Call<MoviesResponse> call = apiMovieTv.getMovieList(API_KEY, LANGUAGE);
        final ArrayList<MovieItems> movieItems = new ArrayList<>();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<MovieItems> movieItemList = response.body().getResults();
                try {
                    movieItems.addAll(movieItemList);
                    listMovies.postValue(movieItems);
                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(TAG, "ERROR : "+t.getMessage());
            }
        });
    }

    public void setListTvShow(final Context context) {
        APIMovieTv apiMovieTv = APIClientMovieTv.getClient().create(APIMovieTv.class);
        Call<TvShowResponse> call = apiMovieTv.getTvShowList(API_KEY, LANGUAGE);
        final ArrayList<TvShowItems> tvShowItems = new ArrayList<>();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                List<TvShowItems> movieItemList = response.body().getResults();
                try {
                    tvShowItems.addAll(movieItemList);
                    listTvShow.postValue(tvShowItems);
                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d(TAG, "ERROR : "+t.getMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<MovieItems>> getListMovies() {
        return listMovies;
    }
    public MutableLiveData<ArrayList<TvShowItems>> getListTvShow() {
        return listTvShow;
    }
}
