package com.stimednp.aplikasimoviecataloguesub3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stimednp.aplikasimoviecataloguesub3.Model.MovieItems;
import com.stimednp.aplikasimoviecataloguesub3.Model.TvShowItems;
import com.stimednp.aplikasimoviecataloguesub3.R;

import java.util.ArrayList;

/**
 * Created by rivaldy on 7/23/2019.
 */

public class TvShowItemsAdapter extends RecyclerView.Adapter<TvShowItemsAdapter.TvShowItemsViewHolder> {
    private ArrayList<TvShowItems> tvShowData = new ArrayList<>();
    private Context context;

    public TvShowItemsAdapter(Context context) {
        this.context = context;
    }

    public void setTvShowData(ArrayList<TvShowItems> tvShowData) {
        this.tvShowData.clear();
        this.tvShowData.addAll(tvShowData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvShowItemsAdapter.TvShowItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_list_movie, viewGroup, false);
        return new TvShowItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowItemsAdapter.TvShowItemsViewHolder tvShowItemsViewHolder, int i) {
        tvShowItemsViewHolder.bind(tvShowData.get(i));
    }

    @Override
    public int getItemCount() {
        return tvShowData.size();
    }

    class TvShowItemsViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewImg, cardViewDesc, cardViewRating;
        TextView tvTitle, tvRelease, tvRating, tvDesc;
        ImageView imgvPoster;

        TvShowItemsViewHolder(@NonNull View itemView) {
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

        void bind(TvShowItems tvShowItems) {
            String pathImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
            tvTitle.setText(tvShowItems.getName());
            tvRelease.setText(tvShowItems.getFirst_air_date());
            tvRating.setText(tvShowItems.getVote_average().toString());
            tvDesc.setText(tvShowItems.getOverview());
            Glide.with(context)
                    .load(pathImg + tvShowItems.getPoster_path())
                    .into(imgvPoster);
        }
    }
}
