package com.spring.generator;

import com.spring.generator.controller.FileController;
import com.spring.generator.controller.GeneratorController;

public class Main {

    public static void main(String[] args) {

        GeneratorController generatorController = new GeneratorController();

        FileController fileController = new FileController(
                generatorController.getGen()
        );

        fileController.analyse();

        generatorController.generate(
                fileController.getFiles()
        );

    }
}
