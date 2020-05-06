package com.shonen.ukr.qtwitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SendTwitt extends AppCompatActivity implements View.OnClickListener {
    private EditText edtMassage;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_twitt);

        edtMassage = findViewById(R.id.edtSendtxt);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSend){
            ParseObject userTweet = new ParseObject("MyTweet");
            userTweet.put("tweet",edtMassage.getText().toString());
            userTweet.put("user", ParseUser.getCurrentUser().getUsername());

            userTweet.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        FancyToast.makeText(SendTwitt.this,ParseUser.getCurrentUser().getUsername() + " tweet: "+ edtMassage.getText().toString(),
                                Toast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                    }else{
                        FancyToast.makeText(SendTwitt.this,e.getMessage(),
                                Toast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                }
            });
        }
    }
}
