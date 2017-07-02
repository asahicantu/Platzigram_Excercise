package com.asahicantu.platzigram_exercise.login.presenter;

import com.asahicantu.platzigram_exercise.login.interactor.LoginInteractor;
import com.asahicantu.platzigram_exercise.login.interactor.LoginInteractorImpl;
import com.asahicantu.platzigram_exercise.login.view.LoginView;

/**
 * Created by Asahi on 7/1/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void login(String user, String password) {
        view.disableInputs();
        view.showProgressBar();
        interactor.Login(user, password);
    }

    @Override
    public void enableInputs() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void disableInputs() {

    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void loginSuccess() {
        view.loginSuccess();
    }

    @Override
    public void loginError(String message) {
        view.enableInputs();
        view.hideProgressBar();
        view.loginError(message);
    }


    @Override
    public void createAccount() {

    }

}
