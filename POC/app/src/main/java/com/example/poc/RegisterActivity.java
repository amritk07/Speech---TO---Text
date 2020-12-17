package com.example.poc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RegisterActivity extends AppCompatActivity {


    PocDatabase db;
    EditText name,email,mobile,pass,city,state,country,college;
    String Name,Email,Pass,Mobile,City,State,Country,College;
    Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db =new PocDatabase(this);
        name = findViewById(R.id.text_name);
        email = findViewById(R.id.text_email);
        pass = findViewById(R.id.text_pass);
        mobile = findViewById(R.id.text_mobile);
        city = findViewById(R.id.text_city);
        country = findViewById(R.id.text_country);
        state = findViewById(R.id.text_state);
        college = findViewById(R.id.text_college);
        button = findViewById(R.id.save_detail);







    }
    public void saveOrder1(View v) {
        if(!validate())
        {

            //Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();

               return;

        }

        Boolean status = db.insertDetails(name.getText().toString(), email.getText().toString(), pass.getText().toString(), mobile.getText().toString(), college.getText().toString(), city.getText().toString(), state.getText().toString(), country.getText().toString());




        if(status==true)
        {
            Intent intent  = new Intent(this,SuccessActivity.class);
            startActivity(intent);
        }




    }
    public void intialize()
    {
        Name = name.getText().toString();
        Email = email.getText().toString();
        Pass = pass.getText().toString();

    }

    public Boolean validate() {
        intialize();
        int count = db.getEmailCount(Email);
        Boolean valid = true;
        if(Name.isEmpty())
        {
            name.setError("Please Enter name");
            valid = false;
        }
        if(Pass.isEmpty())
        {
            pass.setError("Please Enter password");
            valid = false;
        }
        if(Email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {

            email.setError("Enter Valid Email");
            valid = false;

        }

        if(count>0)
        {
            Toast.makeText(getApplicationContext(), "USER ALREADY EXITS", Toast.LENGTH_LONG).show();
            valid = false;
        }



        return valid;


    }




}
