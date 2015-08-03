package com.example.supersaiyans.hangout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

public class EventDetailsActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_event_details);
//    }
    private TextView name;

//    private TextView description;
//    private TextView time;
//    private TextView location;
//    private TextView organizer;
//    private TextView participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        //        Intent in = getIntent();
//        Bundle b = in.getExtras();
//        Event event = (Event)b.getSerializable("event");
//        Event event = new Event("MY Event", 12345, new Double[]{0.0,1.0}, 90887, "12:20");
        // hard code for testing
        Intent in = getIntent();
        Bundle b = in.getExtras();
        String myName = b.getString("eventName");

        Log.d("EventDetailsActivity", "hi there");

        name = (TextView)findViewById(R.id.name);
        name.setText(myName);

//        description = (TextView)findViewById(R.id.description);
//        description.setText(event.getDescription());
//
//        time = (TextView)findViewById(R.id.time);
//        time.setText(event.getTime());
//
//        location = (TextView)findViewById(R.id.location);
//        location.setText("[" + event.getLocation()[0].toString() + ", " + event.getLocation()[1].toString() + "]");
//
//        organizer = (TextView)findViewById(R.id.organizer);
//        organizer.setText(event.getOrganizer());
//
//        participants = (TextView)findViewById(R.id.participants);
//        participants.setText(event.getParticipants());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_details, menu);
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
}
