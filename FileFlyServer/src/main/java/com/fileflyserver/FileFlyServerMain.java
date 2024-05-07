package com.fileflyserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class FileFlyServerMain {
	final private static int PORT = 52685;
	public static void main ( String[] args ) {
		System.out.println("The server is online!");
		while (true) {
			ServerSocket serverSocket = null;
			Socket socket = null;
			DataInputStream socketDataInputStream = null;
			DataOutputStream socketDataOutputStream = null;
			ObjectOutputStream socketObjectOutputStream = null;
   		    try {
				serverSocket = new ServerSocket(PORT);
				socket = serverSocket.accept();
				System.out.println("Connection established!");
				socketDataInputStream = new DataInputStream(socket.getInputStream());
				int request = socketDataInputStream.readInt();
				String filename;
				byte[] filedata;
				switch (request) {
					case 0: // SEND
						int filenameLength = socketDataInputStream.readInt();
					 	filename = new String(socketDataInputStream.readNBytes(filenameLength), StandardCharsets.UTF_8);
						filedata = socketDataInputStream.readAllBytes();
						socketDataInputStream.close();

						System.out.println("File " + filename + " received!");
						System.out.write(filedata);
						System.out.flush();
						System.out.println();

						FileStorage.getInstance().storeFile(filename, filedata);
						break;
					case 1: // ASK
						filename = new String(socketDataInputStream.readAllBytes(), StandardCharsets.UTF_8);
						socketDataInputStream.close();

						System.out.println("Request for file " + filename + "...");
						filedata = FileStorage.getInstance().getFileByName(filename);

						System.out.write(filedata);
						System.out.flush();
						System.out.println();

						socket = serverSocket.accept();

						socketDataOutputStream = new DataOutputStream(socket.getOutputStream());
						socketDataOutputStream.write(filedata);
						socketDataOutputStream.flush();
						socketDataOutputStream.close();
						break;
					case 2: // LIST
						socketDataInputStream.close();

						socket = serverSocket.accept();

						socketObjectOutputStream = new ObjectOutputStream(socket.getOutputStream());
						socketObjectOutputStream.writeObject(FileStorage.getInstance().getFileNames());
						socketObjectOutputStream.flush();
						socketObjectOutputStream.close();
						break;
					default:
						System.out.println("Unknown request");
						break;
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			} finally {
			   try {
				   if (socketDataInputStream != null) { socketDataInputStream.close(); }
				   if (socketDataOutputStream != null) { socketDataOutputStream.close(); }
				   if (socketObjectOutputStream != null) { socketObjectOutputStream.close(); }
				   if (socket != null) { socket.close(); }
				   if (serverSocket != null) { serverSocket.close(); }
			   } catch (IOException exception) {
				   exception.printStackTrace();
			   }
		    }
		}
	}
}
