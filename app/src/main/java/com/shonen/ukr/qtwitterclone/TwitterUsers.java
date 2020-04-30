package com.shonen.ukr.qtwitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class TwitterUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_users);

        FancyToast.makeText(TwitterUsers.this,"Welcome!", Toast.LENGTH_SHORT,FancyToast.INFO,false).show();

    }
}
