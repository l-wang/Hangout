package com.example.supersaiyans.hangout.client;


import com.example.supersaiyans.hangout.model.Comment;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements ClientInterface{

    private int eventID;


    public Client() {
        try {
            Socket socket = new Socket("128.237.163.81", 8000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
            out.println(line.readLine());
            line.close();
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public ArrayList<Event> getEvent(Double[] location, Double distance) {
        ArrayList<Event> arrEvent = new ArrayList<>();
        Event event = new Event(null, 0, location);
        Double[] loc = event.getLocation();
        Double longitude = loc[0];
        Double latitude = loc[1];
        if (Math.abs(longitude - location[0]) < distance && Math.abs(latitude - location[1]) < distance) {
            arrEvent.add(event);
        }
        return arrEvent;
    }

    @Override
    public ArrayList<Comment> getComments(Event event) {
        ArrayList<Comment> arrComment = new ArrayList<>();
        int ID = event.getID();
        Comment comment = new Comment(0, null);
        arrComment.add(comment);
        return arrComment;
    }

    @Override
    public boolean setEvent(Event event) {

    }

    @Override
    public boolean createUser(User user) {

    }

    @Override
    public boolean updateUser(User user) {

    }

    @Override
    public boolean joinEvent(int eventID, int userID) {



    }




}
