package com.asahicantu.platzigram_exercise.account.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asahicantu.platzigram_exercise.R;
import com.asahicantu.platzigram_exercise.account.presenter.CreateAccountPresenter;
import com.asahicantu.platzigram_exercise.account.presenter.CreateAccountPresenterImpl;
import com.asahicantu.platzigram_exercise.home.view.HomeFragment;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity implements  CreateAccountView {
    private CreateAccountPresenter _presenter;

    private TextInputEditText _txtMail;
    private TextInputEditText _txtUserName;
    private TextInputEditText _txtPassword;
    private TextInputEditText _txtConfirmPassword;
    private Button _btnCreateAccount;

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
        _btnCreateAccount = (Button) findViewById(R.id.BtnCreateAccount);
    }


    public void createAccount(View view) {
        cleanErrors();
        enableOrDisableControls(false);
        String email = _txtMail.getText().toString();
        String userName = _txtUserName.getText().toString();
        String password = _txtPassword.getText().toString();
        String confirmPassword = _txtConfirmPassword.getText().toString();
        _presenter.createAccount(userName,password,confirmPassword,email);
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

    @Override
    public void createAccountSuccess(FirebaseUser user) {
        Toast.makeText(this, getString(R.string.message_userCreateSuccess), Toast.LENGTH_LONG);
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
    }

    @Override
    public void createAccountError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT);
        enableOrDisableControls(true);
    }

    public  void cleanErrors(){
        _txtMail.setError(null);
        _txtUserName.setError(null);
        _txtPassword.setError(null);
        _txtConfirmPassword.setError(null);
    }

    public void enableOrDisableControls(boolean enable) {
        _txtMail.setEnabled(enable);
        _txtUserName.setEnabled(enable);
        _txtPassword.setEnabled(enable);
        _txtConfirmPassword.setEnabled(enable);
        _btnCreateAccount.setEnabled(enable);
    }
}

