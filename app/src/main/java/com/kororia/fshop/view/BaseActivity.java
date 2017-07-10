package com.kororia.fshop.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;

/**
 * Created by Asahi on 7/10/2017.
 */

public class BaseActivity extends AppCompatActivity {

    public String TAG = "NO_TAG";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        FirebaseCrash.logcat(Log.DEBUG, TAG, "Initializing");
    }
}
