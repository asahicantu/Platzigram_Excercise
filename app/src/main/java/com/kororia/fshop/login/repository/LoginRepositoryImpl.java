package com.kororia.fshop.login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.kororia.fshop.FShopApp;
import com.kororia.fshop.login.interactor.LoginInteractor;
import com.kororia.fshop.model.User;


/**
 * Created by Asahi on 7/1/2017.
 */

public class LoginRepositoryImpl implements LoginRepository, OnSuccessListener, OnFailureListener {
    private LoginInteractor _interactor;
    private LoginType _loginType;
    private Activity _activity;

    public LoginRepositoryImpl(LoginInteractor interactor) {
        this._interactor = interactor;
    }

    @Override
    public void createAccount(User user) {
        _loginType = LoginType.CREATE_ACCOUNT_USER_NAME_PASSWORD;
        switch (user.getLoginOption()) {
            case EmailAndPassword:
                Task<AuthResult> createUserResultTask = FShopApp.MAUTH.createUserWithEmailAndPassword(user.getEmail(), user.getPassword());
                createUserResultTask.addOnSuccessListener(this);
                createUserResultTask.addOnFailureListener(this);
                break;
            case Phone:
                //signUserResultTask = FShopApp.MAUTH.signInWithCredential(user.getEmail(),user.getPassword());
                break;
        }
    }

    @Override
    public void login(User user, final Activity activity) {
        Task<AuthResult> signUserResultTask = null;
        _activity = activity;
        if (user == null) {
            FirebaseUser currentUser = FShopApp.MAUTH.getCurrentUser();
            if (currentUser != null) {
                _interactor.loginSuccess(currentUser);
                return;
            }
        } else {
            switch (user.getLoginOption()) {
                case EmailAndPassword:
                    _loginType = LoginType.LGOIN_MAIL_PASSWORD;
                    signUserResultTask = FShopApp.MAUTH.signInWithEmailAndPassword(user.getEmail(), user.getPassword());
                    break;
                case Phone:
                    _loginType = LoginType.LOGIN_PHONE;
                    //signUserResultTask = FShopApp.MAUTH.signInWithCredential(user.getEmail(),user.getPassword());
                    break;
            }
            signUserResultTask.addOnSuccessListener(this);
            signUserResultTask.addOnFailureListener(this);
        }
    }

    private void saveUserSession(FirebaseUser user, Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("LOGGED_USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", user.getEmail());
        editor.putString("uId", user.getUid());
        editor.commit();
    }

    @Override
    public void onSuccess(Object o) {
        switch (_loginType) {
            case LGOIN_MAIL_PASSWORD:
                FirebaseUser user = FShopApp.MAUTH.getCurrentUser();
                saveUserSession(user, _activity);
                _interactor.loginSuccess(user);
                break;
            case LOGIN_PHONE:
                break;
            case CREATE_ACCOUNT_USER_NAME_PASSWORD:
                _interactor.createAccountSuccess();
                break;
            case CREATE_ACCOUNT_PHONE:
                break;
        }

    }


    @Override
    public void onFailure(@NonNull Exception e) {
        FirebaseCrash.report(e);
        switch (_loginType) {
            case LGOIN_MAIL_PASSWORD:
                _interactor.loginError(e.getMessage());
                break;
            case LOGIN_PHONE:
                break;
            case CREATE_ACCOUNT_USER_NAME_PASSWORD:
                _interactor.createAccountError(e.getMessage());
                break;
            case CREATE_ACCOUNT_PHONE:
                break;
        }
    }


}


