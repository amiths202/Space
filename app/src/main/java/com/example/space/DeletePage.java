package com.example.space;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeletePage extends AppCompatActivity {

    EditText userEmail;
    TextView res;
    String email;
    Button del;
    ImageButton back;
    ImageButton verify;
    DBHelper db;
    public static final String DBNAME = "spaceDB.db" ;
    public static final int DB_VERSION= 1 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);

        db = new DBHelper(this,DBNAME,null,DB_VERSION);
        userEmail = findViewById(R.id.userEmailDelete);
        verify = findViewById(R.id.deleteVerify);
        res = findViewById(R.id.noteDelete);
        del = findViewById(R.id.buttonDelete);
        back = findViewById(R.id.backButtonDelete);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = userEmail.getText().toString();
                if (db.chkemail(email)) {
                    res.setText("No user registered under this email" );
                } else {
                    String name = db.getNameDB(email);
                    res.setText("Delete " + name + " from our records?");
                    del.setEnabled(true);
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(email);
                if (db.chkemail(email)){
                    Toast.makeText(getApplicationContext(),email + " deleted",Toast.LENGTH_LONG).show();
                    userEmail.setText("");
                    res.setText("");
                    del.setEnabled(false);
                }else {
                    Toast.makeText(getApplicationContext(),"Delete Failed! Try Again",Toast.LENGTH_SHORT).show();
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
