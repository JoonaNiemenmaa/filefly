package com.fileflyserver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataStorage {
    private ArrayList<String> files = new ArrayList<>();
    private static DataStorage dataStorage = null;
    final private String STORAGE_FILE_NAME = "files.data";
    private DataStorage() {}
    public static DataStorage getInstance() {
        if (dataStorage == null) {
            dataStorage = new DataStorage();
        }
        return dataStorage;
    }
    public ArrayList<String> getFileNames() { return files; }
    public void saveFileNames() {
        ObjectOutputStream objectWriter = null;
        try {
            objectWriter = new ObjectOutputStream(new FileOutputStream("received/" + STORAGE_FILE_NAME));
            objectWriter.writeObject(files);
        } catch (IOException exception) {
            
        } finally {
            try {
                if (objectWriter != null) {
                    objectWriter.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            } 
        }
    }
    public void storeFile(String FILENAME, byte[] file) {
        FileOutputStream fileWriter = null;
        try {
            fileWriter = new FileOutputStream("received/" + FILENAME);
            fileWriter.write(file);
            fileWriter.flush();
            files.add(FILENAME);
            saveFileNames();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
