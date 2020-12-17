package com.example.poc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     EditText text_Email,text_Pass;
     public   String EMAIL,PASS;
     PocDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_Email = findViewById(R.id.editTextEmail);
        text_Pass  = findViewById(R.id.editTextPass);

        db = new PocDatabase(this);
        db.insertSpeech();
    }
    public void getDataInString()
    {

        EMAIL = text_Email.getText().toString();
        PASS  = text_Pass.getText().toString();


    }
    public void openHomePage(View v)
    {
        getDataInString();
        if(EMAIL.isEmpty())
        {
            text_Email.setError("Enter your Email Id");


        }
        if(PASS.isEmpty())
        {
            text_Pass.setError("Enter your password");
            return;
        }

        if(!db.Login(EMAIL,PASS))
        {
            Toast.makeText(this, "Login failed wrong user credentials", Toast.LENGTH_LONG).show();
            return;
        }
       else {

           Intent intent = new Intent(this,HomeActivity.class);
           intent.putExtra("info",EMAIL);
           startActivity(intent);

        }

    }

    public void openRegistration(View v)
    {
       Intent intent = new Intent(this,RegisterActivity.class);
       startActivity(intent);
    }



}
