package com.example.space;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationPage extends AppCompatActivity {
    EditText userName,userEmail,userPass;
    String name,email,pass;
    Button reg;
    DBHelper db;
    ImageButton back;
    public static final String DBNAME = "spaceDB.db" ;
    public static final int DB_VERSION= 1 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration);

        db = new DBHelper(this,DBNAME,null,DB_VERSION);
        userName = findViewById(R.id.userNameRegister);
        userEmail = findViewById(R.id.userEmailRegister);
        userPass = findViewById(R.id.userPasswordResgister);
        reg = findViewById(R.id.userButtonRegister);
        back = findViewById(R.id.backButtonRegister);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = userEmail.getText().toString();
                name = userName.getText().toString();
                pass = userPass.getText().toString();

                if (email.equals("")||name.equals("")||pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter valid details",Toast.LENGTH_SHORT).show();
                }else {
                    if(db.chkemail(email)){
                       if (db.adduser(name,email,pass)){
                           Toast.makeText(getApplicationContext(),"Registeration Successful",Toast.LENGTH_SHORT).show();
                           userEmail.setText("");
                           userName.setText("");
                           userPass.setText("");
                       }else {
                           Toast.makeText(getApplicationContext(),"Registeration Failed",Toast.LENGTH_SHORT).show();
                       }
                    }else {
                        Toast.makeText(getApplicationContext(),"Email Already Exists",Toast.LENGTH_LONG).show();
                        userEmail.setText("");
                        userName.setText("");
                        userPass.setText("");

                    }
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
