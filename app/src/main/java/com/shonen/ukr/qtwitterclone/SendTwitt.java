package com.shonen.ukr.qtwitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SendTwitt extends AppCompatActivity implements View.OnClickListener {
    private EditText edtMassage;
    private Button btnSend;
    private Button btnShowTweets;
    private ListView listOfTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_twitt);

        edtMassage = findViewById(R.id.edtSendtxt);
        btnSend = findViewById(R.id.btnSend);
        btnShowTweets = findViewById(R.id.btnShowTwits);
        listOfTweets = findViewById(R.id.listOfTweets);
        btnSend.setOnClickListener(this);
        btnShowTweets.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSend) {
            ParseObject userTweet = new ParseObject("MyTweet");
            userTweet.put("tweet", edtMassage.getText().toString());
            userTweet.put("user", ParseUser.getCurrentUser().getUsername());

            userTweet.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SendTwitt.this, ParseUser.getCurrentUser().getUsername() + " tweet: " + edtMassage.getText().toString(),
                                Toast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                    } else {
                        FancyToast.makeText(SendTwitt.this, e.getMessage(),
                                Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    }
                }
            });
        } else if (v.getId() == R.id.btnShowTwits) {
            final ArrayList<HashMap<String, String>> tweetList = new ArrayList<>();
            final SimpleAdapter adapter = new SimpleAdapter(SendTwitt.this, tweetList, android.R.layout.simple_list_item_2,
                    new String[]{"tweetUserName", "tweetValue"}, new int[]{android.R.id.text1, android.R.id.text2});
            try {
                ParseQuery parseQuery = ParseQuery.getQuery("MyTweet");
                parseQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                            if(objects.size()>0&&e==null){
                                for(ParseObject tweet:objects){
                                    HashMap<String,String> userTweet = new HashMap<>();
                                    userTweet.put("tweenUserName",tweet.getString("user"));
                                    userTweet.put("tweetValue",tweet.getString("tweet"));
                                    tweetList.add(userTweet);
                                }
                            }
                             listOfTweets.setAdapter(adapter);
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
