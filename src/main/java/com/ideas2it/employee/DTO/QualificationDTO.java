package com.ideas2it.employee.DTO;


/**
 * <h2>QualificationDTO</h2>
 * <p>
 * It is a model class for QualificationDTO contains QualificationDTO Details.
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 09-09-2022
 **/
public class QualificationDTO {
    private String course;

    public QualificationDTO(String course) {
        this.course = course;
    }

    public QualificationDTO() {
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