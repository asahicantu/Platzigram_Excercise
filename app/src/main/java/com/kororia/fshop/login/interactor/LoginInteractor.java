package com.kororia.fshop.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;
import com.kororia.fshop.model.User;

/**
 * Created by Asahi on 7/1/2017.
 */

public interface LoginInteractor {
    void createAccount(User user);

    void login(User user, Activity activity);

    void createAccountError(String message);

    void createAccountSuccess();

    void loginError(String message);

    void loginSuccess(FirebaseUser user);

}
