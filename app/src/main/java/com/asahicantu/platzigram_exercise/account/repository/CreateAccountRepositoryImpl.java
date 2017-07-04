package com.asahicantu.platzigram_exercise.account.repository;


import android.support.annotation.NonNull;
import android.util.Log;

import com.asahicantu.platzigram_exercise.account.interactor.CreateAccountInteractor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Asahi on 7/3/2017.
 */

public class CreateAccountRepositoryImpl implements CreateAccountRepository {
    private CreateAccountInteractor _interactor;

    public CreateAccountRepositoryImpl(CreateAccountInteractor _interactor) {
        this._interactor = _interactor;
    }

    @Override
    public void createAccount(final String email, final String password) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Task<AuthResult> createUserResultTask = mAuth.createUserWithEmailAndPassword(email, password);

        createUserResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("ACCOUNT", "createUserWithEmail:success");
                    Task<AuthResult> signUserResultTask = mAuth.signInWithEmailAndPassword(email, password);
                    signUserResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                _interactor.createAccountSuccess(user);
                            } else {
                                _interactor.createAccountError(task.getException().getMessage());
                            }
                        }
                    });
                } else {
                    _interactor.createAccountError(task.getException().getMessage());
                }
            }
        });
    }
}
