package com.example.findyourspotappmoatez.model;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findyourspotappmoatez.Controller.DataHandler;
import com.example.findyourspotappmoatez.Myadapter;
import com.example.findyourspotappmoatez.R;
import com.example.findyourspotappmoatez.model.Spot;

import java.util.ArrayList;
import java.util.List;

public class fetchdata extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Spot> dataholder = new ArrayList<>();
    DataHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showspot);

        recyclerView = findViewById(R.id.Recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DataHandler(this).GetAllData();

        while(cursor.moveToNext()){
            Spot obj = new Spot(cursor.getString(1) , cursor.getString(2), cursor.getString(3) );
            dataholder.add(obj);
        }

        Myadapter myadapter = new Myadapter(dataholder);
        recyclerView.setAdapter(myadapter);
    }

}
