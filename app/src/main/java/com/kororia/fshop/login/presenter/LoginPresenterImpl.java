package com.kororia.fshop.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;
import com.kororia.fshop.R;
import com.kororia.fshop.login.interactor.LoginInteractor;
import com.kororia.fshop.login.interactor.LoginInteractorImpl;
import com.kororia.fshop.login.view.LoginView;
import com.kororia.fshop.model.User;

/**
 * Created by Asahi on 7/1/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView _view;
    private LoginInteractor _interactor;

    public LoginPresenterImpl(LoginView view) {
        this._view = view;
        _interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void loginError(String message) {
        _view.enableOrDisableControls(true);
        _view.hideProgressBar();
        _view.loginError(message);
    }

    @Override
    public void createAccount(User user) {
        _view.showProgressBar();
        _view.enableOrDisableControls(false);
        boolean isValid = validateEmailAndPassword(user);
        if (isValid) {
            _interactor.createAccount(user);
        } else {
            _view.enableOrDisableControls(true);
            _view.hideProgressBar();
        }
    }

    @Override
    public void createAccountSuccess() {
        _view.createAccountSuccess();
    }

    @Override
    public void loginSuccess(FirebaseUser user) {
        _view.loginSuccess(user);
    }

    @Override
    public void login(User user, Activity activity) {
        _view.enableOrDisableControls(false);
        _view.showProgressBar();
        _interactor.login(user, activity);
    }

    @Override
    public void createAccountError(String message) {
        _view.hideProgressBar();
        _view.enableOrDisableControls(true);
        _view.createAccountError(message);
    }

    private boolean validateEmailAndPassword(User user) {
        boolean retVal = true;
        if (user.getUserName().isEmpty()) {
            _view.showValidationError(R.string.message_emptyUserName);
            retVal = false;
        }
        if (user.getEmail().isEmpty()) {
            _view.showValidationError(R.string.message_emptyMail);
            retVal = false;
        }
        if (user.getPassword().isEmpty()) {
            _view.showValidationError(R.string.message_emptyPassword);
            retVal = false;
        }
        if (user.getConfirmPassword().isEmpty()) {
            _view.showValidationError(R.string.message_emptyConfirmPassword);
            retVal = false;
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            _view.showValidationError(R.string.message_passwordMismatch);
            retVal = false;
        }
        return retVal;
    }

}
