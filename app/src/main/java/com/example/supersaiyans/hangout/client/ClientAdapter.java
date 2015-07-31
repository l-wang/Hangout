package com.example.supersaiyans.hangout.client;

import com.example.supersaiyans.hangout.model.Event;

/**
 * Created by Chetan on 7/31/2015.
 */
public class ClientAdapter {

    public void createEvent(Event event){
        DefaultSocketClient ds = new DefaultSocketClient("127.0.0.1",8000);
        ds.setCreateEvent(true);
        ds.setEvent(event);
        ds.run();
    }
}
