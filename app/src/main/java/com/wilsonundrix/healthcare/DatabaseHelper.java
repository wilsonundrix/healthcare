package com.wilsonundrix.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "health.db";
    public static final String tbl_students = "students";
    public static final String tbl_doctors = "doctors";
    public static final String tbl_login = "login_details";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tbl_students + "( student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_first TEXT, student_last TEXT, student_email TEXT UNIQUE,student_reg_no TEXT UNIQUE, student_gender TEXT)");
        db.execSQL("CREATE TABLE " + tbl_doctors + "( doctor_id INTEGER PRIMARY KEY AUTOINCREMENT, doctor_first TEXT, doctor_last TEXT, doctor_email TEXT UNIQUE,doctor_staff_id TEXT UNIQUE, doctor_gender TEXT)");
        db.execSQL("CREATE TABLE " + tbl_login + "( user_id INTEGER, username TEXT UNIQUE, password TEXT, usertype TEXT)");

        db.execSQL("INSERT INTO " + tbl_login + " (user_id,username,password,usertype) values (1,'sylvester','1001','Student')");
        db.execSQL("INSERT INTO " + tbl_login + " (user_id,username,password,usertype) values (2,'sam','2002','Student')");
        db.execSQL("INSERT INTO " + tbl_login + " (user_id,username,password,usertype) values (3,'admin','0000','Doctor')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tbl_students);
        db.execSQL("DROP TABLE IF EXISTS " + tbl_doctors);
        db.execSQL("DROP TABLE IF EXISTS " + tbl_login);
        onCreate(db);
    }

//    INSERTING DATA
    public boolean addStudent(String firstName,String lastName,String email,String regNo,String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("student_first",firstName);
        contentValues.put("student_last",lastName);
        contentValues.put("student_email",email);
        contentValues.put("student_reg_no",regNo);
        contentValues.put("student_gender",gender);
        long res = db.insert(tbl_students,null,contentValues);
        if( res == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addDoctor(String firstName,String lastName,String email,String staff_id,String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("doctor_first",firstName);
        contentValues.put("doctor_last",lastName);
        contentValues.put("doctor_email",email);
        contentValues.put("doctor_staff_id",staff_id);
        contentValues.put("doctor_gender",gender);
        long res = db.insert(tbl_doctors,null,contentValues);
        if( res == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addUser(String userID,String username,String user_pass,String user_type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id",userID);
        contentValues.put("username",username);
        contentValues.put("password",user_pass);
        contentValues.put("user_type",user_type);
        long res = db.insert(tbl_login,null,contentValues);
        if( res == -1){
            return false;
        }else{
            return true;
        }
    }
//    INSERTING DATA

//    GETTING DATA
    public Cursor getUser(String usr,String pass){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor res = db.rawQuery("SELECT * FROM "+ tbl_login + " WHERE username='" + usr + "' AND password='"+ pass +"'",null);
    return res;
}
    public String getUserType(String usr,String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  res = db.rawQuery("SELECT * FROM "+ tbl_login + " WHERE username='" + usr + "' AND password='"+ pass +"'",null);
        String usertype = res.getString(res.getColumnIndex("user_type"));
        return usertype;
    }
//    GETTING DATA
}
