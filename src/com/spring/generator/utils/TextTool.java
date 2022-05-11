package com.spring.generator.utils;

import com.spring.generator.model.Generator;

import java.util.Locale;

public class TextTool {

    public static String changeVariable(String text, Generator gen, String packagePath, String objectPackage, String modelName){
        return text
                .replace("${basePackage}", gen.basePackage)
                .replace("${packagePath}", packagePath)
                .replace("${objectPackage}",objectPackage.replace("\\","."))
                .replace("${objectName}",modelName)
                .replace("${lowerObjectName}",toCamelCase(modelName));
    }

    /**
     * Warning ! Doesn't actually transform into a camelCase
     * ex : FirstCharacter --> firstCharacter
     * but if your string is Titlecase or lowercase
     * Firstcharacter --> firstcharacter
     * firstcharacter --> firstcharacter
     * @param s
     * @return
     */
    public static String toCamelCase(String s) {
        return String.valueOf(s.charAt(0)).toLowerCase(Locale.ROOT) + s.substring(1);
    }
}
