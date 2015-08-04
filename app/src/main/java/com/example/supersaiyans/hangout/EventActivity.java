package com.example.supersaiyans.hangout;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.*;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.supersaiyans.hangout.client.ClientAdapter;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class EventActivity extends ActionBarActivity {

    private EditText eventName;
    private EditText eventTime;
    private EditText eventDescription;
    private EditText eventLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        eventName = (EditText) findViewById(R.id.eventName);
        eventTime = (EditText) findViewById(R.id.eventTime);
        eventDescription = (EditText) findViewById(R.id.eventDescription);
        eventLocation = (EditText) findViewById(R.id.eventLocation);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createEvent(View view){
        /*Double d[] = new Double[2];
        d[0]= 0.0d;
        d[1]=1.1d;
        String time = "31-Aug-2015";
        Event e = new Event("test",1,d,1,time);*/
        Random r = new Random(System.currentTimeMillis());
        String eName = eventName.getText().toString();
        int eventID = r.nextInt(90000);
        // fetch userr id
        int userID=1;
        String time= eventTime.getText().toString();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");

        try{
            Date d = s.parse(time);
            s.applyPattern("dd-MMM-yyyy");
            //Date d = s.parse(time);
            //Log.d("checktime", s.format(d));
            time=s.format(d);
        }catch(Exception e){
            Log.d("MyExceptioonnnn", e.toString());
        }
        //Log.d("checktime", time);
        //hard coding location co-ordinates for now
        String address = eventLocation.getText().toString();


        getLatLongFromGivenAddress(address);
        /*Double[] location = new Double[2];
        location[0]=0.0d;
        location[1]=0.0d;
        Event e = new Event(eName,eventID,location,userID,time);
        ClientAdapter ca = new ClientAdapter();
        ca.createEvent(e);
        Toast.makeText(EventActivity.this, "Event created", Toast.LENGTH_LONG).show();*/
       /* ClientAdapter ca = new ClientAdapter();
        User user = ca.checkUser(1);
        if(user!=null){
            Log.d("userchkkkkkk",user.getName());
        }
        else{
            Log.d("userchkkkkkkfae","");
        }*/

        //ca.getAllEvents();
        //Intent intent = new Intent(this,ShowEventActivity.class);
        //startActivity(intent);
    }

    public  void getLatLongFromGivenAddress(String address)
    {
        double lat= 0.0, lng= 0.0;

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try
        {
            List<Address> addresses = geoCoder.getFromLocationName(address , 1);

            if (addresses.size() > 0)
            {
                Address a = addresses.iterator().next();
               lat = a.getLatitude();
               lng = a.getLongitude();
              //  lat=p.getLatitudeE6()/1E6;
              //  lng=p.getLongitudeE6()/1E6;

                Log.d("Latitude", ""+lat);
                Log.d("Longitude", ""+lng);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}



