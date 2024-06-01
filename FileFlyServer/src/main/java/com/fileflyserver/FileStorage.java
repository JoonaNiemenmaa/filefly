package com.fileflyserver;

import java.io.*;
import java.util.ArrayList;

public class FileStorage {
    private ArrayList<String> files = new ArrayList<>();
    private static FileStorage fileStorage = null;
    final private static String FILES_DIR = System.getProperty("user.home") + "/storage/";
    final private static String STORED_FILES = System.getProperty("user.home") + "/.stored_files";

    private FileStorage() {}

    public static FileStorage getInstance() {
        if (fileStorage == null) {
            fileStorage = new FileStorage();
        }
        File storage = new File(FILES_DIR);
        File stored_files = new File(STORED_FILES);
        if (!storage.exists()) {
            try {
                storage.mkdirs();
            } catch (SecurityException exception) {
                exception.printStackTrace();
            }
        }
        if (!stored_files.exists()) {
            try {
                stored_files.createNewFile();
            } catch (SecurityException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return fileStorage;
    }

    public ArrayList<String> getFileNames() { 
        loadFileNames();
        return files; 
    }

    public void saveFileNames() {
        try {
            ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(STORED_FILES));
            objectWriter.writeObject(files);
            objectWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();            
        }
    }

    public void loadFileNames() {
        try {
            ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(STORED_FILES));
            files = (ArrayList<String>) objectReader.readObject();
            objectReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void storeFile(String FILENAME, byte[] file) {
        try {
            FileOutputStream fileWriter = new FileOutputStream(FILES_DIR + FILENAME);
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
        try {
            FileInputStream fileReader = new FileInputStream(FILES_DIR + filename);
            file = fileReader.readAllBytes();
            fileReader.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return file;
    }

}
