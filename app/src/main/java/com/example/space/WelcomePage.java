package com.example.space;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomePage extends AppCompatActivity {

    Button details,register,update,delete;

    Intent intent;
    String username,userEmail;
    DBHelper db;
    TextView userName;
    ImageButton back;
    public static final String DBNAME = "spaceDB.db" ;
    public static final int DB_VERSION= 1 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        db = new DBHelper(this,DBNAME,null,DB_VERSION);
        userEmail = getIntent().getExtras().getString("userEmail");
        username = db.getNameDB(userEmail);
        userName = findViewById(R.id.userNameWelcome);
        userName.setText(username +" here's some things you can do" );

        details = findViewById(R.id.viewDetailsWelcome);
        register = findViewById(R.id.registerNewWelcome);
        update = findViewById(R.id.upadteWelcome);
        delete = findViewById(R.id.deleteUserWelcome);
        back = findViewById(R.id.exitButtonwelcome);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(WelcomePage.this, RegistrationPage.class) ;
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(WelcomePage.this, DeletePage.class) ;
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(WelcomePage.this, UpdatePage.class) ;
                startActivity(intent);
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(WelcomePage.this, DisplayPage.class);
                startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}