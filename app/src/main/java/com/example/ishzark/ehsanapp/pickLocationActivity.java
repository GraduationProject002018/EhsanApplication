package com.example.ishzark.ehsanapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class pickLocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private LocationManager locationManager;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Marker marker;
    private ImageView btnButton;
    private Context mContext;
    private static final int REQUEST_ACCESS_COARSE_LOCATION = 1;
    private TextView CityText;
    private Button continueButton;

    private TextView latText;
    private TextView longText;
    final LatLngBounds Sharquia_BOUNDS = new LatLngBounds(new LatLng(18.977927, 49.692832),
            new LatLng(28.910124, 47.345048));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donorlocation);

        String Language = "ar";
        Locale locale = new Locale(Language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            config.setLocale(locale);
            getBaseContext().createConfigurationContext(config);
        } else {
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }
        latText = findViewById(R.id.latitiudetext);
        longText = findViewById(R.id.longtitiudetext);

        continueButton = findViewById(R.id.continubtn);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Donors");
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String Userid = currentFirebaseUser.getUid();
                String cityname = CityText.getText().toString();

                DatabaseReference ref2 = ref.child(Userid).child("Location").child(cityname);
                String lat = latText.getText().toString();
                String longt = longText.getText().toString();
                Map<String, Object> loc = new HashMap<>();
                loc.put("Latitude", lat);
                loc.put("Longtitude", longt);

                ref2.updateChildren(loc);

                Intent intent = new Intent(pickLocationActivity.this, ItemDonationActivity.class);
                intent.putExtra("chosencity", cityname);
                String Donorname = intent.getStringExtra("donorname");
                intent.putExtra("donorname", Donorname);
                finish();
                startActivity(intent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        btnButton = findViewById(R.id.locator);


        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        Log.d("mylog", "Not granted");
                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    } else
                        requestLocation();
                } else
                    requestLocation();
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location mCurrentLocation = locationResult.getLastLocation();


                latText.setText(Double.toString(mCurrentLocation.getLatitude()));
                longText.setText(Double.toString(mCurrentLocation.getLongitude()));


                LatLng myCoordinates = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                String cityName = getCityName(myCoordinates);
                CityText.setText(cityName);

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myCoordinates, 15.0f));
                if (marker == null) {
                    marker = mMap.addMarker(new MarkerOptions().position(myCoordinates).title("موقع التبرع"));
                    marker.setPosition(myCoordinates);
                    marker.setDraggable(true);

                } else {
                    marker.remove();
                    marker=null;
                    Marker newmarker = mMap.addMarker(new MarkerOptions().position(myCoordinates).title("موقع التبرع"));
                    newmarker.setPosition(myCoordinates);
                    newmarker.setDraggable(true);

                }



            }
        };


        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("mylog", "Getting Location Permission");
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("mylog", "Not granted");
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else
                requestLocation();
        } else
            requestLocation();

    }


    private String getCityName(LatLng myCoordinates) {
        String myCity = "";
        Geocoder geocoder = new Geocoder(pickLocationActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(myCoordinates.latitude, myCoordinates.longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            myCity = addresses.get(0).getAddressLine(0);
            Log.d("mylog", "Complete Address: " + addresses.toString());
            Log.d("mylog", "Address: " + address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCity;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /*
        Polygon polygon = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(22.2954,50.6794),
                        new LatLng(24.143627, 48.410156),
                        new LatLng(25.054057, 47.445240),
                        new LatLng(26.394672, 47.450812),
                        new LatLng(27.672717, 45.293194),
                         new LatLng(26.394672, 47.450812),
                         new LatLng(27.672717, 45.293194),
                         new LatLng(28.972576, 46.472732),
                        new LatLng(22.934938, 52.575459),
                        new LatLng(22.643304, 55.211186),
                        new LatLng(22.015353, 55.617596),
                        new LatLng(20.090134, 54.9328486)

                )
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
*/
        //mMap.setLatLngBoundsForCameraTarget(Sharquia_BOUNDS);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                LatLng position = marker.getPosition();

// Remove Old Marker

// Add New marker position
               // marker.setPosition(position);
// Add Title on Marker
                CityText.setText(getCityName(position));
// Set Draggable
                marker.setDraggable(true);

// Add marker on map
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15.0f));
                //marker = mMap.addMarker(new MarkerOptions().position(position).title("موقع التبرع"));

                //marker.setPosition(position);
                marker.setDraggable(true);
                final String cityName = getCityName(position);
                if(stringContainsItemFromList(cityName,getResources().getStringArray(R.array.city_arrays))){
                    Toast.makeText(pickLocationActivity.this, "المنطقة  متاحة لهذة الخدمة", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(pickLocationActivity.this, "المنطقة غير متاحة لهذة الخدمة", Toast.LENGTH_LONG).show();
                    continueButton.setEnabled(false);


                }


            }
        });

        // Add a marker in Sydney and move the camera
    }

    private void requestLocation() {
// allowedbounds must be generated only once, in onCreate, because it will be a fixed area



            CityText = findViewById(R.id.locationadd2);
            //Request location enabled from the donor
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
            criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
            String provider = locationManager.getBestProvider(criteria, true);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((RegisterActivity) mContext,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_ACCESS_COARSE_LOCATION);

                return;
            }
            //If location request was used before
            final Location location = locationManager.getLastKnownLocation(provider);
            Log.d("mylog", "In Requesting Location");
            if (location != null && (System.currentTimeMillis() - location.getTime()) <= 1000 * 2) {
                final LatLng myCoordinates = new LatLng(location.getLatitude(), location.getLongitude());

                //Get city name to create an address
                final String cityName = getCityName(myCoordinates);
                CityText.setText(cityName);
                if(stringContainsItemFromList(cityName,getResources().getStringArray(R.array.city_arrays))){
                    Toast.makeText(pickLocationActivity.this, "المنطقة  متاحة لهذة الخدمة", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(pickLocationActivity.this, "المنطقة غير متاحة لهذة الخدمة", Toast.LENGTH_LONG).show();
                    continueButton.setEnabled(false);


                }

            } else {
                //Make a new location request
                LocationRequest locationRequest = new LocationRequest();
                locationRequest.setNumUpdates(1);
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                Log.d("mylog", "Last location too old getting new location!");
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                mFusedLocationClient.requestLocationUpdates(locationRequest,
                        mLocationCallback, Looper.myLooper());
            }

        }
          //



    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        for(int i =0; i < items.length; i++)
        {
            if(inputStr.contains(items[i]))
            {
                return true;
            }
        }
        return false;    }

    }

