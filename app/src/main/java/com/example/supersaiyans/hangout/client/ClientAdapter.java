package com.example.supersaiyans.hangout.client;

import com.example.supersaiyans.hangout.model.Event;

/**
 * Created by Chetan on 7/31/2015.
 */
public class ClientAdapter {

    public void createEvent(Event event){
        DefaultSocketClient ds = new DefaultSocketClient("128.237.163.81",8001);
        ds.setCreateEvent(true);
        ds.setEvent(event);
        ds.execute();
    }
}
