package com.asahicantu.platzigram_exercise.login.interactor;

import com.asahicantu.platzigram_exercise.login.presenter.LoginPresenter;
import com.asahicantu.platzigram_exercise.login.repository.LoginRepository;
import com.asahicantu.platzigram_exercise.login.repository.LoginRepositoryImpl;

/**
 * Created by Asahi on 7/1/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        this.repository = new LoginRepositoryImpl(presenter);
    }

    @Override
    public void Login(String user, String password) {
        repository.login(user, password);
    }
}
