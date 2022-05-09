package com.spring.generator.controller;

import com.spring.generator.model.Files;
import com.spring.generator.model.Generator;
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

        // Base package
        // TODO : auto
        this.gen.basePackage = this.userInput(Question.Q2);

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

    public void generate(Files files){

        if(this.gen.isCreateRepo()){

            if(this.generateCSR(CSR.Type.REPOSITORY,files)){
                System.out.println(CSR.Type.REPOSITORY + "(ies) created !");
            }
        }

        if(this.gen.isCreateService()){

            if(this.generateCSR(CSR.Type.SERVICE,files)){
                System.out.println(CSR.Type.SERVICE + "(s) created !");
            }
        }

        if(this.gen.isCreateController()){

            if(this.generateCSR(CSR.Type.CONTROLLER,files)){
                System.out.println(CSR.Type.SERVICE + "(s) created !");
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

            String absPath = this.gen.path + "\\" + type.toLowerCase(Locale.ROOT) + "\\" + csrPath;

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
