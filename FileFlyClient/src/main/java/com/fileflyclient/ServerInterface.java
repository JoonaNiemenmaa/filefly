package com.fileflyclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            ObjectOutputStream socketOutputStream = new ObjectOutputStream(socket.getOutputStream());

            //Data send = new Data(SEND, filename, b);
            //socketOutputStream.writeObject(send);
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
            ObjectOutputStream socketOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream socketInputStream = new ObjectInputStream(socket.getInputStream());
            
            //Data request = new Data(REQUEST, filename, null);
            //socketOutputStream.writeObject(request);
            socketOutputStream.flush();

            //Data data = (Data) socketInputStream.readObject();
            //file = data.getFileData();

            System.out.write(file);
            System.out.flush();

            socketInputStream.close();
            socketOutputStream.close();
            socket.close();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }/* catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }*/
        return file;
    }
}