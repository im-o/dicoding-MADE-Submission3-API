package com.stimednp.aplikasimoviecataloguesub3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stimednp.aplikasimoviecataloguesub3.Model.MovieItems;
import com.stimednp.aplikasimoviecataloguesub3.R;

import java.util.ArrayList;

/**
 * Created by rivaldy on 7/23/2019.
 */

public class MovieItemsAdapter extends RecyclerView.Adapter<MovieItemsAdapter.MovieItemsViewHolder> {
    private ArrayList<MovieItems> moviesData = new ArrayList<>();
    private Context context;

    public MovieItemsAdapter(Context context) {
        this.context = context;
    }

    public void setMoviesData(ArrayList<MovieItems> moviesData) {
        this.moviesData.clear();
        this.moviesData.addAll(moviesData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieItemsAdapter.MovieItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_list_movie, viewGroup, false);
        return new MovieItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemsAdapter.MovieItemsViewHolder movieItemsViewHolder, int i) {
        movieItemsViewHolder.bind(moviesData.get(i));
//        movieItemsViewHolder.cardViewImg.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
//        movieItemsViewHolder.cardViewDesc.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_from_end));
//        movieItemsViewHolder.cardViewRating.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    class MovieItemsViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewImg, cardViewDesc, cardViewRating;
        TextView tvTitle, tvRelease, tvRating, tvDesc;
        ImageView imgvPoster;

        MovieItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvRelease = itemView.findViewById(R.id.tv_item_release);
            tvRating = itemView.findViewById(R.id.tv_item_rating);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
            imgvPoster = itemView.findViewById(R.id.img_item_photo);
            cardViewImg = itemView.findViewById(R.id.card_img);
            cardViewDesc = itemView.findViewById(R.id.card_view_desc);
            cardViewRating = itemView.findViewById(R.id.card_view_rating);
        }

        void bind(MovieItems movieItems) {
            String pathImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
            String title = movieItems.getTitle();
            String release = movieItems.getRelease_date();
            String voteValue = movieItems.getVote_average().toString();
            String overView = movieItems.getOverview();
            String imgUrl = movieItems.getPoster_path();

            tvTitle.setText(title);
            tvRelease.setText(release);
            tvRating.setText(voteValue);
            tvDesc.setText(overView);
            Glide.with(context)
                    .load(pathImg + imgUrl)
                    .into(imgvPoster);
        }
    }
}
