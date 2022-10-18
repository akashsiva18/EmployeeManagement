package com.ideas2it.employee.util;

/**
 * <h2>StringUtil</h2>
 * <p>
 * This Class contains only String related validation methods.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public class StringUtil {

   /**
     * <p>
     * It check the string in the excepted mail format.
     * </p>
     * <p>
     * Ex 1. email = akash71426.2@gmail.com, return true.
     * Ex 2. email = .akash7142@gmail.com, return false.
     * Ex 3. email = alask71425@gmail.co, return false.
     * </p>
     * @param {@link String} email
     * @return {@link boolean} If the string in correct format return true else false.
     **/
    public static Boolean isEmailValid(String email) {
        String patternForEmail = "^[a-z][a-z0-9.]{4,}@[a-z0-9.]{5,}(com|in|co.in)$";
        return email.matches(patternForEmail);
    }
 
   /**
     * <p>
     * It check the string in the excepted Mobile Number format.
     * </p>
     * <p>
     * Ex 1. mobileNumber = 9876543211, return true.
     * Ex 2. mobileNumber = 4876543211, return false.
     * Ex 3. mobileNumber = 9876543a12, return false.
     * </p>
     * @param {@link String} mobileNumber.
     * @return {@link boolean} If the string in correct format return true else false.
     **/
    public static boolean isValidMobileNumber(String mobileNumber) {
        String validationPattern = "[6-9][0-9]{9}";
        return mobileNumber.matches(validationPattern);
    }

   /**
     * <p>
     * It check the string in the excepted Name format.
     * </p>
     * <p>
     * Ex 1. name = ARUN.S, retur true.
     * Ex 2. name = .ARUN, retur false.
     * Ex 3. name = ARUN S, retur true.
     * Ex 4. name = ARUN S2, retur false.
     * </p>
     * @param {@link String} name
     * @return {@link boolean} If the string in correct format return true else false.
     **/
    public static boolean isValidName(String name) {
        String validationPattern = "[A-Za-z]{3}[A-Za-z.\s]*";
        return name.matches(validationPattern);
    }


   /**
     * <p>
     * It check the string that not Start from 0 and not include number.
     * </p>
     * @param {@link String} id
     * @return {@link boolean} If the string in correct format return true else false.
     **/
    public static boolean isValidId(String id) {
        String validIdPatten = "^[1-9][0-9]*";
        return id.matches(validIdPatten);
    }


   /**
     * <p>
     * It check the string that check the valid input of user menu.
     * </p>
     * @param {@link String} option
     * @return {@link boolean} If the string in correct format return true else false.
     **/
    public static boolean isvalidNumberInput(String string) {
        String validIdPatten = "[0-9]*";
        return string.matches(validIdPatten);
    } 
}