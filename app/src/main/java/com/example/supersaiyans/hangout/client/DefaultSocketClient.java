package com.example.supersaiyans.hangout.client;

import android.os.AsyncTask;
import android.util.Log;

import com.example.supersaiyans.hangout.model.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Chetan on 7/31/2015.
 */
public class DefaultSocketClient extends AsyncTask<Void,Void,Void> implements SocketClientInterface, SocketClientConstant {
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket socket;
    private String strHost;
    private int iPort;
    boolean createEvent;
    Event event;


    public DefaultSocketClient(String strHost, int iPort) {
        setiPort (iPort);
        setStrHost (strHost);
    }

    public DefaultSocketClient(Socket socket) {
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

    public void setCreateEvent(boolean value) {
        this.createEvent = value;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
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

                    receiveEvents = false;
                    this.writer.writeUTF("Great thanks");
                    this.writer.flush();
                }

                else if(receiveComments){

                }

                else{
                    inputLine = this.reader.readUTF();
                    Log.d("Socket talking","Socketsss" + inputLine);
                    //System.out.println("Server--" + inputLine);
                    if(inputLine.equalsIgnoreCase("Connection Established. Enter choice")){
                        if(this.createEvent){
                            this.writer.writeUTF("1");
                            this.writer.flush();
                        }
                    }
                    if(inputLine.equalsIgnoreCase("Send Event Object")){
                       this.writer.writeObject(this.event);
                        keepRunning=false;
                    }
                    /*if(user){
                        if(seeAutomobiles){
                            System.out.println("Choose one from the list, preceed by Send auto-");
                            seeAutomobiles=false;
                            receiveAutomobile=true;
                        }
                        fromUser = stdIn.readLine();
                        if (fromUser != null) {
                            System.out.println("User: " + fromUser);
                            this.writer.writeUTF(fromUser);
                            this.writer.flush();
                            if(fromUser.equalsIgnoreCase("Show Automobiles"))
                                seeAutomobiles=true;
                            if(fromUser.equalsIgnoreCase("Bye"))
                                keepRunning=false;
                        }
                        if(seeAutomobiles)
                            user=true;
                        else
                            user=false;
                    }
                    if(inputLine.equalsIgnoreCase("Send Automobile")){
                        if(this.prop!=null){System.out.println("lets send");
                            this.writer.writeObject(this.prop);System.out.println("sent");
                            this.writer.flush();
                        }
                    }
                    if(inputLine.equalsIgnoreCase("Automobile added")){
                        this.writer.writeUTF("Great thanks");
                        this.writer.flush();
                    }*/
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

    protected Void doInBackground(Void... arg0) {
        if (openConnection()){
            handleSession();
            closeSession();
        }
        return null;
    }
}
