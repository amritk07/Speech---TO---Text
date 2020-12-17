package com.example.poc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PocDatabase extends SQLiteOpenHelper {


    public static final String table_name = "user";
    public static final String  Database_name = "POC";
    protected static  final String name_column = "name";
    protected static final String email_column = "email";
    protected static final String pass_column = "pass";
    protected static final String mobile_column = "mobile";
    protected static  final String college_column = "college";
    protected static  final String country_column = "country";
    protected static final String state_column = "state";
    protected static final String city_column = "city";



    public PocDatabase(Context context)
    {
        super(context, Database_name , null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_string = "CREATE TABLE " + table_name + "(" + name_column  + " TEXT,"   + email_column + " TEXT, " + pass_column + " TEXT NOT NULL , " + mobile_column + " TEXT, " + college_column + " TEXT, " + city_column + " TEXT, " + state_column + " TEXT, "

                + country_column  +" TEXT " + ")";

           String speech_table = "create table speech ( speech_id INTEGER(10), speech_text VARCHAR(100) )";

        db.execSQL(table_string);
        db.execSQL(speech_table);
        Log.d(null,"table created");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + "speech");

        // Create tables again
        onCreate(db);
        //insertSpeech();

    }

    public void insertSpeech()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put("speech_id",1);
        cn.put("speech_text","bingo");
        long r = db.insert("speech",null,cn);

    }



    public Boolean insertDetails(String name,String email,String pass,String mobile,String college,String city,String state,String country)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(name_column,name);
        cn.put(email_column,email);
        cn.put(pass_column,pass);
        cn.put(mobile_column,mobile);
        cn.put(college_column,college);
        cn.put(city_column,city);
        cn.put(state_column,state);
        cn.put(country_column,country);

        long r = db.insert(table_name,null,cn);
        if(r==-1)
        {
            return false;
        }
        else

        {
            return true;
        }





    }
    public String getSpeech()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor word = db.rawQuery("select * from speech where speech_id = 1",null);
        String speech_word = null;
        while(word.moveToNext())
        {
            speech_word = word.getString(1);
            break;
        }
        return speech_word;


    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from  " + table_name ,null);
        return res;

    }

    public int getEmailCount(String myEmail)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from  " + table_name + " where email = " +"'" + myEmail + "'" ,null);
        int count = res.getCount();
        return count;

    }
    public String getName(String myEmail)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String word=null;
        Cursor res = db.rawQuery("select * from  " + table_name + " where email = " +"'" + myEmail + "'" ,null);
        while(res.moveToNext())
        {
            word = res.getString(0);
            break;

        }
        return word;

    }



    public boolean Login(String email, String password) throws SQLException
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + table_name + " WHERE email=? AND pass=?", new String[]{email,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }





}
