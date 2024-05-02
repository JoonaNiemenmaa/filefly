package com.fileflyclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerInterface {
    private static ServerInterface serverInterface = null;
    final private int PORT = 52685;
    final private String SERVER_IP = "localhost";

    // Fields referring to server request types
    final private int SEND = 0;
    final private int REQUEST = 1;
    final private int LIST = 2;

    private ServerInterface() {}

    public static ServerInterface getInstance() {
        if (serverInterface == null) {
            serverInterface = new ServerInterface();
        }
        return serverInterface;
    }

    public void sendFile(String filename, byte[] b) {
        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            DataOutputStream socketOutputStream = new DataOutputStream(socket.getOutputStream());

            socketOutputStream.writeInt(SEND);
            socketOutputStream.writeInt(filename.length());
            socketOutputStream.write(filename.getBytes());
            socketOutputStream.write(b);
            socketOutputStream.flush();

            socketOutputStream.close();
            socket.close();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }   
    }

    public byte[] requestFile(String filename) {
        byte[] file = null;
        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            DataOutputStream socketOutputStream = new DataOutputStream(socket.getOutputStream());

            socketOutputStream.writeInt(REQUEST);
            socketOutputStream.write(filename.getBytes());
            socketOutputStream.flush();

            DataInputStream socketInputStream = new DataInputStream(socket.getInputStream());

            socketInputStream.readFully(file);

            socketInputStream.close();
            socketOutputStream.close();
            socket.close();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return file;
    }
}