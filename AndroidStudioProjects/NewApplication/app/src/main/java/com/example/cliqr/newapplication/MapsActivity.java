package com.example.cliqr.newapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    CameraUpdate cu;
    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call();
                Intent i = new Intent(getBaseContext(), Home.class);
                startActivity(i);
            }
        });

    }

    private void call() {
        //Intent in=new Intent(Intent.ACTION_CALL, Uri.parse("0000000000"));
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0377778888"));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        }

        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"yourActivity is not founded", Toast.LENGTH_SHORT).show();
        }
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

        float MAP_ZOOM_MAX = mMap.getMaxZoomLevel();
        float MAP_ZOOM_MIN = mMap.getMinZoomLevel();
        double latitude = 40.738933;

        double longitude = -74.001366;
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 15));
        mMap.addMarker(marker);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker arg0) {
                if(arg0.getTitle().equals("Hello Maps")) { // if marker source is clicked
                    final Dialog dialog = new Dialog(MapsActivity.this);
                    dialog.setContentView(R.layout.vendorinfo);
                    dialog.setTitle("Hello Maps");
                    dialog.show();
                    ImageView imageView = (ImageView) dialog.findViewById(R.id.phone);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            call();
                        }
                    });
                    Toast.makeText(getApplicationContext(), arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                return true;
            }
        });
    }
}
