package com.fileflyclient;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {
	private static FileReader fileReader = null;
	private FileReader() {}
	public static FileReader getInstance() {
		if (fileReader == null) {
			fileReader = new FileReader();
		}
		return fileReader;
	}
	public byte[] readFile(String FILENAME) {
        FileInputStream inputStream = null;
		System.out.println(System.getProperty("user.dir"));
		try {
			inputStream = new FileInputStream(FILENAME);
			byte[] b = inputStream.readAllBytes();
            return b;
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
	    }
        return null;
    } 
}
