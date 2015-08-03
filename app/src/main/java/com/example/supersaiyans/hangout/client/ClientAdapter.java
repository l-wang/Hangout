package com.example.supersaiyans.hangout.client;

import android.util.Log;

import com.example.supersaiyans.hangout.model.Comment;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;
import com.example.supersaiyans.hangout.resource.ClientResource;

import java.util.ArrayList;

/**
 * Created by Chetan on 7/31/2015.
 */
public class ClientAdapter {

    public void createEvent(Event event){
        DefaultSocketClient ds = new DefaultSocketClient(ClientResource.ip,ClientResource.port);
        ds.setCreateEvent(true);
        ds.setEvent(event);
        ds.execute();
    }

    public void joinEvent(int eventID, int userID){
        DefaultSocketClient ds = new DefaultSocketClient(ClientResource.ip,ClientResource.port);
        ds.setJoineEvent(true);
        ds.setEventID(eventID);
        ds.setUserID(userID);
        ds.execute();
    }

    public void addComment(Comment comment){
        DefaultSocketClient ds = new DefaultSocketClient(ClientResource.ip,ClientResource.port);
        ds.setAddComment(true);
        ds.setComment(comment);
        ds.execute();
    }

    public void createUser(User user){
        DefaultSocketClient ds = new DefaultSocketClient(ClientResource.ip,ClientResource.port);
        ds.setAddUser(true);
        ds.setUser(user);
        ds.execute();
    }

    public ArrayList<Event> getAllEvents() {
        DefaultSocketClientToGetEvents ds = new DefaultSocketClientToGetEvents(ClientResource.ip,ClientResource.port);
        ArrayList<Event> eventList=null;
        try{
            eventList = ds.execute().get();
            if(eventList!=null){
                Log.d("YaySuccess" + eventList.iterator().next().getID(),"yessssssssss");
            }
            else{
                Log.d("YayFailure" ,"nooooooooooooooo");
            }

        }catch (Exception e){
            Log.d("ChetanErrorrrrrrrrrrrrr",e.toString());
        }
        return eventList;
    }

    public ArrayList<Comment> getAllComments(int eventID) {
        DefaultSocketClientToGetComments ds = new DefaultSocketClientToGetComments(ClientResource.ip,ClientResource.port);
        ds.setEventID(eventID);
        ArrayList<Comment> commentList=null;
        try{
            commentList = ds.execute().get();
            if(commentList!=null){
                Log.d("YaySuccess" + commentList.iterator().next().getCommentID(),"yessssssssss");
            }
            else{
                Log.d("YayFailure" ,"nooooooooooooooo");
            }

        }catch (Exception e){
            Log.d("ChetanErrorrrrrrrrrrrrr",e.toString());
        }
        return commentList;
    }

    public ArrayList<Event> getUserEvents(int userID) {
        DefaultSocketClientToGetEvents ds = new DefaultSocketClientToGetEvents(ClientResource.ip,ClientResource.port);
        ds.setGetEventsBasedONID(true);
        //ds.setUserID(userID);
        ds.setUserID(1);
        ArrayList<Event> eventList=null;
        try{
            eventList = ds.execute().get();
            if(eventList!=null){
                Log.d("YaySuccess" + eventList.iterator().next().getID(),"yessssssssss");
            }
            else{
                Log.d("YayFailure" ,"nooooooooooooooo");
            }

        }catch (Exception e){
            Log.d("ChetanErrorrrrrrrrrrrrr",e.toString());
        }
        return eventList;
    }

    public User checkUser (int userID) {
        DefaultSocketClientToGetUsers ds = new DefaultSocketClientToGetUsers(ClientResource.ip,ClientResource.port);
        ds.setUserID(userID);
        try {
            User u = ds.execute().get();
            if (u != null) {
                Log.d("YaySuccess" + u.getID(), "yessssssssss");
                return u;
            } else {
                Log.d("YayFailure", "nooooooooooooooo");
                return null;
            }

        } catch (Exception e) {
            Log.d("ChetanErrorrrrrrrrrrrrr", e.toString());

        }
        return null;
    }
}
