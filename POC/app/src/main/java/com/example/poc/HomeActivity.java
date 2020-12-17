package com.example.poc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView text_name,text_speech,text_mic;
    PocDatabase db;
    ImageView img;
    private static final int RECOGNIZER_RESULT =1;
    String speech_word = null,mic_word =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String Email = intent.getStringExtra("info");
        text_name = findViewById(R.id.textView1);//name text view
        text_speech = findViewById(R.id.textView2);
        text_mic = findViewById(R.id.textView3);
        img = findViewById(R.id.imageView);

        db = new PocDatabase(this);
        String user_name = db.getName(Email); //getName(String str) is function in PocDatabase class
        text_name.setText(user_name);//set name in textview

     //****************************************
        speech_word = db.getSpeech().toLowerCase();
        text_speech.setText(speech_word + " //from speech table");



   //**********************************************************
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak");
                startActivityForResult(speechIntent,RECOGNIZER_RESULT);
            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode == RECOGNIZER_RESULT && resultCode == RESULT_OK)
       {
           ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


           text_mic.setText(matches.get(0).toString().toLowerCase());
           speech_word = speech_word.toLowerCase();
           mic_word    = text_mic.getText().toString().toLowerCase();
           if(speech_word.matches(mic_word))
           {
               Toast.makeText(this,"CORRECT",Toast.LENGTH_LONG).show();
           }
           else
               Toast.makeText(this,"Sorry, Try Again",Toast.LENGTH_LONG).show();



       }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
