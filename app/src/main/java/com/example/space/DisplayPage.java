package com.example.space;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayPage extends AppCompatActivity {

    DBHelper db;
    UserCursorAdapter displayAdapter;
    Cursor displayCur;
    ImageButton back;
    public static final String DBNAME = "spaceDB.db" ;
    public static final int DB_VERSION= 1 ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        db = new DBHelper(this,DBNAME,null,DB_VERSION);
        ListView detailList = findViewById(R.id.detailsList);
        back = findViewById(R.id.backButtonDetails);

        displayCur = db.display();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                displayAdapter = new UserCursorAdapter(getApplicationContext(), displayCur);
                detailList.setAdapter(displayAdapter);
            }
        });

//      displayAdapter.changeCursor(displayCur);


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
