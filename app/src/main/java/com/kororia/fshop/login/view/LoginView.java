package com.kororia.fshop.login.view;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Asahi on 7/1/2017.
 */

public interface LoginView {

    void loginError(String message);

    void showProgressBar();

    void hideProgressBar();

    void showValidationError(int messageId);

    void createAccountSuccess();

    void loginSuccess(FirebaseUser user);

    void createAccountError(String message);

    void enableOrDisableControls(boolean enable);

}
