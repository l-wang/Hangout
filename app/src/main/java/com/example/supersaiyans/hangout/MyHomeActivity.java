package com.example.supersaiyans.hangout;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.supersaiyans.hangout.model.User;

import java.util.List;


public class MyHomeActivity extends Activity {

    private TextView userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home); //
        userInfo = (TextView)findViewById(R.id.userInfo);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        User user = (User)b.getSerializable("user");
        userInfo.setText(user.toString());

//        Intent intent = getIntent();
//        String email = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
//
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(email);
//        setContentView(textView);
        // test
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
