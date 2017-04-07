package com.example.admin.lecture09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Scanner;

public class Gre_Words extends AppCompatActivity {
    private ArrayList<WordDictionary> gre_array_list;
    private RecyclerView recyclerView;
    private DictionaryRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gre__words);
        //defining the arraylist for adding words on it
        gre_array_list=new ArrayList<>();

        //reading from grewords file (placed in raw folder)
        //create raw (android directory) by yourself by right-click on res folder
        readFromFileAndDisplay();
        adapter=new DictionaryRecyclerViewAdapter(Gre_Words.this,gre_array_list);
        recyclerView=(RecyclerView)this.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setOnRecyclerClickListener(new DictionaryRecyclerViewAdapter.recyclerViewClick() {
            @Override
            public void onClick(int p) {
                String gre_word_from_al=gre_array_list.get(p).getGre_word();
                Intent i=new Intent(Gre_Words.this,Gre_Definition.class);
                i.putExtra("gre_word_to_pass", gre_word_from_al);
                startActivity(i);
            }
        });


    }

    public void readFromFileAndDisplay(){
        Scanner scanner=new Scanner(getResources().openRawResource(R.raw.grewords));
        while(scanner.hasNextLine()){
            String Line=scanner.nextLine();
            String[] parts=Line.split("\t");
            if(parts.length>=2){
                String gre_words_for_arrayList=parts[0];
                gre_array_list.add(new WordDictionary(gre_words_for_arrayList));
            }
        }
        //if file is in open state, let it close first
        if(scanner!=null){
            scanner.close();
        }
    }
}
