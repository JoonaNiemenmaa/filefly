package com.fileflyclient;

public class Main {
	final private static int PORT = 52685;
	final private static String SERVER_IP = "localhost";
	public static void main( String[] args ) {
		final String FILENAME = "test.txt";
		FileReader fileReader = FileReader.getInstance();
		byte[] b = fileReader.readFile(FILENAME);	
		ServerInterface serverInterface = ServerInterface.getInstance();
		serverInterface.sendFile(FILENAME, b);
	}
	public static int getPort() { return PORT; }
	public static String getServerIP() { return SERVER_IP; }
}
