package com.asahicantu.platzigram_exercise.login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asahicantu.platzigram_exercise.R;
import com.asahicantu.platzigram_exercise.login.presenter.LoginPresenter;
import com.asahicantu.platzigram_exercise.login.presenter.LoginPresenterImpl;
import com.asahicantu.platzigram_exercise.view.CreateAccountActivity;
import com.asahicantu.platzigram_exercise.view.MainActivity;
import com.wang.avi.AVLoadingIndicatorView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;
    private TextInputEditText txtUserName;
    private TextInputEditText txtPassword;
    private Button btnLogin;
    private AVLoadingIndicatorView progressIndicator;

    private TextView txtCreateAccount;
    private TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenterImpl(this);
        initializeControls();
    }

    private void initializeControls() {
        txtUserName = (TextInputEditText) findViewById(R.id.txtUserName);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        txtCreateAccount = (TextView) findViewById(R.id.txtCreateAccount);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        progressIndicator = (AVLoadingIndicatorView) findViewById(R.id.progressIndicator);
        txtError = (TextView) findViewById(R.id.txtError);
        txtError.setText("");
        hideProgressBar();
    }

    private void setControlsEnabledorDisabled(boolean enable) {
        txtUserName.setEnabled(enable);
        txtPassword.setEnabled(enable);
        txtCreateAccount.setEnabled(enable);
        btnLogin.setEnabled(enable);
        txtError.setEnabled(enable);
    }

    @Override
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
    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);

    }

    @Override
    public void disableInputs() {
        setControlsEnabledorDisabled(false);
    }

    @Override
    public void enableInputs() {
        setControlsEnabledorDisabled(true);
    }

    @Override
    public void hideProgressBar() {
        progressIndicator.hide();
    }

    @Override
    public void login(View view) {
        String userName = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        if (userName.isEmpty() || password.isEmpty()) {
            loginError(getString((R.string.message_userNameOrPasswordNull)));
        } else {
            presenter.login(userName, password);
        }
    }


    @Override
    public void loginError(String message) {
        txtError.setText(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

