package com.kororia.fshop.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;
import com.kororia.fshop.model.User;

/**
 * Created by Asahi on 7/1/2017.
 */

public interface LoginPresenter {

    void createAccount(User user);

    void createAccountSuccess();

    void loginSuccess(FirebaseUser user);
    void loginError(String message);

    void login(User user, Activity activity);

    void createAccountError(String message);
}
