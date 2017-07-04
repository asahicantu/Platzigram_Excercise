package com.asahicantu.platzigram_exercise.login.repository;

import android.support.annotation.NonNull;

import com.asahicantu.platzigram_exercise.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Asahi on 7/1/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {
    private LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login(String email, String password) {
        try {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            Task<AuthResult> signInResultTask = mAuth.signInWithEmailAndPassword(email, password);
            signInResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        presenter.loginSuccess();
                    } else {
                        //String errorMessage = Resources.getSystem().getString(R.string.message_userNameNotFoud);
                        presenter.loginError(task.getException().toString());
                    }
                }
            });
        } catch (Exception e) {
            presenter.loginError(e.getMessage());
        }
    }
}
