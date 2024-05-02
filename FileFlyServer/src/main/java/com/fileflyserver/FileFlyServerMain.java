package com.fileflyserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class FileFlyServerMain {
	final private static int PORT = 52685;
	public static void main ( String[] args ) {
		System.out.println("The server is online!");
		while (true) {
   		    try {
				ServerSocket serverSocket = new ServerSocket(PORT);
				Socket socket = serverSocket.accept();
				System.out.println("Connection established!");
				DataInputStream clientInputStream = new DataInputStream(socket.getInputStream());
				DataOutputStream clientOutputStream = new DataOutputStream(socket.getOutputStream()); 
				int request = clientInputStream.readInt();    
				String filename;
				byte[] file;
				switch (request) {
					case 0:
						int filenameLength = clientInputStream.readInt();
						byte[] FILENAME_BYTES = clientInputStream.readNBytes(filenameLength);
					 	filename = new String(FILENAME_BYTES, StandardCharsets.UTF_8);
						file = clientInputStream.readAllBytes();
						System.out.println("File " + filename + " received!");
						System.out.write(file);
						System.out.flush();
						System.out.println();
						DataStorage.getInstance().storeFile(filename, file);
						break;
					case 1:
						filename = new String(clientInputStream.readAllBytes(), StandardCharsets.UTF_8);
						System.out.println("Request for file " + filename + "...");
						file = DataStorage.getInstance().getFileByName(filename);
						System.out.write(file);
						System.out.flush();
						System.out.println();
						clientOutputStream.write(file);
						clientOutputStream.flush();
						break;
					case 2:

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
			}
		}
	}
}
