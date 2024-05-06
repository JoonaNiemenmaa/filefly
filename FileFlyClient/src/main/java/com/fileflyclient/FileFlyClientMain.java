package com.fileflyclient;

public class FileFlyClientMain {
	// args[0] is the command type ("send", "request", "list")
	// "send" and "request" commands take an argument for the file name to execute. The filename is read from args[1]
	public static void main( String[] args ) {
		String command = "send";
		String filename;
		byte[] file;
		switch (command) {
			case "send":
				filename = "test.txt";
				file = FileHandler.getInstance().readFile(filename);	
				ServerInterface.getInstance().sendFile(filename, file);
				break;
			case "request":
				filename = "test.txt";
				file = ServerInterface.getInstance().requestFile(filename);
				FileHandler.getInstance().writeFile(filename, file);
				break;
			case "list":

				break;
		
			default:
				break;
		}	
	}
}
