package com.kororia.fshop.login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.kororia.fshop.R;
import com.kororia.fshop.login.presenter.LoginPresenter;
import com.kororia.fshop.login.presenter.LoginPresenterImpl;
import com.kororia.fshop.model.User;
import com.kororia.fshop.view.BaseActivity;
import com.kororia.fshop.view.MainActivity;
import com.wang.avi.AVLoadingIndicatorView;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    private LoginPresenter presenter;
    private TextInputEditText _txtLogin;
    private TextInputEditText _txtPassword;
    private Button btnLogin;
    private AVLoadingIndicatorView progressIndicator;

    private TextView txtCreateAccount;
    private TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "LoginActivity";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenterImpl(this);
        initializeControls();
        presenter.login(null, this);
        enableOrDisableControls(true);
    }

    private void initializeControls() {
        _txtLogin = (TextInputEditText) findViewById(R.id.txtMail);
        _txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        progressIndicator = (AVLoadingIndicatorView) findViewById(R.id.progressIndicator);
        txtError = (TextView) findViewById(R.id.txtError);
        txtError.setText("");
        txtCreateAccount = (TextView) findViewById(R.id.txtCreateAccount);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        txtCreateAccount.setOnClickListener(this);
        hideProgressBar();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String email = _txtLogin.getText().toString();
                String password = _txtPassword.getText().toString();
                User user = new User(email, password);
                presenter.login(user, this);
                break;
            case R.id.txtCreateAccount:
                Intent intent = new Intent(this, CreateAccountActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void goWebSite(View view) {
        String url = "https://platzi.com";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    @Override
    public void showProgressBar() {
        progressIndicator.show();
    }


    @Override
    public void hideProgressBar() {
        progressIndicator.hide();
    }

    @Override
    public void showValidationError(int messageId) {
        String message = getString(messageId);
        TextInputEditText editor = null;
        switch (messageId) {
            case R.string.message_emptyMail:
                editor = _txtLogin;
                break;
            case R.string.message_emptyPassword:
                editor = _txtPassword;
                break;
        }
        if (editor != null) {
            editor.setError(message);
        }
    }

    @Override
    public void loginSuccess(FirebaseUser user) {
        Toast.makeText(this, R.string.message_login_success, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void enableOrDisableControls(boolean enable) {
        _txtLogin.setEnabled(enable);
        _txtPassword.setEnabled(enable);
        txtCreateAccount.setEnabled(enable);
        btnLogin.setEnabled(enable);
        txtError.setEnabled(enable);
    }

    @Override
    public void loginError(String message) {
        txtError.setText(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createAccountError(String message) {

    }

    @Override
    public void createAccountSuccess() {

    }


}

