package com.stimednp.aplikasimoviecataloguesub3.Activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.stimednp.aplikasimoviecataloguesub3.Adapter.TabAdapter;
import com.stimednp.aplikasimoviecataloguesub3.Fragment.TabMoviesFragment;
import com.stimednp.aplikasimoviecataloguesub3.Fragment.TabTvShowFragment;
import com.stimednp.aplikasimoviecataloguesub3.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_tollbar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tab_layout_main);
        viewPager = findViewById(R.id.view_pager_main);
        tabAdapter = new TabAdapter(getSupportFragmentManager());

        //callmethod
        setActionBarToolbar();
        setMyTabLaout();
    }

    private void setMyTabLaout() {
        String titleTab1 = getResources().getString(R.string.name_tab1_movies);
        String titleTab2 = getResources().getString(R.string.name_tab2_tvshow);
        tabAdapter.addFragment(new TabMoviesFragment(), titleTab1);
        tabAdapter.addFragment(new TabTvShowFragment(), titleTab2);

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabIcons[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabIcons[1]);
    }

    private int[] tabIcons = {
            R.drawable.ic_movie_white_24dp,
            R.drawable.ic_live_tv_black_24dp
    };

    private void setActionBarToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_right_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int itemId) {
        if (itemId == R.id.change_set_lang) {
            Intent changeLangIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(changeLangIntent);
        }
    }
}
