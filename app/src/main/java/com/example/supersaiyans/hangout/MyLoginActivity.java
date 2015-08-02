package com.example.supersaiyans.hangout;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.supersaiyans.hangout.client.ClientAdapter;
import com.example.supersaiyans.hangout.model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONObject;


public class MyLoginActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.lei.hangout.email";

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_my_login);
        info = (TextView)findViewById(R.id.info);

        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String fbId = loginResult.getAccessToken().getUserId();
                int userId = generateUId(fbId);
                ClientAdapter adapter = new ClientAdapter();
                User user = adapter.checkUser(1); //getUser(userId) User user = User object fetched from server

                if (user != null) {
                    Log.d("MyLoginActivity", user.toString());
                    Bundle b = new Bundle();
                    b.putSerializable("user", user);

                    Intent intent = new Intent(MyLoginActivity.this, MyHomeActivity.class);
                    intent.putExtras(b);
                    startActivity(intent);

                } else {
                    Log.d("MyLoginActivity", "User was null");
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(
                                        JSONObject object,
                                        GraphResponse response) {
                                    // Application code
                                    //Log.v("LoginActivity", response.toString());
                                    try {
                                        JSONObject jObj = new JSONObject(object.toString());
                                        String fbId = jObj.getString("id");
                                        String name = jObj.getString("name");
                                        int userId = generateUId(fbId);

                                        User user = new User(userId, fbId, name);

                                        // create user in server
                                        ClientAdapter adapter = new ClientAdapter();
                                        adapter.createUser(user);

                                        Log.d("MyLoginActivity", user.toString());
                                        info.setText(user.toString());

                                        Bundle b = new Bundle();
                                        b.putSerializable("user", user);

                                        Intent intent = new Intent(MyLoginActivity.this, MyHomeActivity.class);
                                        intent.putExtras(b);
                                        startActivity(intent);

                                    } catch(Exception JsonException) {
                                        Log.d("LoginActivity", "Exception: Not a JSon Object");
                                    }


                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id, name");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    private int generateUId(String fbId) {
        return Integer.parseInt(fbId.substring(fbId.length() - 5));
    }
}

