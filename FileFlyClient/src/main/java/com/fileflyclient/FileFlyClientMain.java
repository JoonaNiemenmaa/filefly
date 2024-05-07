package com.fileflyclient;

import java.util.ArrayList;

public class FileFlyClientMain {
	// args[0] is the command type ("send", "request", "list")
	// "send" and "request" commands take an argument for the file name to execute. The filename is read from args[1]
	public static void main( String[] args ) {
		String command = args[0];
		String filename;
		byte[] filedata;
		switch (command) {
			case "send":
				filename = args[1];
				filedata = FileHandler.getInstance().readFile(filename);
				ServerInterface.getInstance().sendFile(filename, filedata);
				break;
			case "ask":
				filename = args[1];
				filedata = ServerInterface.getInstance().askForFile(filename);
				FileHandler.getInstance().writeFile(filename, filedata);
				break;
			case "list":
				ArrayList<String> files = ServerInterface.getInstance().requestList();
				System.out.println("Retrieving files from file server...");
				for (String file : files) {
					System.out.println(file);
				}
				break;
		
			default:
				break;
		}	
	}
}
