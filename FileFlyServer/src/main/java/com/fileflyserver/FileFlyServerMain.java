package com.fileflyserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileFlyServerMain {
	final private static int PORT = 52685;
	public static void main ( String[] args ) {
		System.out.println("The server is online!");
		while (true) {
   		    /*try {
				ServerSocket serverSocket = new ServerSocket(PORT);
				Socket socket = serverSocket.accept();
				System.out.println("Connection established!");
				ObjectInputStream clientInputStream = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream()); 
				//Data data = (Data) clientInputStream.readObject();
				//System.out.println("Request type: " + data.getRequestType());
				String filename;
				byte[] filedata;
				switch (data.getRequestType()) {
					case 0: // SEND
					 	filename = data.getFileName();
						filedata = data.getFileData();
						System.out.println("File " + filename + " received!");
						System.out.write(filedata);
						System.out.flush();
						System.out.println();
						DataStorage.getInstance().storeFile(filename, filedata);
						break;
					case 1: // REQUEST
						filename = data.getFileName();
						System.out.println("Request for file " + filename + "...");
						filedata = DataStorage.getInstance().getFileByName(filename);
						System.out.write(filedata);
						System.out.flush();
						System.out.println();
						Data responseData = new Data(3, filename, filedata);
						clientOutputStream.writeObject(responseData);
						clientOutputStream.flush();
						break;
					case 2: // LIST

						break;
					default:
						System.out.println("Unknown request");
						break;
				}
				clientInputStream.close();
				clientOutputStream.close();
				socket.close();
				serverSocket.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			} catch (ClassNotFoundException exception) {
				exception.printStackTrace();
			}*/
			break;
		}
	}
}
