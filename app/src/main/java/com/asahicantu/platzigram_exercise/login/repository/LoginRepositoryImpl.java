package com.asahicantu.platzigram_exercise.login.repository;

import android.content.res.Resources;

import com.asahicantu.platzigram_exercise.R;
import com.asahicantu.platzigram_exercise.login.presenter.LoginPresenter;

/**
 * Created by Asahi on 7/1/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {
    private LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login(String userName, String password) {
        try {
            boolean loginsuccess = true;
            if (loginsuccess) {
                presenter.loginSuccess();
            } else {
                String errorMessage = Resources.getSystem().getString(R.string.message_userNameNotFoud);
                presenter.loginError(errorMessage);
            }
        } catch (Exception e) {
            presenter.loginError(e.getMessage());
        }
    }
}
