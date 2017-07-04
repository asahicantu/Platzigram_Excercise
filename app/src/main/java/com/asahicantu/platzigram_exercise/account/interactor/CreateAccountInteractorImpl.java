package com.asahicantu.platzigram_exercise.account.interactor;

import com.asahicantu.platzigram_exercise.account.presenter.CreateAccountPresenter;
import com.asahicantu.platzigram_exercise.account.repository.CreateAccountRepository;
import com.asahicantu.platzigram_exercise.account.repository.CreateAccountRepositoryImpl;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by AMoreno15 on 7/3/2017.
 */

public class CreateAccountInteractorImpl implements CreateAccountInteractor {
    private CreateAccountPresenter _presenter;
    private CreateAccountRepository _repository;

    public CreateAccountInteractorImpl(CreateAccountPresenter _presenter) {
        this._presenter = _presenter;
        this._repository = new CreateAccountRepositoryImpl(this);
    }

    @Override
    public void createAccount(final String userName, final String password, final String email) {
        _repository.createAccount(email, password);
    }

    @Override
    public void createAccountSuccess(FirebaseUser user) {
        _presenter.createAccountSuccess(user);
    }

    @Override
    public void createAccountError(String message) {
        _presenter.createAccountError(message);
    }
}
