package com.spring.generator.util;

import com.spring.generator.model.Generator;

import java.util.Locale;

public class TextTool {

    public static String changeVariable(String text, Generator gen, String packagePath, String objectPackage, String modelName){
        return text
                .replace("${basePackage}", gen.basePackage)
                .replace("${packagePath}", packagePath)
                .replace("${objectPackage}",objectPackage.replace("\\","."))
                .replace("${objectName}",modelName)
                .replace("${lowerObjectName}",modelName.toLowerCase(Locale.ROOT));
    }
}
