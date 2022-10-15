package com.ideas2it.employee.DTO;

/**
 * <h2>RoleDTO</h2>
 * <p>
 * It is a model class for RoleDTO contains role Details.
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 09-09-2022
 **/
public class RoleDTO {

    private String description;

    public RoleDTO(){
    }

    public RoleDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    } 
    
}