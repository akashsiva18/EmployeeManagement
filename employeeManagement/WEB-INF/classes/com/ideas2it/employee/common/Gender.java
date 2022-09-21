package com.ideas2it.employee.common;

/**
 * It is a enum class that contains Genders.
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHERS("Others");

    public String type;

    Gender(String type) {
        this.type = type;
    }
}