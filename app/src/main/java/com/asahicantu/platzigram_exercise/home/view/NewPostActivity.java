package com.asahicantu.platzigram_exercise.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.asahicantu.platzigram_exercise.R;
import com.squareup.picasso.Picasso;

public class NewPostActivity extends AppCompatActivity {

    public static final String IMAGE_PATH = "IMAGE_PATH";
    private ImageView _imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);


        _imgPhoto = (ImageView) findViewById(R.id.imgPhoto);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String imagePath = extras.getString(IMAGE_PATH);
            Picasso.with(this).load(imagePath).into(_imgPhoto);
        }
    }

    public void createPost(View view) {

    }
}
