package com.shonen.ukr.qtwitterclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class TwitterUsers extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_users);
        setTitle("Twitter users");
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);


        FancyToast.makeText(TwitterUsers.this, "Welcome!", Toast.LENGTH_SHORT, FancyToast.INFO, false).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logOutItem){
            ParseUser.getCurrentUser().logOut();
            finish();
            Intent intent = new Intent(TwitterUsers.this,LogIn.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
