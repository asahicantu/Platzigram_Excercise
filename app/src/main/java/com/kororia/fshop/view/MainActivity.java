package com.kororia.fshop.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kororia.fshop.FShopApp;
import com.kororia.fshop.R;
import com.kororia.fshop.home.view.HomeFragment;
import com.kororia.fshop.login.view.LoginActivity;
import com.kororia.fshop.profile.view.ProfileFragment;
import com.kororia.fshop.search.view.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnTabSelectListener {

    private FragmentManager mgr = getSupportFragmentManager();
    private NavigationView _navigationView;
    private DrawerLayout _drawer;
    private BottomBar _bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "MainActivity";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _bottomBar = (BottomBar) findViewById(R.id.mainBottomBar);
        _bottomBar.setDefaultTab(R.id.home);
        mgr.popBackStack();
        _bottomBar.setOnTabSelectListener(this);

        _drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        _navigationView = (NavigationView) findViewById(R.id.nav_view);
        _navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mgr.getFragments().size() <= 1) {
                moveTaskToBack(true);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.action_signout:
                signout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        FShopApp.MAUTH.signOut();
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_location:
                fragment = new MapFragment();
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
        mgr.beginTransaction().replace(R.id.mainContainer, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
        _drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        BaseFragment fragment = null;
        switch (tabId) {
            case R.id.home:
                fragment = HomeFragment.newInstance(null, null);
                break;
            case R.id.profile:
                fragment = ProfileFragment.newInstance(null, null);
                break;
            case R.id.search:
                fragment = SearchFragment.newInstance(null, null);
                break;
        }

        mgr.beginTransaction().replace(R.id.mainContainer, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }
}

