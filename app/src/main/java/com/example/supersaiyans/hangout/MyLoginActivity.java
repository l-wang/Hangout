package com.example.supersaiyans.hangout;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MyLoginActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.lei.hangout.email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
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

    /** Called when the user clicks the Sign IN button */
    public void login(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MyHomeActivity.class);
//        EditText editText = (EditText) findViewById(R.id.email);
//        String email = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, email);
        startActivity(intent);
    }

    /** Called when the user clicks the Sign UP button */
    public void signup(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}

