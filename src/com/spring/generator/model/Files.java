package com.spring.generator.model;

import java.util.ArrayList;

public class Files {

    private ArrayList<String> filesList;

    public Files() {
        this.filesList = new ArrayList<>();
    }

    public ArrayList<String> getFilesList() {
        return filesList;
    }

    public void setFilesList(ArrayList<String> filesList) {
        this.filesList = filesList;
    }
}
