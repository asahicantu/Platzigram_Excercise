package com.asahicantu.platzigram_excercise.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.asahicantu.platzigram_excercise.fragment.HomeFragment;
import com.asahicantu.platzigram_excercise.fragment.ProfileFragment;
import com.asahicantu.platzigram_excercise.fragment.SearchFragment;
import com.asahicantu.platzigram_excercise.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.mainBottombar);
        bottomBar.setDefaultTab(R.id.home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
                                             @Override
                                             public void onTabSelected(@IdRes int tabId) {
                                                 switch (tabId) {
                                                     case R.id.home:
                                                         HomeFragment homeFragment = new HomeFragment();
                                                         getSupportFragmentManager().beginTransaction().replace(R.id.content_main, homeFragment)
                                                                 .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                                                 .addToBackStack(null).commit();
                                                        showToolbar(getResources().getString(R.string.tab_home), false);

                                                         break;
                                                     case R.id.profile:
                                                         ProfileFragment profileFragment = new ProfileFragment();
                                                         getSupportFragmentManager().beginTransaction().replace(R.id.content_main, profileFragment)
                                                                 .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                                                 .addToBackStack(null).commit();
                                                         showToolbar(getResources().getString(R.string.tab_profile), false);
                                                         break;
                                                     case R.id.search:
                                                         SearchFragment searchFragment = new SearchFragment();
                                                         getSupportFragmentManager().beginTransaction().replace(R.id.content_main, searchFragment)
                                                                 .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                                                 .addToBackStack(null).commit();
                                                         showToolbar(getResources().getString(R.string.tab_search), false);
                                                         break;
                                                 }
                                             }
                                         });


     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.addDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
            }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void showToolbar(String tittle, boolean upButton){
        ActionBar bar = getSupportActionBar();
        bar.setTitle(tittle);
        bar.setDisplayHomeAsUpEnabled(upButton);
    }


}

