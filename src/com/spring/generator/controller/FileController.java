package com.spring.generator.controller;


import com.spring.generator.model.Files;
import com.spring.generator.model.Generator;

import java.io.File;
import java.util.ArrayList;

public class FileController {

    private final Generator gen;

    private final Files files;

    public FileController(Generator generator) {

        this.gen = generator;
        this.files = new Files();
    }

    public Files getFiles() {
        return files;
    }

    public void analyse(){
        this.files.setFilesList(this.listFilesForFolder(
                this.gen.path,
                this.files.getFilesList()
        ));
        System.out.println(this.files.getFilesList().size() + " file(s) found !");
    }

    public ArrayList<String> listFilesForFolder(String path, ArrayList<String> allFiles) {

        File folder = new File(path);
        if(!folder.exists() || !folder.isDirectory()){
            return null;
        }

        for (final File fileEntry : folder.listFiles()) {

            if (fileEntry.isDirectory() && fileEntry.listFiles().length > 0) {

                listFilesForFolder(folder.getPath() + "\\" + fileEntry.getName(), allFiles);

            } else {
                String comparedPath = path.replace(this.gen.path,"");
                String pathFile = "";

                if(comparedPath.length() > 0){
                    pathFile = comparedPath + "\\" + fileEntry.getName();

                    if(pathFile.charAt(0) == '\\'){
                        pathFile = pathFile.substring(1);

                    }

                }
                else {
                    pathFile = fileEntry.getName();

                }
                allFiles.add(pathFile);
            }
        }

        return allFiles;
    }

    public static String getPackage(String[] strings){
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < strings.length-1; i++) {
            s.append(".").append(strings[i]);
        }

        return s.toString();
    }

}
