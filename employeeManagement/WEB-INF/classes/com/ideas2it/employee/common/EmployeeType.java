package com.ideas2it.employee.common;

/**
 * It is a enum class that contains EmployeeType.
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public enum EmployeeType {
    TRAINER("trainer"),
    TRAINEE("trainee");

    public String type;
 
    EmployeeType(String type) {
        this.type = type;
    }
}


