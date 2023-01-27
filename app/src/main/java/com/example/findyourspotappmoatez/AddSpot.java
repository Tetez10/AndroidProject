package com.example.findyourspotappmoatez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.findyourspotappmoatez.Controller.DataHandler;
import com.example.findyourspotappmoatez.model.Spot;

public class AddSpot extends AppCompatActivity {
    private TextView latitude, longitude;
    private EditText title;
    private Button save;
    private DataHandler db;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addspot);

        latitude = findViewById(R.id.Latitudespot);
        longitude = findViewById(R.id.LongitudeSpot);
        title = findViewById(R.id.NameSpot);
        save = findViewById(R.id.SaveSpot);

        db = new DataHandler(this);
        extras = getIntent().getExtras();

        if (extras != null) {

            latitude.setText(String.valueOf(extras.getDouble("latitude")));
            longitude.setText(String.valueOf(extras.getDouble("longitude")));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.addPlace(new Spot(latitude.getText().toString()
                        , longitude.getText().toString(),
                        title.getText().toString()));
                Toast.makeText(AddSpot.this, "Spot saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddSpot.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}



