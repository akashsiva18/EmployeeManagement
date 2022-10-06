package com.ideas2it.employee.model;

/**
 * <h2>Qualification</h2>
 * <p>
 * It is a model class for Qualification contains Qualification Details.
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 09-09-2022
 **/
public class Qualification{

    private int qualificationId;
    private String course;

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    } 

    @Override
    public String toString() {
        return getCourse();
    }
}