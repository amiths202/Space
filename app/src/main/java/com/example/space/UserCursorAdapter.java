package com.example.space;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class UserCursorAdapter extends CursorAdapter {
    public UserCursorAdapter(Context context, Cursor c) {
        super(context, c,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_template,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = view.findViewById(R.id.userNameTemplate);
        TextView email = view.findViewById(R.id.userEmailTemplate);

        String uName = cursor.getString(cursor.getColumnIndexOrThrow("userName"));
        String uEmail = cursor.getString(cursor.getColumnIndexOrThrow("userEmail"));

        name.setText(uName);
        email.setText(uEmail);
    }
}
