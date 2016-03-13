package com.example.sayyadabid.testapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.sayyadabid.testapp.R;
import com.example.sayyadabid.testapp.datamodels.Destination;
import com.example.sayyadabid.testapp.datamodels.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This activity is responsible for displaying maps.
 * When the map is ready, navigate camera to input destination.
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static String TAG_SELECTED_DESTINATION = "SELECTED_DESTINATION";
    private GoogleMap mMap;
    private Destination destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        destination = (Destination) getIntent().getSerializableExtra(TAG_SELECTED_DESTINATION);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Location location = destination.getLocation();
        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.addMarker(new MarkerOptions().position(sydney).title(destination.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
