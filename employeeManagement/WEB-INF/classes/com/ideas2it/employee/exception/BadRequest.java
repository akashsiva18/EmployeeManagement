package com.ideas2it.employee.exception;

import java.util.List;
import java.util.ArrayList;

/**
 * <h2>BadRequest</h2>
 * <p>
 * It is a user defiend Exception class extends Runtime Exception.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 18-08-2022
 **/
public class BadRequest extends RuntimeException {

    public List<Integer> errors = new ArrayList<>();

    /**
     * <p>
     * It is a parameter constructor that sent message and list through the exception.
     * </p>
     *
     * @param {@link String} message.
     * @param {@link List<Integer>} errors.
     **/
    public BadRequest(String message, List<Integer> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * <p>
     * It is a parameter constructor that sent message through exception.
     * </p>
     *
     * @param {@link String} message.
     **/
    public BadRequest(String message) {
        super(message);
    }
}