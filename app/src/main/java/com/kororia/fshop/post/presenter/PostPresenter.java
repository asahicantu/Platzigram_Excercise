package com.kororia.fshop.post.presenter;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by Asahi on 7/8/2017.
 */

public interface PostPresenter {
    void uploadPhoto(Bitmap bitmap, String photoName);

    void uploadPhotoSuccess(String message);

    void uploadPhotoError(String message);

    void getPhoto(String photoName);

    void getPhotoSuccess(Uri uri);

    void getPhotoError(String message);

    boolean existsPhoto(String photoName);
}
