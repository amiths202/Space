package com.example.space;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePage extends AppCompatActivity {

    EditText userEmail,userName,userPassword,userNewEmail;
    TextView res;
    ImageButton back;
    String email,name,pass,newEmail;
    Button verify,update;
    DBHelper db;
    public static final String DBNAME = "spaceDB.db" ;
    public static final int DB_VERSION= 1 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        db = new DBHelper(this,DBNAME,null,DB_VERSION);
        userEmail = findViewById(R.id.userEmailUpdate);
        userNewEmail = findViewById(R.id.userEmailNewUpdate);
        userName = findViewById(R.id.userNameUpdate);
        userPassword = findViewById(R.id.userPasswordUpdate);
        res = findViewById(R.id.reportUpdate);
        verify = findViewById(R.id.verifyUpdate);
        update = findViewById(R.id.dataUpdate);
        back = findViewById(R.id.backButtonUpdate);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = userEmail.getText().toString();
                if(!(db.chkemail(email))){
                    res.setText("Verified!");
                    userNewEmail.setEnabled(true);
                    userName.setEnabled(true);
                    userPassword.setEnabled(true);
                    update.setEnabled(true);
                }
                else {
                    res.setText("Oops! No such email registered.");
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = userEmail.getText().toString();
                newEmail = userNewEmail.getText().toString();
                name = userName.getText().toString();
                pass = userPassword.getText().toString();
                db.update(name,email,newEmail,pass);
                if(db.chkemail(newEmail)){
                    Toast.makeText(getApplicationContext(),"Update Failed!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Updated!",Toast.LENGTH_SHORT).show();
                    userName.setText("");
                    userNewEmail.setText("");
                    res.setText("");
                    userNewEmail.setEnabled(false);
                    userName.setText("");
                    userName.setEnabled(false);
                    userPassword.setText("");
                    userPassword.setEnabled(false);
                    update.setEnabled(false);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

}
