package com.shonen.ukr.qtwitterclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class TwitterUsers extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<String> usersArr;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_users);
        setTitle("Twitter users");

        listView = findViewById(R.id.idListView);
        usersArr = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_checked,usersArr);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(this);

        try {
            ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
            parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
            parseQuery.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> objects, ParseException e) {
                    if(e==null){
                        if(objects.size()>0){
                            for(ParseUser user:objects){
                                usersArr.add(user.getUsername());
                            }
                            listView.setAdapter(arrayAdapter);

                            for(String twitterUsrr:usersArr){

                                if (ParseUser.getCurrentUser().getList("fanOf")!=null) {
                                    if(ParseUser.getCurrentUser().getList("fanOf").contains(twitterUsrr)){
                                        listView.setItemChecked(usersArr.indexOf(twitterUsrr),true);
                                    }
                                }
                            }

                        }
                    }
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        FancyToast.makeText(TwitterUsers.this, "Welcome " + ParseUser.getCurrentUser().getUsername(), Toast.LENGTH_SHORT, FancyToast.INFO, false).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logOutItem){
            ParseUser.getCurrentUser().logOut();
            finish();
            Intent intent = new Intent(TwitterUsers.this,SingUp.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.sendTwitt){
            Intent intent = new Intent(TwitterUsers.this,SendTwitt.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        CheckedTextView checkedTextView = (CheckedTextView) view;

        if(checkedTextView.isChecked()){
            FancyToast.makeText(TwitterUsers.this,usersArr.get(position) + " is now followed",Toast.LENGTH_SHORT,FancyToast.INFO,false).show();
            ParseUser.getCurrentUser().add("fanOf", usersArr.get(position));
        }else {
            FancyToast.makeText(TwitterUsers.this,usersArr.get(position) + " is not followed",Toast.LENGTH_SHORT,FancyToast.INFO,false).show();
            ParseUser.getCurrentUser().getList("fanOf").remove(usersArr.get(position));
            List currentUserFanOFList = ParseUser.getCurrentUser().getList("fanOf");
            ParseUser.getCurrentUser().remove("fanOf");
            ParseUser.getCurrentUser().put("fanOf",currentUserFanOFList);
        }
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null) {
                    FancyToast.makeText(TwitterUsers.this," Saved",Toast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                }

            }
        });
    }
}
