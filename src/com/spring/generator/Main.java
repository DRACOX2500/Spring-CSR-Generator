package com.spring.generator;

import com.spring.generator.controller.FileController;
import com.spring.generator.util.Data;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Data data = new Data();
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter your main file path from your project :\n");
        data.path = scan.nextLine();

        System.out.println("Please enter your base package :\n");
        data.basePackage = scan.nextLine();

        System.out.println("Create Controllers ?(y/n) :\n");
        data.setCreateController(scan.nextLine());

        System.out.println("Create Services ?(y/n) :\n");
        data.setCreateService(scan.nextLine());

        System.out.println("Create Repositories ?(y/n) :\n");
        data.setCreateRepo(scan.nextLine());

        FileController fileController = new FileController(data.path);
        fileController.analyseModelDirectory();
        try{
            fileController.generate(data);
        }
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }


    }
}
