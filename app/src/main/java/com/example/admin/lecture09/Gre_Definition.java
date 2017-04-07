package com.example.admin.lecture09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Scanner;

public class Gre_Definition extends AppCompatActivity {
    private TextView gre_definition;
    private String gre_word_received;
    private HashMap<String,String> dictionary_hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gre__definition);
        gre_definition=(TextView)this.findViewById(R.id.gre_definition);
        //instantiate hash map otherwise it will generate null pointer exception
        dictionary_hashMap=new HashMap<>();

        //receiving the word for displaying it's meaning here using recycler view
        Intent i=getIntent();
        gre_word_received=i.getExtras().getString("gre_word_to_pass");
        readFromGREFile();

        if(dictionary_hashMap.containsKey(gre_word_received)){
            gre_definition.setText(dictionary_hashMap.get(gre_word_received));
        }else{
            gre_definition.setText("Word not Found");
        }
    }

    public void readFromGREFile(){
        Scanner scanner=new Scanner(getResources().openRawResource(R.raw.grewords));
        while(scanner.hasNextLine()){
            String Line=scanner.nextLine();
            String[] parts=Line.split("\t");
            if(parts.length>=2){
                String gre_words_for_arrayList=parts[0];
                String gre_definition=parts[1];
                dictionary_hashMap.put(gre_words_for_arrayList,gre_definition);
            }
        }
    }
}
