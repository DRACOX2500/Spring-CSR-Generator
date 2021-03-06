package com.spring.generator.model;

public class Generator {

    public String path;

    public String mainPath;

    public String basePackage;

    private boolean createController;

    private boolean createService;

    private boolean createRepo;

    private boolean overwriteFiles;

    public void setCreateController(final String s) {
        this.createController = s.equals("y");
    }

    public void setCreateService(final String s) {
        this.createService = s.equals("y");
    }

    public void setCreateRepo(final String s) {
        this.createRepo = s.equals("y");
    }

    public boolean isCreateController() {
        return createController;
    }

    public boolean isCreateService() {
        return createService;
    }

    public boolean isCreateRepo() {
        return createRepo;
    }

    public void setOverwriteFiles(final String s) {
        this.overwriteFiles = s.equals("y");
    }

    public boolean isOverwriteFiles() {
        return overwriteFiles;
    }
}
