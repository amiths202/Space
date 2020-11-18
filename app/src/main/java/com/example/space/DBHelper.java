package com.example.space;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static android.widget.Toast.*;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "spaceAppDB.db" ;
    public static final String TBNAME = "spaceUsers" ;
    public static final int DB_VERSION= 1 ;
    
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TBNAME+ "(_id Integer Primary Key AutoIncrement,userName text, userEmail text, userPass text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TBNAME);
        onCreate(db);
    }

    public boolean adduser(String name, String email, String pass){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        ContentValues cv = new ContentValues() ;
        cv.put("userName",name);
        cv.put("userEmail",email);
        cv.put("userPass",pass);
        long result =  sqLiteDatabase.insert(TBNAME,null,cv);
        if (result==-1) {
            sqLiteDatabase.close();
            return false;
        }
        else {
            sqLiteDatabase.close();
            return true;
        }

    }

    public void delete(String email){
        SQLiteDatabase db = this.getWritableDatabase() ;
//        db.execSQL("DELETE FROM "+TBNAME+ " WHERE userEmail="+email+";");
        db.delete(TBNAME,"userEmail=?",new String[]{email});
        db.close();
    }
    public void update(String name,String email ,String newEmail,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
//        //Cursor cur = db.rawQuery("update "+TBNAME+" set userName ='"+name+"',"+ "userPass = '"+pass+"';",null);
//        db.execSQL("UPDATE " +TBNAME+ " SET userName=" +name+ ", userPass=" +pass+ ", userEmail=" +newEmail+ " WHERE userEmail=" +email+ ";");
        ContentValues cv = new ContentValues() ;
        cv.put("userName",name);
        cv.put("userEmail",newEmail);
        cv.put("userPass",pass);
        db.update(TBNAME,cv,"userEmail=?",new String[]{email});
        db.close();
    }

//    public String display(Context ctx){
//        SQLiteDatabase db = this.getReadableDatabase() ;
//        Cursor cur = db.rawQuery("SELECT * FROM "+TBNAME+";",null);
//        String finalres =" ";
//        while(cur.moveToNext()){
//            finalres = cur.getString(1)+":"+cur.getString(2) ;
//        }
//        return finalres ;
//    }

    public Cursor display (){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM spaceUsers";
        Cursor cur = db.rawQuery(query,null );
        return cur;
    }

    public Boolean emailPass(String email, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TBNAME + " WHERE userEmail=? AND userPass=?", new String[]{email,pass});
        if (cur.getCount()>0) return true;
        else return false;
    }
    public String getNameDB(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String name = "";
        try (Cursor cur = db.rawQuery("SELECT userName FROM " + TBNAME + " WHERE userEmail=?", new String[]{email})) {
            if (cur.getCount() > 0) {
                cur.moveToFirst();
                name = cur.getString(cur.getColumnIndex("userName"));
            };
            return name;
        }
    }


    public boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TBNAME + " WHERE userEmail=?",new String[]{email});
        if (cur.getCount()>0) return false; //record exists
        else return true;
    }
}
