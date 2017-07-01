package com.asahicantu.platzigram_exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.asahicantu.platzigram_exercise.R;
import com.asahicantu.platzigram_exercise.activity.CreateAccountActivity;
import com.asahicantu.platzigram_exercise.activity.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void newAccount(View view) {
        Intent intent = new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
    }
}

