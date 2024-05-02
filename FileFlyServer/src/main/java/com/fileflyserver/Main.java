package com.fileflyserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
	final private static int PORT = 52685;
	public static void main ( String[] args ) {
		System.out.println("The server is online!");
		ServerSocket serverSocket = null;
		Socket socket = null;
		while (true) {
			int request = 0;
   		    try {
				serverSocket = new ServerSocket(PORT);
				socket = serverSocket.accept();
				System.out.println("Connection established!");
				DataInputStream requestInputStream = new DataInputStream(socket.getInputStream());
				request = requestInputStream.readInt();    
				switch (request) {
					case 0:
						int filenameLength = requestInputStream.readInt();
						byte[] FILENAME_BYTES = requestInputStream.readNBytes(filenameLength);
					 	String FILENAME = new String(FILENAME_BYTES, StandardCharsets.UTF_8);
						byte[] file = requestInputStream.readAllBytes();
						System.out.println("File " + FILENAME + " received!");
						System.out.write(file);
						System.out.flush();
						System.out.println();
						DataStorage.getInstance().storeFile(FILENAME, file);
						break;
					case 1:
						
						break;
					case 2:

						break;
					default:
						System.out.println("Unknown request");
						break;
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			} finally {
				try {
					serverSocket.close();
					socket.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
	public static int getPort() { return PORT; }
}
