package com.example.supersaiyans.hangout.client;

import android.os.AsyncTask;
import android.util.Log;

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
public class DefaultSocketClientToGetUsers extends AsyncTask<Void,Void,User> implements SocketClientInterface, SocketClientConstant {
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket socket;
    private String strHost;
    private int iPort;
    ArrayList<Event> eventList;
    private int userID;
    User user;


    public DefaultSocketClientToGetUsers(Socket socket) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public DefaultSocketClientToGetUsers(String strHost, int iPort) {
        setiPort (iPort);
        setStrHost (strHost);
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
            boolean receiveUser = false;
            boolean receiveComments = false;
            boolean keepRunning=true;
            while(keepRunning){
                if(receiveUser){
                    //
                    this.user= (User)this.reader.readObject();
                    receiveUser = false;
                    keepRunning=false;
                }


                else{
                    inputLine = this.reader.readUTF();
                    Log.d("Socket talking","Socketsss" + inputLine);
                    //System.out.println("Server--" + inputLine);
                    if(inputLine.equalsIgnoreCase("Connection Established. Enter choice")){
                            this.writer.writeUTF("8");
                            this.writer.flush();
                            //receiveEvents=true;
                    }

                    else if(inputLine.equalsIgnoreCase("Send user id")){
                        this.writer.writeUTF(Integer.toString(this.userID));
                        this.writer.flush();
                        receiveUser=true;
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

    protected User doInBackground(Void... arg0) {
        if (openConnection()){
            handleSession();
            closeSession();
        }
        return user;
    }


}
