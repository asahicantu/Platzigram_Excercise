package com.kororia.fshop.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.kororia.fshop.R;
import com.kororia.fshop.login.presenter.LoginPresenter;
import com.kororia.fshop.login.presenter.LoginPresenterImpl;
import com.kororia.fshop.model.AccountOption;
import com.kororia.fshop.model.User;
import com.kororia.fshop.view.MainActivity;
import com.wang.avi.AVLoadingIndicatorView;

public class CreateAccountActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    private LoginPresenter _presenter;

    private TextInputEditText _txtMail;
    private TextInputEditText _txtUserName;
    private TextInputEditText _txtPassword;
    private TextInputEditText _txtConfirmPassword;
    private AVLoadingIndicatorView _loadingIndicator;
    private Button _btnCreateAccount;

    private int _loginTrialCount = 3;

    private User _user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        _presenter = new LoginPresenterImpl(this);
        initializeControls();
    }

    private void initializeControls() {
        _txtMail = (TextInputEditText)findViewById(R.id.txtMail);
        _txtUserName = (TextInputEditText)findViewById(R.id.txtName);
        _txtPassword = (TextInputEditText)findViewById(R.id.txtPassword);
        _txtConfirmPassword= (TextInputEditText)findViewById(R.id.txtConfirmPassword);
        _loadingIndicator = (AVLoadingIndicatorView) findViewById(R.id.progressIndicator);

        _btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        _btnCreateAccount.setOnClickListener(this);
        hideProgressBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateAccount:
                createAccount();
                break;
        }

    }

    public void createAccount() {
        String email = _txtMail.getText().toString();
        String userName = _txtUserName.getText().toString();
        String password = _txtPassword.getText().toString();
        String confirmPassword = _txtConfirmPassword.getText().toString();
        _user = new User(email, userName, password, confirmPassword, null, AccountOption.EmailAndPassword);
        _presenter.createAccount(_user);
    }

    @Override
    public void createAccountSuccess() {
        Toast.makeText(this, getString(R.string.message_userCreateSuccess), Toast.LENGTH_LONG).show();
        _presenter.login(_user, this);
    }

    @Override
    public void loginSuccess(FirebaseUser user) {
        Toast.makeText(this, getString(R.string.message_login_success), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        if (_loginTrialCount > 0) {
            _presenter.login(_user, this);
            _loginTrialCount--;
        }
    }

    @Override
    public void showProgressBar() {
        _loadingIndicator.show();
    }

    @Override
    public void hideProgressBar() {
        _loadingIndicator.hide();
    }

    @Override
    public void createAccountError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showValidationError(int messageId) {
        String message = getString(messageId);
        TextInputEditText editor = null;
        switch (messageId){
            case R.string.message_emptyMail:
                editor = _txtMail;
                break;
            case R.string.message_emptyUserName:
                editor = _txtUserName;
                break;
            case R.string.message_emptyPassword:
                editor = _txtPassword;
                break;
            case R.string.message_emptyConfirmPassword:
            case R.string.message_passwordMismatch:
                editor = _txtConfirmPassword;
                break;
        }
        if(editor != null){
            editor.setError(message);
        }
    }


    @Override
    public void enableOrDisableControls(boolean enable) {
        _txtMail.setEnabled(enable);
        _txtUserName.setEnabled(enable);
        _txtPassword.setEnabled(enable);
        _txtConfirmPassword.setEnabled(enable);
        _btnCreateAccount.setEnabled(enable);
    }
}

