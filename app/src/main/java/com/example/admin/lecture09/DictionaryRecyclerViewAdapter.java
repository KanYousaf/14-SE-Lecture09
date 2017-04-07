package com.example.admin.lecture09;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 4/6/2017.
 */

/**
    Recycler Adapter involves following things:
        1. Adapter Class: This contains three methods and constructor
            (I): Constructor: Used for initializing Context and ArrayList<YourDataFile>
            (II): Methods:
            a. OnCreateViewHolder(): to define custom layout used for widgets
            b. OnBindViewHolder(): to define the data sources of widgets
            c. getItemCount(): return the size of arrayList of used data
        2. ViewHolder Class (to define widgets)

 */

public class DictionaryRecyclerViewAdapter extends RecyclerView.Adapter<DictionaryRecyclerViewAdapter.myOwnViewHolder> {
    private ArrayList<WordDictionary> wordDictionaryArrayList;
    private LayoutInflater inflater;
    private recyclerViewClick recyclerViewClick;

    //interface is used for handling onClick event of recyclerview
    public interface recyclerViewClick{
        public void onClick(int p);
    }

    public void setOnRecyclerClickListener(recyclerViewClick recyclerViewClick){
        this.recyclerViewClick=recyclerViewClick;
    }


    public DictionaryRecyclerViewAdapter(Context context, ArrayList<WordDictionary> wordDictionaryArrayList) {
        this.inflater=LayoutInflater.from(context);
        this.wordDictionaryArrayList=wordDictionaryArrayList;
    }

    @Override
    public myOwnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row_layout, parent, false);
        return new myOwnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myOwnViewHolder holder, final int position) {
        holder.display_gre_word_tv.setText(wordDictionaryArrayList.get(position).getGre_word());
        holder.myContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              recyclerViewClick.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return wordDictionaryArrayList.size();
    }


    public class myOwnViewHolder extends RecyclerView.ViewHolder{
        private TextView display_gre_word_tv;
        private View myContainer;
        public myOwnViewHolder(View itemView) {
            super(itemView);

            display_gre_word_tv=(TextView)itemView.findViewById(R.id.display_gre_name);
            myContainer=itemView.findViewById(R.id.container_root);
        }
    }
}
