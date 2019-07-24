package com.stimednp.aplikasimoviecataloguesub3.Activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.stimednp.aplikasimoviecataloguesub3.R;

public class DetailsMovieActivity extends AppCompatActivity {
    Toolbar toolbarDetails;
    CollapsingToolbarLayout collapse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        //inisial
        toolbarDetails = findViewById(R.id.toolbar_detail);
        collapse = findViewById(R.id.collapse_toolbar_detail);

        setSupportActionBar(toolbarDetails);
        collapse.setExpandedTitleColor(Color.argb(0,0,0,0));

        //callmethod
        setActionBarToolbar();
    }
    private void setActionBarToolbar() {
        toolbarDetails.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbarDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
