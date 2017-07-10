package com.kororia.fshop.post.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kororia.fshop.FShopApp;
import com.kororia.fshop.R;
import com.kororia.fshop.post.presenter.PostPresenter;
import com.kororia.fshop.post.presenter.PostPresenterImpl;
import com.kororia.fshop.utils.ToolbarUtils;
import com.kororia.fshop.view.BaseActivity;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends BaseActivity implements PostView, ToolbarUtils {
    public static final String IMAGE_URL = "IMAGE_URL";
    private ImageView _imageHeader;
    private PostPresenter _presenter;
    private String imageUrl;
    private String photoName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "PictureDetailActivity";
        _presenter = new PostPresenterImpl(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("", true,null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }
        _imageHeader = (ImageView) findViewById(R.id.imageHeader);
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(IMAGE_URL);
        photoName = FShopApp.getFileName(imageUrl);
        _presenter.getPhoto(photoName);
    }

    private void attachPhoto(String uri) {
        Picasso.with(this).load(uri).into(_imageHeader);
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(tittle);
        ab.setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
    }

    @Override
    public void uploadPhotoSuccess(String message) {
    }

    @Override
    public void uploadPhotoError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getPhotoSuccess(Uri uri) {
        attachPhoto(uri.toString());
    }


    @Override
    public void getPhotoError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.w(TAG, message);
//        _imageHeader.setDrawingCacheEnabled(true);
//        _imageHeader.buildDrawingCache();
//        Bitmap bitmap = _imageHeader.getDrawingCache();
//        String photoName = FShopApp.getFileName(imageUrl);
//        _presenter.uploadPhoto(bitmap,photoName);

    }
}
