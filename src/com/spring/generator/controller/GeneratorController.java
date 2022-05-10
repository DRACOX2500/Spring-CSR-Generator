package com.spring.generator.controller;

import com.spring.generator.model.Files;
import com.spring.generator.model.Generator;
import com.spring.generator.util.Analyser;
import com.spring.generator.util.CSR;
import com.spring.generator.util.Question;
import com.spring.generator.util.TextTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class GeneratorController {

    private Generator gen;

    public GeneratorController() {

        this.gen = new Generator();

        // Main project path
        this.gen.path = this.userInput(Question.Q1);
        this.gen.mainPath = this.gen.path.replaceAll("(\\\\|\\/)model","");

        // Base package
        this.gen.basePackage = this.getBasePackage(this.gen.path);
        System.out.println("Base Package : " + this.gen.basePackage);

        // Generate Question CSR
        this.gen.setCreateController(this.userInput(Question.Q3));
        this.gen.setCreateService(this.userInput(Question.Q4));
        this.gen.setCreateRepo(this.userInput(Question.Q5));
    }

    private String userInput(String question){

        Scanner scan = new Scanner(System.in);

        System.out.println(question);
        return scan.nextLine();
    }

    public String getBasePackage(String path) {
        File modelPack = new File(path);
        String pack = "";

        // Method 1 : try to get base package from first model
        for(File file : modelPack.listFiles()){
            pack = findPack(pack, file);
        }

        // Method 2 : try to get base package from Main file
        if(pack.equals("")){
            File[] mainDirectory = modelPack.getParentFile().listFiles();
            for(File file : mainDirectory){
                pack = findPack(pack, file);
            }
        }


        return pack;
    }

    private String findPack(String pack, File file) {
        if(file.isFile()){
            pack = Analyser.searchInFile(file, "package ([a-z0-9]*\\.?)+;");
            if(!pack.equals("")){
                pack = pack.replace("package ","");
                pack = pack.substring(0, pack.length()-1);
                pack = pack.replaceAll("\\.model(\\..*)*","");
            }
        }
        return pack;
    }

    public void generate(Files files){

        if(this.gen.isCreateRepo()){

            if(this.generateCSR(CSR.Type.REPOSITORY,files)){
                System.out.println(CSR.Type.REPOSITORY + "(ies) created !");
            }
            else {
                System.out.println(CSR.Type.REPOSITORY + "(ies) generation failed  !");
            }
        }

        if(this.gen.isCreateService()){

            if(this.generateCSR(CSR.Type.SERVICE,files)){
                System.out.println(CSR.Type.SERVICE + "(s) created !");
            }
            else {
                System.out.println(CSR.Type.SERVICE + "(ies) generation failed  !");
            }
        }

        if(this.gen.isCreateController()){

            if(this.generateCSR(CSR.Type.CONTROLLER,files)){
                System.out.println(CSR.Type.CONTROLLER + "(s) created !");
            }
            else {
                System.out.println(CSR.Type.CONTROLLER + "(ies) generation failed  !");
            }
        }
    }

    public Generator getGen() {
        return gen;
    }

    private boolean generateCSR(final String type, Files files) {

        for ( String file : files.getFilesList())
        {
            // Init
            String objPackage = file.replace(".java","");
            String csrPath = objPackage + type + ".java";

            String[] s = objPackage.split("\\\\");
            String packagePath = FileController.getPackage(s);
            String modelName = s[s.length-1];

            String absPath = this.gen.mainPath + "\\" + type.toLowerCase(Locale.ROOT) + "\\" + csrPath;

            File csrFile = new File(absPath);
            File directory = new File(
                    absPath.replace(
                            "\\" + modelName + type + ".java",
                            ""
                    )
            );

            // Create file
            if (!directory.exists()) {
                directory.mkdirs();
                if (!csrFile.exists()) {
                    csrFile.getParentFile().mkdir();
                    try {
                        csrFile.createNewFile();
                    }catch (IOException ioe){
                        ioe.printStackTrace();
                        return false;
                    }
                }
            }

            // Write file
            try {
                Scanner scanner = new Scanner( new File("./file/"+type+"File") );
                String content = scanner.useDelimiter("\\A").next();
                scanner.close();

                content = TextTool.changeVariable(content,gen,packagePath,objPackage,modelName);

                FileWriter fw = new FileWriter(absPath);
                fw.write(content);
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }

        return true;

    }
}
