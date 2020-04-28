package com.shonen.ukr.qtwitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shonen.ukr.qtwitterclone.R;

public class SingUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUserName;
    private EditText edtUserEmail;
    private EditText edtUserPassword;

    private Button btnSinUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        edtUserName = findViewById(R.id.singUpUserName);
        edtUserEmail = findViewById(R.id.singUpUserEMail);
        edtUserPassword = findViewById(R.id.singUpUserPassword);
        btnSinUp = findViewById(R.id.btnSingUpSU);
        btnSinUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSingUpSU:
                final ParseUser addNewUser = new ParseUser();
                addNewUser.setUsername(edtUserName.getText().toString());
                addNewUser.setEmail(edtUserEmail.getText().toString());
                addNewUser.setPassword(edtUserPassword.getText().toString());
                addNewUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(SingUp.this,"Done",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SingUp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
