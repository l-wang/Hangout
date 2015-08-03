package com.example.supersaiyans.hangout;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supersaiyans.hangout.client.ClientAdapter;
import com.example.supersaiyans.hangout.model.Event;

public class ShowEventActivity extends Activity {//don't delete any comment in this file

//    private static final String TAG = "ContextMenu";

    private ClientAdapter clientAdapter = new ClientAdapter();
    private  ArrayList<Event> events = new ArrayList<>();
    private String eventName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        ListView list = (ListView) findViewById(R.id.ListView01);
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


//        events = clientAdapter.getAllEvents();

        //create arraylist and add data
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();

        for (Event ev : events) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("ItemTitle", ev.getTime());
            map.put("ItemText", ev.getName());
            listItem.add(map);
        }

//        for (int i = 0; i < 10; i++) {
//            HashMap<String, Object> map = new HashMap<>();
////            map.put("ItemImage", R.drawable.checked);//ID of image
//            map.put("ItemTitle", "event " + i);
//            map.put("ItemText", "address is at street: " + i);
//            listItem.add(map);
//        }

        //create adapter item and the element corresponding to array
        // 生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,//data
                R.layout.list_items,//ListItem XML
//                new String[] {"ItemImage", "ItemTitle", "ItemText"},//动态数组与ImageItem对应的子项
//                new int[] {R.id.ItemImage, R.id.ItemTitle, R.id.ItemText}//ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new String[] {"ItemTitle", "ItemText"},
                new int[] {R.id.ItemTitle, R.id.ItemText}
        );

        //add and show
        list.setAdapter(listItemAdapter);

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                setTitle("click #" + arg2 + "item");
                String mytitle = null;
                String mycontent = null;

                switch(arg0.getId())
                {
                    case R.id.ListView01:
                        ListView templist = (ListView)arg0;
                        View mView = templist.getChildAt(arg2);

                        TextView textViewTitle = (TextView) mView.findViewById(R.id.ItemTitle);
                        mytitle = textViewTitle.getText().toString();

//                        mysqlhelper.db = mysqlhelper.mOpenHelper.getReadableDatabase();
//                        Cursor cur = mysqlhelper.db.rawQuery("select Content from Table_1 where Title = ?",new String[]{mytitle});
//                        int count = cur.getCount();
//                        cur.moveToFirst();
//                        mycontent = cur.getString(0);
                        TextView textViewText = (TextView) mView.findViewById(R.id.ItemText);
                        mycontent = textViewText.getText().toString();


//                                 cur.close();
//                        mysqlhelper.db.close();

                        Intent intent = new Intent(ShowEventActivity.this, EventDetailsActivity.class);
                        intent.putExtra("Title", mytitle);
                        intent.putExtra("Content", mycontent);
                        setResult(2, intent);
                        Toast.makeText(ShowEventActivity.this, "send event info to eventdetails", Toast.LENGTH_LONG).show();
                        finish();
                        startActivityForResult(intent, 2);
                        break;
                }
            }
        });

        //long-press
        list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("long-click events operations: ");
                menu.add(0, 0, Menu.NONE, "WANNA SEE Event Details ?");
                menu.add(0, 1, Menu.NONE, "more operations TO BE ADDED !!!");
//                menu.add(0, 1, 0, "pop up long-press menu 1");

            }
        });
    }

    //long-press response
    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        setTitle("in long-press menu you clicked #" + item.getItemId() + "item");
        Bundle b = new Bundle();
        b.putString(Integer.toString(R.id.ItemTitle), eventName);
        Intent intent = new Intent(ShowEventActivity.this, EventDetailsActivity.class);
        intent.putExtras(b);
        startActivity(intent);
        return super.onContextItemSelected(item);

        // 得到当前被选中的item信息
//        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        final Map<String, String> map = (HashMap) lv.getItemAtPosition(selectedPosition);

//        Log.v(TAG, "ID = " + menuInfo.id);

//        switch(item.getItemId()) {
//
//            case 0:
//                item.setIntent(new Intent(ShowEventActivity.this, EventDetailsActivity.class));
//                break;
//
//            case 1:
//                // delete this event on the listview and in db
//
//                break;
//
//            default:
//
//                return super.onContextItemSelected(item);
//
//        }
//
//        return true;
    }
}