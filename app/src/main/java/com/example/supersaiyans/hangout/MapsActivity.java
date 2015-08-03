package com.example.supersaiyans.hangout;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.supersaiyans.hangout.client.ClientAdapter;
import com.example.supersaiyans.hangout.model.Event;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    LocationManager locationMgr;

    private ClientAdapter clientAdapter = new ClientAdapter();
    private ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        Double[] loc1 = new Double[]{40.446966, -79.944839};
        Double[] loc2 = new Double[]{40.443032, -79.948928};
        Double[] loc3 = new Double[]{45.747173, -112.773967};

        Event one = new Event("event1", 1, loc1, 11, "001");
        Event two = new Event("event2", 2, loc2, 22, "002");
        Event three = new Event("event3", 3, loc3, 33, "003");
//        Event four = new Event("event4", 4, null, 44, "004");
//        Event five = new Event("event5", 5, null, 55, "005");
//        Event six = new Event("event6", 6, null, 66, "006");
//        Event seven = new Event("event7", 7, null, 77, "007");
//        Event eight = new Event("event8", 8, null, 88, "008");
//        Event nine = new Event("event9", 9, null, 99, "009");
        events.add(one);
        events.add(two);
        events.add(three);
//        events.add(four);
//        events.add(five);
//        events.add(six);
//        events.add(seven);
//        events.add(eight);
//        events.add(nine);

//        events = clientAdapter.getUserEvents();
        for (Event ev : events) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(ev.getLocation()[0], ev.getLocation()[1])).title(ev.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(ev.getLocation()[0], ev.getLocation()[1])));
        }
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(MapsActivity.this, EventDetailsActivity.class));
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        locationMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        Location location = locationMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        try {                        //DON'T USE GPS_PROVIDER! I spent whole night to debug this.WTF
            double lat = location.getLatitude();
            double longitude = location.getLongitude();
            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, longitude)).title("I'm here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, longitude)));
        } catch (Exception e) {

        }
    }
//    }
//    private void setUpMap() {
////        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("Snippet"));
//
//        // Enable MyLocation Layer of Google Map
//        mMap.setMyLocationEnabled(true);
//
//        // Get LocationManager object from System Service LOCATION_SERVICE
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        // Create a criteria object to retrieve provider
//        Criteria criteria = new Criteria();
//
//        // Get the name of the best provider
//        String provider = locationManager.getBestProvider(criteria, true);
//
//        // Get Current Location
//        Location myLocation = locationManager.getLastKnownLocation(provider);
//
//        // set map type
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
//        // Get latitude of the current location
//        double latitude = myLocation.getLatitude();
//
//        // Get longitude of the current location
//        double longitude = myLocation.getLongitude();
//
//        // Create a LatLng object for the current location
//        LatLng latLng = new LatLng(latitude, longitude);
//
//        // Show the current location in Google Map
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//
//        // Zoom in the Google Map
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").snippet("Consider yourself located"));
//    }
}
