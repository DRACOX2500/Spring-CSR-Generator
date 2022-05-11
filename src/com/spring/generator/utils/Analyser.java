package com.spring.generator.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Analyser {

    public static String searchInFile(File file, String regex){
        Scanner scanner = null;
        String target = "";
        try {
            scanner = new Scanner( file );
            String content = scanner.useDelimiter("\\A").next();
            scanner.close();

            scanner = new Scanner(content);
            target = scanner.findInLine(Pattern.compile(regex));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return target;
    }
}
