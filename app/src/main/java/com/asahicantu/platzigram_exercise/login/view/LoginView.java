package com.asahicantu.platzigram_exercise.login.view;

import android.view.View;

/**
 * Created by Asahi on 7/1/2017.
 */

public interface LoginView {

    void createAccount(View view);

    void disableInputs();

    void enableInputs();

    void hideProgressBar();

    void goWebSite(View view);

    void login(View view);

    void loginSuccess();

    void loginError(String message);

    void showProgressBar();

}
