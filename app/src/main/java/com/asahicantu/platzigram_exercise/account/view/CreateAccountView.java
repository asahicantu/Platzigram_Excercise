package com.asahicantu.platzigram_exercise.account.view;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by AMoreno15 on 7/3/2017.
 */

public interface CreateAccountView {

    void showValidationError(int messageId);

    void createAccountSuccess(FirebaseUser user);

    void createAccountError(String message);
}
