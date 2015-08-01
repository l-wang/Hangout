package com.example.supersaiyans.hangout.client;

import android.util.Log;

import com.example.supersaiyans.hangout.model.Comment;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

import java.util.ArrayList;

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

    public void joinEvent(int eventID, int userID){
        DefaultSocketClient ds = new DefaultSocketClient("128.237.163.81",8001);
        ds.setJoineEvent(true);
        ds.setEventID(eventID);
        ds.setUserID(userID);
        ds.execute();
    }

    public void addComment(Comment comment){
        DefaultSocketClient ds = new DefaultSocketClient("128.237.163.81",8001);
        ds.setAddComment(true);
        ds.setComment(comment);
        ds.execute();
    }

    public void createUser(User user){
        DefaultSocketClient ds = new DefaultSocketClient("128.237.163.81",8001);
        ds.setAddUser(true);
        ds.setUser(user);
        ds.execute();
    }

    public ArrayList<Event> getAllEvents() {
        DefaultSocketClientToGetEvents ds = new DefaultSocketClientToGetEvents("128.237.163.81",8001);
        ArrayList<Event> eventList=null;
        try{
            eventList = ds.execute().get();
        }catch (Exception e){
            Log.d("ChetanErrorrrrrrrrrrrrr",e.toString());
        }
        return eventList;
    }
}
