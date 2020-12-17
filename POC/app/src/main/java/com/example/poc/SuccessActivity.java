package com.example.poc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {
    PocDatabase db;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
         db = new PocDatabase(this);
         text = findViewById(R.id.test);


       /* Cursor res = db.getAllData();

       // StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append(res.getString(0)+"\n");
            buffer.append(res.getString(7)+"\n");

        }
          text.setText(buffer); */
















    }
    public void returnLogin(View v)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);


    }
}
