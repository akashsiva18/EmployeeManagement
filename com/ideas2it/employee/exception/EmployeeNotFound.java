package com.ideas2it.employee.exception;

/**
 * <h2>EmployeeNotFound</h2>
 * <p>
 * It is a user defiend Exception class extends RuntimeException.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 18-08-2022
 **/
public class EmployeeNotFound extends RuntimeException {
    
    /**
     * <p>
     * It is a parameter constructor that sent message through exception.
     * </p>
     *
     * @param {@link String} message.
     **/
    public EmployeeNotFound(String message) {
        super(message);
    }
}