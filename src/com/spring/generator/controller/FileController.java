package com.spring.generator.controller;


import com.spring.generator.util.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileController {

    private final String path;
    private final String pathModel;

    private ArrayList<String> nameModelList;

    private String objectPackage;
    private String modelName;

    public FileController(String path) {
        this.path = path;
        this.pathModel = this.path+"\\model";
        this.nameModelList = new ArrayList<>();
    }

    public void listFilesForFolder(String path) {
        File folder = new File(path);
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {

                listFilesForFolder(folder.getPath() + "\\" + fileEntry.getName());
            } else {

                String comparedPath = path.replace(this.pathModel,"");
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
                this.nameModelList.add(pathFile);

                System.out.println(pathFile);
            }
        }
    }

    public void analyseModelDirectory(){
        this.listFilesForFolder(this.path+"\\model");
        System.out.println(this.nameModelList.size() + " file(s) found !");
    }

    public void generate(Data data) throws IOException {
        System.out.println(data.isCreateController());
        if(data.isCreateRepo()){

            for(String file : this.nameModelList) {
                objectPackage = file.replace(".java","");
                String controllerPath = objectPackage + "Repository.java";
                String s[] = file.split("\\\\");
                modelName = s[s.length-1].replace(".java","");

                String absPath = data.path + "\\repository\\" + controllerPath;
                File controllerFile = new File(absPath);
                File directory = new File(absPath.replace("\\"+modelName+"Repository.java",""));


                System.out.println(controllerFile.getPath());
                if (!directory.exists()) {
                    directory.mkdirs();
                    if (!controllerFile.exists()) {
                        controllerFile.getParentFile().mkdir();
                        controllerFile.createNewFile();
                    }
                }

                // Read
                Scanner scanner = new Scanner( new File("./file/RepositoryFile") );
                String content = scanner.useDelimiter("\\A").next();
                scanner.close();

                // change variable
                content = this.changeVariable(content, data);

                // Write
                try{
                    FileWriter fw=new FileWriter(absPath);
                    fw.write(content);
                    fw.close();
                }catch(Exception e){System.out.println(e);}
                System.out.println("File generated !");
            }

        }

        if(data.isCreateService()){

            for(String file : this.nameModelList) {
                objectPackage = file.replace(".java","");
                String controllerPath = objectPackage + "Service.java";
                String s[] = file.split("\\\\");
                modelName = s[s.length-1].replace(".java","");

                String absPath = data.path + "\\service\\" + controllerPath;
                File controllerFile = new File(absPath);
                File directory = new File(absPath.replace("\\"+modelName+"Service.java",""));


                System.out.println(controllerFile.getPath());
                if (!directory.exists()) {
                    directory.mkdirs();
                    if (!controllerFile.exists()) {
                        controllerFile.getParentFile().mkdir();
                        controllerFile.createNewFile();
                    }
                }

                // Read
                Scanner scanner = new Scanner( new File("./file/ServiceFile") );
                String content = scanner.useDelimiter("\\A").next();
                scanner.close();

                // change variable
                content = this.changeVariable(content, data);

                // Write
                try{
                    FileWriter fw=new FileWriter(absPath);
                    fw.write(content);
                    fw.close();
                }catch(Exception e){System.out.println(e);}
                System.out.println("File generated !");
            }

        }

        if(data.isCreateController()){

            for(String file : this.nameModelList) {
                objectPackage = file.replace(".java","");
                String controllerPath = objectPackage + "Controller.java";
                String s[] = file.split("\\\\");
                modelName = s[s.length-1].replace(".java","");

                String absPath = data.path + "\\controller\\" + controllerPath;
                File controllerFile = new File(absPath);
                File directory = new File(absPath.replace("\\"+modelName+"Controller.java",""));


                System.out.println(controllerFile.getPath());
                if (!directory.exists()) {
                    directory.mkdirs();
                    if (!controllerFile.exists()) {
                        controllerFile.getParentFile().mkdir();
                        controllerFile.createNewFile();
                    }
                }

                // Read
                Scanner scanner = new Scanner( new File("./file/ControllerFile") );
                String content = scanner.useDelimiter("\\A").next();
                scanner.close();

                // change variable
                content = this.changeVariable(content, data);

                // Write
                try{
                    FileWriter fw=new FileWriter(absPath);
                    fw.write(content);
                    fw.close();
                }catch(Exception e){System.out.println(e);}
                System.out.println("File generated !");
            }

        }
    }

    public String changeVariable(String s, Data data){
        return s
                .replace("${basePackage}",data.basePackage)
                .replace("${objectPackage}",objectPackage.replace("\\","."))
                .replace("${objectName}",modelName)
                .replace("${lowerObjectName}",modelName.toLowerCase(Locale.ROOT));
    }
}
