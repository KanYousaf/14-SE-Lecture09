package com.example.admin.lecture09;

import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView display_stt_tv;
    private EditText tts_edit_text;
    private TextToSpeech tts;
    private Locale myCurrentSpokenLanguage=Locale.US;
    private static final int REQ_CODE=123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    tts.setLanguage(myCurrentSpokenLanguage);
                }
            }
        });
    }

    public void tts_function(View view) {
        tts_edit_text=(EditText)this.findViewById(R.id.textToSpeechET);
        String speech=tts_edit_text.getText().toString();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }else{
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        tts.stop();
        tts.shutdown();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tts.stop();
        tts.shutdown();
    }

    public void stt_function(View view) {
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something!");
        startActivityForResult(i, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE:
                if(resultCode==RESULT_OK && data !=null){
                    ArrayList<String> list= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                          if(list.isEmpty() || list== null){
                              return;
                          }  else{
                           display_stt_tv=(TextView)this.findViewById(R.id.display_text);
                              display_stt_tv.setText(list.get(0));
                          }
                }
                break;
        }
    }

    public void file_handling_function(View view) {
        Intent i=new Intent(MainActivity.this, Gre_Words.class);
        startActivity(i);
    }
}
