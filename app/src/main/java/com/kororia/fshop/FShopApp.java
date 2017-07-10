package com.kororia.fshop;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

/**
 * Created by Asahi on 7/3/2017.
 */

public class FShopApp extends Application {

    public static final int PERMISSION_ACCESS_COURSE_LOCATION = 0;
    public static final int PERMISSION_REQUEST_CAMERA = 0;

    public static final long LOCATION_MIN_DISTANCE_CHANGE_FOR_UPDATES = 4; // 10 meters
    public static final long LOCATION_MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    public static final String PATH_POSTED_IMAGES = "postedImages";

    public static FirebaseAuth MAUTH;

    public static FirebaseStorage FBSTORAGE;

    private static FShopApp instance;

    public static FShopApp getInstance() {
        return instance;
    }

    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf("/") + 1, path.length());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        MAUTH = FirebaseAuth.getInstance();
        FBSTORAGE = FirebaseStorage.getInstance();
        instance = this;
    }


}
