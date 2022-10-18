package com.ideas2it.employee.model;

import javax.persistence.*;

/**
 * <h2>Qualification</h2>
 * <p>
 * It is a model class for Role contains role Details.
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 09-09-2022
 **/
@Entity(name = "role")
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "description")
    private String description;

    public Role(String description) {
        this.description = description;
    }

    public Role(){
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    } 
    
}