package com.kororia.fshop.post.view;

import android.net.Uri;

/**
 * Created by AMoreno15 on 7/3/2017.
 */

public interface PostView {
    void uploadPhotoSuccess(String message);

    void uploadPhotoError(String message);

    void getPhotoSuccess(Uri uri);

    void getPhotoError(String message);
}
