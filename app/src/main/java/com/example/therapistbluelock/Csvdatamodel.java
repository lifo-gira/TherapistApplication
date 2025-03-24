package com.example.therapistbluelock;

import java.util.List;

public class Csvdatamodel {
    String patientName;
    int age;
    String assessmentMode;
    List<String> parameters; // Holds values of parameters

    public Csvdatamodel(String patientName, int age, String assessmentMode, List<String> parameters) {
        this.patientName = patientName;
        this.age = age;
        this.assessmentMode = assessmentMode;
        this.parameters = parameters;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAssessmentMode() {
        return assessmentMode;
    }

    public void setAssessmentMode(String assessmentMode) {
        this.assessmentMode = assessmentMode;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}
