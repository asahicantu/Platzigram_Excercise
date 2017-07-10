package com.kororia.fshop.post.presenter;

import android.graphics.Bitmap;
import android.net.Uri;

import com.kororia.fshop.post.interactor.PostInteractor;
import com.kororia.fshop.post.interactor.PostInteractorImpl;
import com.kororia.fshop.post.view.PostView;

import java.io.ByteArrayOutputStream;

/**
 * Created by Asahi on 7/8/2017.
 */

public class PostPresenterImpl implements PostPresenter {
    private PostInteractor _interactor;
    private PostView _view;

    public PostPresenterImpl(PostView postView) {
        this();
        _view = postView;
    }

    private PostPresenterImpl() {
        _interactor = new PostInteractorImpl(this);
    }

    @Override
    public void uploadPhoto(Bitmap bitmap, String photoName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageByte = baos.toByteArray();
        _interactor.uploadPhoto(imageByte, photoName);
    }

    @Override
    public void uploadPhotoSuccess(String message) {
        _view.uploadPhotoSuccess(message);
    }

    @Override
    public void uploadPhotoError(String message) {
        _view.uploadPhotoSuccess(message);
    }

    @Override
    public void getPhoto(String photoName) {
        _interactor.getPhoto(photoName);
    }

    @Override
    public void getPhotoSuccess(Uri uri) {
        _view.getPhotoSuccess(uri);
    }

    @Override
    public void getPhotoError(String message) {
        _view.getPhotoError(message);
    }

    @Override
    public boolean existsPhoto(String photoName) {
        return false;
    }
}
