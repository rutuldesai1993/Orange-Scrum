package com.example.rutuldesai.orangescrum;

public class ProjectModel {

    public final String PROJECT_ID;
    public final String PROJECT_NAME;

    public ProjectModel(String PROJECT_ID, String PROJECT_NAME) {
        this.PROJECT_ID = PROJECT_ID;
        this.PROJECT_NAME = PROJECT_NAME;
    }

    public String getPROJECT_ID() {
        return PROJECT_ID;
    }

    public String getPROJECT_NAME() {
        return PROJECT_NAME;
    }

    @Override
    public String toString() {
        return "ProjectModel{" +
                "PROJECT_ID='" + PROJECT_ID + '\'' +
                ", PROJECT_NAME='" + PROJECT_NAME + '\'' +
                '}';
    }
}
