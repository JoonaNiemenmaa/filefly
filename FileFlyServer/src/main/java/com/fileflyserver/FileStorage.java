package com.fileflyserver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileStorage {
    private ArrayList<String> files = new ArrayList<>();
    private static FileStorage fileStorage = null;
    final private String STORAGE_FILE_NAME = "storage/";
    final private String FILENAMESDATA_FILE_NAME = "files.data";

    private FileStorage() {}

    public static FileStorage getInstance() {
        if (fileStorage == null) {
            fileStorage = new FileStorage();
        }
        return fileStorage;
    }

    public ArrayList<String> getFileNames() { 
        loadFileNames();
        return files; 
    }

    public void saveFileNames() {
        try {
            ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE_NAME + FILENAMESDATA_FILE_NAME));
            objectWriter.writeObject(files);
            objectWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();            
        }
    }

    public void loadFileNames() {
        try {
            ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(STORAGE_FILE_NAME + FILENAMESDATA_FILE_NAME));
            files = (ArrayList<String>) objectReader.readObject();
            objectReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void storeFile(String FILENAME, byte[] file) {
        FileOutputStream fileWriter = null;
        try {
            fileWriter = new FileOutputStream(STORAGE_FILE_NAME + FILENAME);
            fileWriter.write(file);
            fileWriter.flush();
            fileWriter.close();
            loadFileNames();
            files.add(FILENAME);
            saveFileNames();
        } catch (IOException exception) {
            exception.printStackTrace();
        }   
    }

    public byte[] getFileByName(String filename) {
        byte[] file = null;
        FileInputStream fileReader = null;
        try {
            fileReader = new FileInputStream(STORAGE_FILE_NAME + filename);
            file = fileReader.readAllBytes();
            fileReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return file;
    }

}
