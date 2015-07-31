package com.example.supersaiyans.hangout.client;

/**
 * Created by Chetan on 7/31/2015.
 */
public interface SocketClientInterface {
    public boolean openConnection();
    public void handleSession();
    public void closeSession();
}
