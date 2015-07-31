package com.example.supersaiyans.hangout.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Chetan on 7/31/2015.
 */
public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstant {
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket socket;
    private String strHost;
    private int iPort;


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




    @Override
    public boolean openConnection(){
        try {
            this.socket = new Socket(strHost, iPort);
        }
        catch(IOException socketError){socketError.printStackTrace();
            if (DEBUG) System.err.println("Unable to connect to " + strHost);
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
        /*try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromUser,inputLine;
            boolean user = true;
            boolean seeAutomobiles = false;
            boolean receiveAutomobile = false;
            boolean keepRunning=true;
            while(keepRunning){
                if(receiveAutomobile){
                    Automobile a = (Automobile) this.reader.readObject();
                    configureForUser(a);
                    receiveAutomobile = false;
                    this.writer.writeUTF("Great thanks");
                    this.writer.flush();
                }
                else{
                    inputLine = this.reader.readUTF();
                    System.out.println("Server--" + inputLine);
                    if(inputLine.equalsIgnoreCase("Connection Established. Enter choice")){
                        user=true;
                    }
                    if(user){
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
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
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

    public void run(){
        if (openConnection()){
            handleSession();
            closeSession();
        }
    }
}
