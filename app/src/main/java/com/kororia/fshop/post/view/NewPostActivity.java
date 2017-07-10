package com.kororia.fshop.post.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kororia.fshop.FShopApp;
import com.kororia.fshop.R;
import com.kororia.fshop.post.presenter.PostPresenter;
import com.kororia.fshop.post.presenter.PostPresenterImpl;
import com.kororia.fshop.view.BaseActivity;
import com.squareup.picasso.Picasso;

public class NewPostActivity extends BaseActivity implements PostView, View.OnClickListener {

    public static final String IMAGE_PATH = "IMAGE_PATH";
    private ImageView _imgPhoto;
    private TextInputEditText _txtTitle;
    private TextInputEditText _txtDescription;
    private Button _btnCreatePost;
    private PostPresenter _presenter;
    private String _photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "NewPostActivity";
        super.onCreate(savedInstanceState);
        _presenter = new PostPresenterImpl(this);

        setContentView(R.layout.activity_new_post);
        _imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        _txtTitle = (TextInputEditText) findViewById(R.id.txtTitle);
        _txtDescription = (TextInputEditText) findViewById(R.id.txtDescription);
        _btnCreatePost = (Button) findViewById(R.id.btnCreatePost);
        _btnCreatePost.setOnClickListener(this);
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if (extras != null) {
            _photoPath = extras.getString(IMAGE_PATH);
            showPhoto(_photoPath);
        }
    }

    private void showPhoto(String photoPath) {
        Picasso.with(this).load(photoPath).into(_imgPhoto);
    }

    @Override
    public void onClick(View v) {
        _imgPhoto.setDrawingCacheEnabled(true);
        _imgPhoto.buildDrawingCache();
        Bitmap bitmap = _imgPhoto.getDrawingCache();
        String photoName = FShopApp.getFileName(_photoPath);
        _presenter.uploadPhoto(bitmap, photoName);
    }

    @Override
    public void uploadPhotoSuccess(String message) {
        Log.w(TAG, message);
        finish();
    }

    @Override
    public void uploadPhotoError(String message) {
        Log.w(TAG, message);
    }

    @Override
    public void getPhotoSuccess(Uri uri) {

    }

    @Override
    public void getPhotoError(String message) {

    }
}
