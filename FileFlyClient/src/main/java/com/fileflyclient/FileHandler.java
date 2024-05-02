package com.fileflyclient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
	private static FileHandler fileHandler = null;

	private FileHandler() {}

	public static FileHandler getInstance() {
		if (fileHandler == null) {
			fileHandler = new FileHandler();
		}
		return fileHandler;
	}
	
	public byte[] readFile(String filename) {
		try {
			FileInputStream fileReader = new FileInputStream(filename);
			byte[] b = fileReader.readAllBytes();
			fileReader.close();
            return b;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
        return null;
    }
	public void writeFile(String filename, byte[] file) {
		try {
			FileOutputStream fileWriter = new FileOutputStream(filename);
			fileWriter.write(file);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
