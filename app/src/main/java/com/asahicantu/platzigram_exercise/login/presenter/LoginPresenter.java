package com.asahicantu.platzigram_exercise.login.presenter;

/**
 * Created by Asahi on 7/1/2017.
 */

public interface LoginPresenter {

    void createAccount();

    void disableInputs();

    void enableInputs();

    void hideProgressBar();

    void login(String user, String password);

    void loginSuccess();

    void loginError(String message);

    void showProgressBar();
}
