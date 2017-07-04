package com.asahicantu.platzigram_exercise.account.presenter;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by AMoreno15 on 7/3/2017.
 */

public interface CreateAccountPresenter {

    void createAccount(String userName,String email,String password,String confirmPassword);

    void createAccountSuccess(FirebaseUser user);

    void createAccountError(String message);
}
