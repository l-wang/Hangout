package com.example.supersaiyans.hangout.client;

import android.os.AsyncTask;
import android.util.Log;

import com.example.supersaiyans.hangout.model.Comment;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Chetan on 7/31/2015.
 */
public class DefaultSocketClientToGetEvents extends AsyncTask<Void,Void,ArrayList<Event>> implements SocketClientInterface, SocketClientConstant {
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket socket;
    private String strHost;
    private int iPort;
    ArrayList<Event> eventList;



    public DefaultSocketClientToGetEvents(String strHost, int iPort) {
        setiPort (iPort);
        setStrHost (strHost);
    }

    public DefaultSocketClientToGetEvents(Socket socket) {
        this.socket=socket;
    }

    public String getStrHost() {
        return strHost;
    }

    public void setStrHost(String strHost) {
        this.strHost = strHost;
    }

    public int getiPort() {
        return iPort;
    }

    public void setiPort(int iPort) {
        this.iPort = iPort;
    }



    @Override
    public boolean openConnection(){
        try {
            this.socket = new Socket(strHost, iPort);
        }
        catch(IOException socketError){socketError.printStackTrace();
            //if (DEBUG) System.err.println("Unable to connect to " + strHost);
            Log.d("Error","ConnError"+"Unable to connect to " + strHost);
            return false;
        }
        try {
            this.writer = new ObjectOutputStream(this.socket.getOutputStream());
            this.writer.flush();
            this.reader = new ObjectInputStream(this.socket.getInputStream());
            //this.out = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (Exception e){e.printStackTrace();
            if (DEBUG) System.err.println("Unable to obtain stream to/from " + strHost);
            return false;
        }
        return true;
    }


    @Override
    public void handleSession()  {


        try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromUser,inputLine;
            boolean user = true;
            boolean seeAutomobiles = false;
            boolean receiveEvents = false;
            boolean receiveComments = false;
            boolean keepRunning=true;
            while(keepRunning){
                if(receiveEvents){
                    //
                    ArrayList<Event> listEvents = (ArrayList<Event>)reader.readObject();
                    this.eventList=listEvents;
                    receiveEvents = false;
                    keepRunning=false;
                }


                else{
                    inputLine = this.reader.readUTF();
                    Log.d("Socket talking","Socketsss" + inputLine);
                    //System.out.println("Server--" + inputLine);
                    if(inputLine.equalsIgnoreCase("Connection Established. Enter choice")){
                        this.writer.writeUTF("5");
                        this.writer.flush();
                        receiveEvents=true;
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void closeSession() {
        try {
            this.writer = null;
            this.reader = null;
            this.socket.close();
        }
        catch (IOException e){e.printStackTrace();
            if (DEBUG) System.err.println("Error closing socket to " + strHost);
        }
    }

  /*  public void run(){
        if (openConnection()){
            handleSession();
            closeSession();
        }
    }*/

    protected ArrayList<Event> doInBackground(Void... arg0) {
        if (openConnection()){
            handleSession();
            closeSession();
        }
        return eventList;
    }


}
