package com.asahicantu.platzigram_exercise.account.presenter;

import com.asahicantu.platzigram_exercise.account.interactor.CreateAccountInteractor;
import com.asahicantu.platzigram_exercise.account.interactor.CreateAccountInteractorImpl;
import com.asahicantu.platzigram_exercise.account.view.CreateAccountView;
import com.asahicantu.platzigram_exercise.R;

/**
 * Created by AMoreno15 on 7/3/2017.
 */

public class CreateAccountPresenterImpl implements  CreateAccountPresenter {
    private CreateAccountView _view;
    private CreateAccountInteractor _interactor;
    public CreateAccountPresenterImpl(CreateAccountView view) {
        _view = view;
        _interactor = new CreateAccountInteractorImpl(this);


    }

    @Override
    public void createAccount(String userName,String email,String password,String confirmPassword) {
        boolean isValid = ValidateFields(userName, password, confirmPassword);
        if(isValid){
            _interactor.createAccount(userName,password,email);
        }

    }

    private boolean  ValidateFields(String userName, String password, String confirmPassword) {
        boolean isValid = true;
        if(userName.isEmpty()){
            _view.showValidationError(R.string.message_emptyUserName);
            isValid = false;
        }
        if(password.isEmpty()){
            _view.showValidationError(R.string.message_emptyPassword);
            isValid = false;
        }
        if(confirmPassword.isEmpty()){
            _view.showValidationError(R.string.message_wrongOrEmptyPassword);
            isValid = false;
        }
        if(!password.equals(confirmPassword)){
            _view.showValidationError(R.string.message_passwordMismatch);
            isValid = false;
        }
        return isValid;
    }
}
