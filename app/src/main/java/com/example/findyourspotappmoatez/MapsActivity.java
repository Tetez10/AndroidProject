package com.example.findyourspotappmoatez;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.TextView;

import com.example.findyourspotappmoatez.Controller.DataHandler;
import com.example.findyourspotappmoatez.model.Spot;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    DataHandler db;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;


    private LatLng BrusselRoyalParc = new LatLng(50.84570325249622, 4.360241886736915);
    private LatLng EgmontParc = new LatLng(50.83771487484857, 4.356416901996021);

    List<Marker> markerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



}

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
       // mMap.addMarker(new MarkerOptions().position(latLng).title("new Marker")
        //    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        Intent intent = new Intent(MapsActivity.this, AddSpot.class);
      intent.putExtra("latitude" , latLng.latitude);
      intent.putExtra("longitude" , latLng.longitude);

      startActivity(intent);

    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this ,marker.getPosition().toString(),Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {



        mMap = googleMap;
        db = new DataHandler(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);


        markerList = new ArrayList<>();
        List<Spot> placeList = db.getAllPlaces();

        for(Spot p: placeList){
            String myInfo = "ID: " + p.getId() + " Latitude: "+ p.getPlatitude() + "Longitude"
                    + p.getPlongitude() + " Title: " + p.getTitle();
            Log.d("myInfo", myInfo);

            markerList.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(p.getPlatitude())
                            ,Double.parseDouble(p.getPlongitude()))).title(p.getTitle())
                    .zIndex( p.getId() ).snippet("By You")     ));
        }

        markerList.add(mMap.addMarker(new MarkerOptions()
                .position(BrusselRoyalParc).title("RoyalParc")));
        markerList.add(mMap.addMarker(new MarkerOptions()
                .position(EgmontParc).title("Egmontpark")));

        for(Marker m : markerList){
            // Add a marker in Sydney and move the camera
            LatLng latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng) );
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
        }


    }
}


