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
            // exception.printStackTrace();
            System.err.println("Error: Could not connect to file server");
            System.exit(0);
        } catch (IOException exception) {
            // exception.printStackTrace();
            System.err.println("Error: Could not transfer file to server");
            System.exit(0);
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
            // exception.printStackTrace();
            System.err.println("Error: Could not connect to file server");
            System.exit(0);
        } catch (IOException exception) {
            // exception.printStackTrace();
            System.err.println("Error: Could not request file from server");
            System.exit(0);
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
            // exception.printStackTrace();
            System.err.println("Error: Could not connect to file server");
            System.exit(0);
        } catch (IOException exception) {
            // exception.printStackTrace();
            System.out.println("Error: Could not retrieve file from server");
            System.exit(0);
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
            // exception.printStackTrace();
            System.err.println("Error: Could not connect to file server");
            System.exit(0);
		} catch (IOException exception) {
			// exception.printStackTrace();
            System.out.println("Error: Could not request files from server");
            System.exit(0);
		}
        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            ObjectInputStream socketInputStream = new ObjectInputStream(socket.getInputStream());
            files = (ArrayList<String>) socketInputStream.readObject();
            socketInputStream.close();
            socket.close();
        } catch (UnknownHostException exception) {
            // exception.printStackTrace();
            System.err.println("Error: Could not connect to file server");
            System.exit(0);
        } catch (IOException exception) {
            // exception.printStackTrace();
            System.err.println("Error: Could not receive file names from server");
            System.exit(0);
        } catch (ClassNotFoundException exception) {
            // exception.printStackTrace();
            System.err.println("Error: Casting received data to ArrayList failed");
            System.exit(0);
        }
		return files;
	}
}
