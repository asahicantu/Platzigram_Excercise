package com.asahicantu.platzigram_exercise.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.asahicantu.platzigram_exercise.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }


    public void createAccount(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
