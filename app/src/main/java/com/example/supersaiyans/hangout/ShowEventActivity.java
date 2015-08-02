package com.example.supersaiyans.hangout;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.example.supersaiyans.hangout.client.ClientAdapter;
import com.example.supersaiyans.hangout.model.Event;

public class ShowEventActivity extends Activity {//don't delete any comment in this file

    private ClientAdapter clientAdapter = new ClientAdapter();
    private  ArrayList<Event> events = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        ListView list = (ListView) findViewById(R.id.ListView01);
//        Event one = new Event("event1", 1, null, 11, "001");
//        Event two = new Event("event2", 2, null, 22, "002");
//        events.add(one);
//        events.add(two);


        events = clientAdapter.getAllEvents();

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
                new String[] {"ItemImage", "ItemTitle", "ItemText"},//动态数组与ImageItem对应的子项
                new int[] {R.id.ItemImage, R.id.ItemTitle, R.id.ItemText}//ImageItem的XML文件里面的一个ImageView,两个TextView ID
        );

        //add and show
        list.setAdapter(listItemAdapter);


//        list.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                setTitle("click #" + arg2 + "item");
//            }
//        });

        //long-press
//        list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
//            @Override
//            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//                menu.setHeaderTitle("long-press menu-ContextMenu");
//                menu.add(0, 0, 0, "pop up long-press menu 0");
//                menu.add(0, 1, 0, "pop up long-press menu 1");
//            }
//        });
    }

    //long-press response
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        setTitle("in long-press menu you clicked #" + item.getItemId() + "item");
//        return super.onContextItemSelected(item);
//    }
}