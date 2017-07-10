package com.kororia.fshop.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.crash.FirebaseCrash;
import com.kororia.fshop.R;

/**
 * Created by Asahi on 7/1/2017.
 */

public abstract class BaseFragment extends Fragment {

    public static String TAG;
    private Toolbar _toolbar;

    public Toolbar getToolbar() {
        return _toolbar;
    }

    public void showToolbar(String tittle, boolean upButton, View view, AppCompatActivity activity) {
        _toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(_toolbar);
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawer, _toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ActionBar bar = activity.getSupportActionBar();
        bar.setTitle(tittle);
        bar.setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        FirebaseCrash.logcat(Log.WARN, TAG, "Initializing ");
        super.onCreate(savedInstanceState);
    }
}
