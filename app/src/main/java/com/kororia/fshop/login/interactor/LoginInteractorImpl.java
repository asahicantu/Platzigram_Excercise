package com.kororia.fshop.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;
import com.kororia.fshop.login.presenter.LoginPresenter;
import com.kororia.fshop.login.repository.LoginRepository;
import com.kororia.fshop.login.repository.LoginRepositoryImpl;
import com.kororia.fshop.model.User;

/**
 * Created by Asahi on 7/1/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private LoginPresenter _presenter;
    private LoginRepository _repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this._presenter = presenter;
        this._repository = new LoginRepositoryImpl(this);
    }

    @Override
    public void createAccount(User user) {
        _repository.createAccount(user);
    }

    @Override
    public void login(User user, Activity activity) {
        _repository.login(user, activity);
    }

    @Override
    public void createAccountSuccess() {
        _presenter.createAccountSuccess();
    }

    @Override
    public void loginSuccess(FirebaseUser user) {
        _presenter.loginSuccess(user);
    }

    @Override
    public void loginError(String message) {
        _presenter.loginError(message);
    }

    @Override
    public void createAccountError(String message) {
        _presenter.createAccountError(message);
    }
}
