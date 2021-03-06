package com.example.supersaiyans.hangout;


import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.example.supersaiyans.hangout.client.ClientAdapter;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileActivity extends Activity {

    private ClientAdapter clientAdapter = new ClientAdapter();
    private ArrayList<Event> events = new ArrayList<>();
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Double[] loctn3 = new Double[]{30.23423223, 117.00322};

        ListView list = (ListView) findViewById(R.id.ListView02);
        Event one = new Event("event1", 1, null, 11, "001");
        Event two = new Event("event2", 2, null, 22, "002");
        Event three = new Event("event3", 3, null, 33, "003");
        Event four = new Event("event4", 4, null, 44, "004");
        Event five = new Event("event5", 5, null, 55, "005");
        Event six = new Event("event6", 6, null, 66, "006");
        Event seven = new Event("event7", 7, null, 77, "007");
        Event eight = new Event("event8", 8, null, 88, "008");
        Event nine = new Event("event9", 9, null, 99, "009");
        events.add(one);
        events.add(two);
        events.add(three);
        events.add(four);
        events.add(five);
        events.add(six);
        events.add(seven);
        events.add(eight);
        events.add(nine);

//        user = new User(3, "event3", loctn3);
//        events = clientAdapter.getUserEvents(user.getID());

        //create arraylist and add data
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();

        for (Event ev : events) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("ItemTime", ev.getTime());
            map.put("ItemName", ev.getName());
            map.put("ItemEventID", ev.getID());
            listItem.add(map);
        }

        //create adapter item and the element corresponding to array
        // 生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,//data
                R.layout.list_items,//ListItem XML
                new String[] {"ItemTime", "ItemName", "ItemEventID"},//动态数组与ImageItem对应的子项
                new int[] {R.id.ItemTitle, R.id.ItemText, R.id.ItemID}//ImageItem的XML文件里面的一个ImageView,两个TextView ID
        );

        //add and show
        list.setAdapter(listItemAdapter);


        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                setTitle("click #" + arg2 + "item");
                String myEventTime;
                String myEventName;
                int myEventID;

                switch(arg0.getId()) {
                    case R.id.ListView02:
                        ListView templist = (ListView)arg0;
                        View mView = templist.getChildAt(arg2);

                        TextView textViewTitle = (TextView) mView.findViewById(R.id.ItemTitle);
                        myEventTime = textViewTitle.getText().toString();
                        TextView textViewText = (TextView) mView.findViewById(R.id.ItemText);
                        myEventName = textViewText.getText().toString();
                        TextView textViewID = (TextView) mView.findViewById(R.id.ItemID);
                        myEventID = Integer.parseInt(textViewID.getText().toString());


//                        mysqlhelper.db = mysqlhelper.mOpenHelper.getReadableDatabase();
//                        Cursor cur = mysqlhelper.db.rawQuery("select Content from Table_1 where Title = ?",new String[]{mytitle});
//                        int count = cur.getCount();
//                        cur.moveToFirst();
//                        mycontent = cur.getString(0);

//                                 cur.close();
//                        mysqlhelper.db.close();

                        Intent intent = new Intent(ProfileActivity.this, EventDetailsActivity.class);
                        intent.putExtra("eventTime", myEventTime);
                        intent.putExtra("eventName", myEventName);
                        intent.putExtra("eventID", myEventID);
                        setResult(2, intent);
                        Toast.makeText(ProfileActivity.this, "have sent event info to eventdetails", Toast.LENGTH_LONG).show();
                        finish();
                        startActivityForResult(intent, 2);
                        break;
                }
            }
        });

        //long-press
        list.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("long-click events operations: ");
                menu.add(0, 0, Menu.NONE, "DELETE ?");
                menu.add(0, 1, Menu.NONE, "more operations TO BE ADDED !!!");
//                menu.add(0, 1, 0, "pop up long-press menu 1");
            }
        });
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
        startActivity(intent);
    }

    /** Called when the user clicks the SEARCH button */
    public void showEvents(View view) {
        Intent intent = new Intent(this, ShowEventActivity.class);
        startActivity(intent);
    }

}
