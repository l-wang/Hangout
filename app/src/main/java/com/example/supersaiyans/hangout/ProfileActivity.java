package com.example.supersaiyans.hangout;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;

public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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


    /** Called when the user clicks the CREATE button */
    public void loadEvent(View view){
        Intent intent = new Intent(this,EventActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the ME button */
    public void openProfile(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


    /** Called when the user clicks the MAP button */
    public void openMap(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MapsActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user clicks the SEARCH button */
    public void showEvents(View view) {
        Intent intent = new Intent(this, ShowEventActivity.class);
        startActivity(intent);
    }

}
