package com.francisbailey.irc;

import com.francisbailey.irc.Client;
import com.francisbailey.irc.Connection;
import com.francisbailey.irc.Modes;
import com.francisbailey.irc.SendableMessage;


import java.util.ArrayList;

/**
 * Created by fbailey on 05/05/17.
 */
public class MockConnection implements Connection, ModeTarget {

    private ArrayList<SendableMessage> outputBuffer;
    private Client clientInfo;
    private Boolean registered;


    public MockConnection() {
        this.outputBuffer = new ArrayList<>();
    }


    public String getLastOutput() {

        int size = this.outputBuffer.size();

        if (size > 0) {
            return this.outputBuffer.get(size - 1).compile();
        }

        return null;
    }

    public String getAllOutput() {
        String output = "";

        for (SendableMessage m: this.outputBuffer) {
            output += m.compile();
        }

        return output;
    }


    public void clearOutputBuffer() {
        this.outputBuffer.clear();
    }


    @Override
    public void terminate() {

    }

    @Override
    public void send(SendableMessage message) {
        this.outputBuffer.add(message);
    }

    public void register(Client clientInfo) {
        this.clientInfo = clientInfo;
        this.registered = true;
    }

    public Boolean isRegistered() {
        return this.registered;
    }

    public Client getClientInfo() {
        return this.clientInfo;
    }

    public void setClientInfo(Client c) {
        this.clientInfo = c;
    }

    public String getHostNameInfo() {
        return "";
    }

    @Override
    public String getTargetType() {
        return "mock-user";
    }
}
