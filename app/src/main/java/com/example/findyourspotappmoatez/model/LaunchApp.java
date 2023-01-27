package com.example.findyourspotappmoatez.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.findyourspotappmoatez.MapsActivity;
import com.example.findyourspotappmoatez.R;

public class LaunchApp extends AppCompatActivity {
    private Button buttonlaunch;
    private Button spotbutton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        buttonlaunch = findViewById(R.id.buttonlaunch);
        spotbutton = findViewById(R.id.buttonlist);


        buttonlaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenApp();
            }
        });


        spotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),fetchdata.class));
            }
        });




    }

    public void OpenApp(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }
}