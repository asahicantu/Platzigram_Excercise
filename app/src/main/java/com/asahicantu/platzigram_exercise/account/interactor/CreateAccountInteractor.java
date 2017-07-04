package com.asahicantu.platzigram_exercise.account.interactor;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by AMoreno15 on 7/3/2017.
 */

public interface CreateAccountInteractor {

    void createAccount(String userName, String password, String email);

    void createAccountSuccess(FirebaseUser user);

    void createAccountError(String message);
}
