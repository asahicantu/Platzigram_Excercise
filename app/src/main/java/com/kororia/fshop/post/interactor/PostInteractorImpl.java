package com.kororia.fshop.post.interactor;

import android.net.Uri;

import com.kororia.fshop.post.presenter.PostPresenter;
import com.kororia.fshop.post.repository.PostRepository;
import com.kororia.fshop.post.repository.PostRepositoryImpl;

/**
 * Created by Asahi on 7/8/2017.
 */

public class PostInteractorImpl implements PostInteractor {
    private PostRepository _repository;
    private PostPresenter _presenter;

    public PostInteractorImpl(PostPresenter presenter) {
        _presenter = presenter;
        _repository = new PostRepositoryImpl(this);
    }

    @Override
    public void uploadPhoto(byte[] imageByte, String photoName) {
        _repository.uploadPhoto(imageByte, photoName);
    }

    @Override

    public void uploadPhotoSuccess(String message) {
        _presenter.uploadPhotoSuccess(message);
    }

    @Override
    public void uploadPhotoError(String message) {
        _presenter.uploadPhotoError(message);
    }

    @Override
    public void getPhoto(String photoName) {
        _repository.getPhoto(photoName);
    }

    @Override
    public void getPhotoSuccess(Uri uri) {
        _presenter.getPhotoSuccess(uri);
    }

    @Override
    public void getPhotoError(String message) {
        _presenter.getPhotoError(message);
    }

    @Override
    public boolean existsPhoto(String photoName) {
        return false;
    }
}
