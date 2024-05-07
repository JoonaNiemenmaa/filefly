package com.fileflyclient;

import java.io.*;
import java.util.ArrayList;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerInterface {
    private static ServerInterface serverInterface = null;
    final private int PORT = 52685;
    final private String SERVER_IP = "localhost";
    final private int SEND = 0;
    final private int ASK = 1;
    final private int LIST = 2;

    private ServerInterface() {}

    public static ServerInterface getInstance() {
        if (serverInterface == null) {
            serverInterface = new ServerInterface();
        }
        return serverInterface;
    }

    public void sendFile(String filename, byte[] filedata) {
        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            DataOutputStream socketOutputStream = new DataOutputStream(socket.getOutputStream());

            socketOutputStream.writeInt(SEND);
            socketOutputStream.writeInt(filename.length());
            socketOutputStream.write(filename.getBytes());
            socketOutputStream.write(filedata);
            socketOutputStream.flush();

            socketOutputStream.close();
            socket.close();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }   
    }

    public byte[] askForFile(String filename) {
        byte[] file = null;
        try {
            Socket socket = new Socket(SERVER_IP, PORT);

            DataOutputStream socketOutputStream = new DataOutputStream(socket.getOutputStream());
            socketOutputStream.writeInt(ASK);
            socketOutputStream.write(filename.getBytes());
            socketOutputStream.flush();
            socketOutputStream.close();

            socket.close();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try {
            Socket socket = new Socket(SERVER_IP, PORT);

            DataInputStream socketInputStream = new DataInputStream(socket.getInputStream());
            file = socketInputStream.readAllBytes();
            socketInputStream.close();

            System.out.write(file);
            System.out.flush();

            socket.close();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return file;
    }
	public ArrayList<String> requestList() {
		ArrayList<String> files = null;
		try {
			Socket socket = new Socket(SERVER_IP, PORT);
            DataOutputStream socketOutputStream = new DataOutputStream(socket.getOutputStream());
            socketOutputStream.writeInt(LIST);
            socketOutputStream.flush();
			socket.close();
		} catch (UnknownHostException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            ObjectInputStream socketInputStream = new ObjectInputStream(socket.getInputStream());
            files = (ArrayList<String>) socketInputStream.readObject();
            socketInputStream.close();
            socket.close();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
		return files;
	}
}
