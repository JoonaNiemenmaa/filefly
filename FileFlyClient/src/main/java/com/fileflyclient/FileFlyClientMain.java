package com.fileflyclient;

public class FileFlyClientMain {
	// args[0] is the command type ("send", "request", "list") and args[1] is the input argument, usually the filename
	public static void main( String[] args ) {
		String command = "request";
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
