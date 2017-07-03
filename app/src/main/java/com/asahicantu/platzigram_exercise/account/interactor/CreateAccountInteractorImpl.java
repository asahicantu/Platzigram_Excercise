package com.asahicantu.platzigram_exercise.account.interactor;

import com.asahicantu.platzigram_exercise.account.presenter.CreateAccountPresenter;

/**
 * Created by AMoreno15 on 7/3/2017.
 */

public class CreateAccountInteractorImpl implements CreateAccountInteractor {
    private CreateAccountPresenter presenter;
    public CreateAccountInteractorImpl(CreateAccountPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void createAccount(String userName, String password, String email) {
        //FirebaseAuth mAuth = FirebaseAuth.getInstance();
    }
}
