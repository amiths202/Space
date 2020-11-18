package com.example.space;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText userEmail,userPass;
    Button login;
    TextView notRegistered;
    DBHelper db;
    String email,password;
    public static final String DBNAME = "spaceDB.db" ;
    public static final String TBNAME = "spaceUsers" ;
    public static final int DB_VERSION= 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this,DBNAME,null,DB_VERSION);
        userEmail = findViewById(R.id.userEmailLogin);
        userPass = findViewById(R.id.userPasswordLogin);
        notRegistered = findViewById(R.id.userNotRegistered);
        login = findViewById(R.id.userSignIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = userEmail.getText().toString();
                password = userPass.getText().toString();
                if(db.emailPass(email,password)){
                    Intent i = new Intent(getApplicationContext(), WelcomePage.class);
                    i.putExtra("userEmail",email);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

        notRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistrationPage.class);
                startActivity(intent);
            }
        });


    }
}