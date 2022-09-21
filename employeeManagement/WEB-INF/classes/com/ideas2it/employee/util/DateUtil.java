package com.ideas2it.employee.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.ideas2it.employee.common.Constant;
/**
 * <h2>DateUtil</h2>
 * <p>
 * This Class contains only Date related validation and methods.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public class DateUtil {
    
   /**
     * <p>
     * It compute the years between from two date.
     * </p>
     * 
     * <p>
     * Ex: start date = 18/01/2000, end date = 22/02/2009, return 9;
     * </p>
     
     * @param {@link LocalDate} startDate - From date.
     * @param {@link LocalDate} endDate - To date.
     * @return {@link Integer} return no of years diffrence between two dates
     **/
    public static Integer computeYears(LocalDate startDate, LocalDate endDate) {
        Period betweenPeriod = Period.between(startDate, endDate);
        int yearsBetween = betweenPeriod.getYears();
        return yearsBetween;
    }

   /**
     * <p>
     * It check the string in the excepted date format.
     * </p>
     * 
     * @param {@link String} date - date for validate
     * @return {@link boolean} If the string in correct format return true else false.
     **/
    public static Boolean isValidDateFormat(String date) {
        boolean isValidDate = true;
        DateTimeFormatter formatForDate = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT);
        try {
            LocalDate parsedDate = LocalDate.parse(date, formatForDate);
        } catch (DateTimeParseException e) {
            isValidDate = false;
        }
        return isValidDate;
    }

   /**
     * <p>
     * It check date is future date or not.
     * </p>
     * 
     * <p>
     * Ex: date = 16/02/2023, return true.
     * Ex: date = 16/02/2022, return false.
     * </p>
     *
     * @param {@link LocalDate} date - date for check.
     * @return {@link boolean} If the date is future date it return true else false. 
     **/
    public static Boolean isFutureDate(LocalDate date) {
	return date.compareTo(LocalDate.now()) > 0;
    }

   /**
     * <p>
     * It check the age eligibility.
     * </p>
     * 
     * <p>
     * Ex 1: date = 16/02/2000, requiredAge = 18, return true.
     * Ex 2: date = 16/02/2003, requiredAge = 22, return false.
     * </p>
     *
     * @param {@link LocalDate} date - date of birth.
     * @param {@link int} requiredAge - eligible age.
     * @return {@link boolean}
     *         - if the age is higher than the given
     *           age return true else false.
     **/
    public static Boolean isAgeEligible(LocalDate date, int requiredAge) {
         int age = computeYears(date,LocalDate.now());
         return (age > requiredAge);
    }

   /**
     * <p>
     * It parse string to LocalDate.
     * </p>
     *
     * @param {@link String} date.
     * @return {@link LocalDate} 
     *          - if the string date in the required format it parsed and return 
     *            else return the current date.
     **/
    public static LocalDate parseStringToDate(String date) {
        LocalDate parsedDate = LocalDate.now();
        DateTimeFormatter formatForDate = DateTimeFormatter.ofPattern("yyyy-MM-d");
        try {
            parsedDate = LocalDate.parse(date, formatForDate);
        } catch (DateTimeParseException error) {
            parsedDate = LocalDate.now();   
        }
        return parsedDate;
    }

   /**
     * <p>
     * It check the date in correct format and age eligible.
     * </p>
     *
     * <p>
     * Ex: dateInString = 22/01/2000, eligibleAge = 18, return true.
     * Ex: dateInString = 22/01/2008, eligibleAge = 18, return false.
     * </p>
     *
     * @param {@link String} dateInString - string for validation.
     * @return {@link Boolean} 
     *         - if date format is matched and eligibleAge is greater 
     *           than the required age return true else false.
     **/
    public static Boolean isValidDateOfBirth(String dateInString, int eligibleAge) {
        boolean isValidDate = false;
        if (isValidDateFormat(dateInString)) {
            LocalDate date = parseStringToDate(dateInString);
            if (isAgeEligible(date, eligibleAge)) {
                isValidDate = true;
            }
        }
        return isValidDate;
    }
}