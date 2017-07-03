package com.asahicantu.platzigram_exercise.account.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.asahicantu.platzigram_exercise.R;
import com.asahicantu.platzigram_exercise.account.presenter.CreateAccountPresenter;
import com.asahicantu.platzigram_exercise.account.presenter.CreateAccountPresenterImpl;
import com.asahicantu.platzigram_exercise.view.MainActivity;

public class CreateAccountActivity extends AppCompatActivity implements  CreateAccountView {
    private CreateAccountPresenter _presenter;

    private TextInputEditText _txtMail;
    private TextInputEditText _txtUserName;
    private TextInputEditText _txtPassword;
    private TextInputEditText _txtConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        _presenter = new CreateAccountPresenterImpl(this);

        initializeControls();

    }

    private void initializeControls() {
        _txtMail = (TextInputEditText)findViewById(R.id.txtMail);
        _txtUserName = (TextInputEditText)findViewById(R.id.txtName);
        _txtPassword = (TextInputEditText)findViewById(R.id.txtPassword);
        _txtConfirmPassword= (TextInputEditText)findViewById(R.id.txtConfirmPassword);

    }


    public void createAccount(View view) {
        String email = _txtMail.getText().toString();
        String userName = _txtUserName.getText().toString();
        String password = _txtPassword.getText().toString();
        String confirmPassword = _txtConfirmPassword.getText().toString();
        _presenter.createAccount(userName,password,confirmPassword,email);
    }


    @Override
    public void creaateAccount() {

    }

    @Override
    public void showValidationError(int messageId) {
        String message = getString(messageId);
        TextInputEditText editor = null;
        switch (messageId){
            case R.string.message_emptyMail:
                editor = _txtMail;
                break;
            case R.id.txtName:
                editor = _txtUserName;
                break;
            case R.id.txtPassword:
                editor = _txtPassword;
            case R.id.txtConfirmPassword:
                editor = _txtConfirmPassword;
                break;
        }
        if(editor != null){
            editor.setError(message);
        }
    }

    public  void cleanErrors(){
        _txtMail.setError(null);
        _txtUserName.setError(null);
        _txtPassword.setError(null);
        _txtConfirmPassword.setError(null);
    }
}

