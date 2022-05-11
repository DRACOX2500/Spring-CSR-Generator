# Spring-CSR-Generator
![](https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white)
![](https://img.shields.io/badge/version-0.3_beta-00CC00)

Controller, Service, Repository Generator for Spring project

# Requirement

## Your system
![](https://img.shields.io/badge/Java_SE-8+-success?logo=java&logoColor=white)

Please have at least [Java 8](https://www.java.com/en/download/manual.jsp) or newer !

## Your project
![](https://img.shields.io/badge/Spring-0?logo=spring&logoColor=white)

Only for [Spring Boot](https://spring.io/projects/spring-boot) project

Use the following dependencies in your project :
- Spring Web (for endpoints)
- Spring Data (only for Repository)

# How to use ?

## Method start.bat

Download the [project](https://drive.google.com/file/d/1E7uKv-gKwb-k8MrPsS6ESjVwmKKoN44u/view?usp=sharing) !

1. Execute "start.bat"
2. Follow instructions...

## Method .jar

1. Download the [.jar](https://drive.google.com/file/d/1LpOMVVcNrUfGHbtOvv-LYEjQ60u65zKg/view?usp=sharing) and clone the project
2. Keep the .jar in the root folder of this project
3. Open terminal in the same directory where the .jar is located
4. Use the following command to start the program :
`java -jar Spring-CSR-Generator.jar`
5. Follow instructions...

Normally controllers, services and repositories will be generated...

# Versions
## Version 0.4 beta

Downgrade Java version to compile and use the project

Optimise ControllerFile

Have the choice to overwrite existing files or not

Fix bug :
- The questions did not correspond to the correct generator functions
- CSR files were always overwritten
- The .bat crash in case of Java version error

## Version 0.3 beta

Adding endpoints in controllers !

The base package is automatically retrieved !

Fix bug : 
- Bad writing of the "ServiceFile" file
- Bad log in terminal

## Version 0.2 beta

Rebuilt the project for cleaner code.

## Version 0.1 beta

Project initialization

All the CSR generation process is functional.