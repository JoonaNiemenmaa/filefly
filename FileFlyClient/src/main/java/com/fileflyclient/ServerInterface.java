package com.fileflyclient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerInterface {
    private static ServerInterface serverInterface = null;
    final private int PORT;
    final private String SERVER_IP;
    final private int SEND = 0;
    final private int REQUEST = 1;
    final private int LIST = 2;
    private ServerInterface() {
        this.PORT = Main.getPort();
        this.SERVER_IP = Main.getServerIP();
    }
    public static ServerInterface getInstance() {
        if (serverInterface == null) {
            serverInterface = new ServerInterface();
        }
        return serverInterface;
    }
    public void sendFile(String FILENAME, byte[] b) {
        Socket socket = null;
        DataOutputStream socketOutputStream = null;
        try {
            socket = new Socket(SERVER_IP, PORT);
            socketOutputStream = new DataOutputStream(socket.getOutputStream());
            socketOutputStream.writeInt(SEND);
            socketOutputStream.writeInt(FILENAME.length());
            socketOutputStream.write(FILENAME.getBytes());
            socketOutputStream.write(b);
            socketOutputStream.flush();
            socketOutputStream.close();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (socketOutputStream != null) {
                    socketOutputStream.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }           
        }   
    }
}