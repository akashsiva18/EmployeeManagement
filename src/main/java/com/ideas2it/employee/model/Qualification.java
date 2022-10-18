package com.ideas2it.employee.model;


import javax.persistence.*;

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
@Entity(name = "qualification")
public class Qualification{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private int qualificationId;
    @Column(name = "course")
    private String course;

    public Qualification(String course) {
        this.course = course;
    }

    public Qualification() {
    }

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