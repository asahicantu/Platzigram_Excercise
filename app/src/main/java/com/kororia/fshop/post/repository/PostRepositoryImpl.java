package com.kororia.fshop.post.repository;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kororia.fshop.FShopApp;
import com.kororia.fshop.post.interactor.PostInteractor;

/**
 * Created by Asahi on 7/9/2017.
 */

public class PostRepositoryImpl implements PostRepository, OnFailureListener, OnSuccessListener {
    private PostInteractor _interactor;
    private PostActionType _postActionType;

    public PostRepositoryImpl(PostInteractor _interactor) {
        this._interactor = _interactor;
    }

    @Override
    public void uploadPhoto(byte[] imageByte, String photoName) {
        _postActionType = PostActionType.UPLOAD_PHOTO;
        StorageReference sr = FShopApp.FBSTORAGE.getReference();
        sr = sr.child(FShopApp.PATH_POSTED_IMAGES + "/" + photoName);
        UploadTask ut = sr.putBytes(imageByte);
        ut.addOnFailureListener(this);
        ut.addOnSuccessListener(this);
    }

    @Override
    public void getPhoto(String photoName) {
        _postActionType = PostActionType.GET_PHOTO;
        StorageReference sr = FShopApp.FBSTORAGE.getReference();
        sr = sr.child(FShopApp.PATH_POSTED_IMAGES + "/" + photoName);
        Task<Uri> task = sr.getDownloadUrl();
        task.addOnSuccessListener(this);
        task.addOnFailureListener(this);
    }


    public void tryUploadPhoto(byte[] bytes, String photoName) {
        _postActionType = PostActionType.UPLOAD_PHOTO;
        StorageReference sr = FShopApp.FBSTORAGE.getReference();
        sr = sr.child(FShopApp.PATH_POSTED_IMAGES + "/" + photoName);
        UploadTask ut = sr.putBytes(bytes);
        ut.addOnFailureListener(this);
        ut.addOnSuccessListener(this);
    }


    @Override
    public void onFailure(@NonNull Exception e) {
        FirebaseCrash.report(e);
        switch (_postActionType) {
            case UPLOAD_PHOTO:
                _interactor.uploadPhotoError("Upload photo failed. " + e.getMessage().toString());

                break;
            case GET_PHOTO:
                _interactor.getPhotoError("Get photo failed. " + e.getMessage().toString());
            case GET_PHOTO_UPLOAD_IF_FAILS:
                Log.w("UPLOAD_PHOTO", e.getMessage().toString());
                break;
        }

    }

    @Override
    public void onSuccess(Object o) {
        switch (_postActionType) {
            case UPLOAD_PHOTO:
                UploadTask.TaskSnapshot taskSnapshot = (UploadTask.TaskSnapshot) o;
                Uri uriImg = taskSnapshot.getDownloadUrl();
                _interactor.uploadPhotoSuccess("Upload photo success " + uriImg.toString());
                break;
            case GET_PHOTO: {
                Uri uri = (Uri) o;
                _interactor.getPhotoSuccess(uri);
            }
            break;
            case GET_PHOTO_UPLOAD_IF_FAILS: {
                Task<Uri> task = (Task<Uri>) o;
                Uri uri = task.getResult();

            }
            break;
        }

    }

}
