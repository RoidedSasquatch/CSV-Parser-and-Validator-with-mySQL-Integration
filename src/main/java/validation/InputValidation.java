/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import java.util.LinkedHashMap;
import org.cst8288Lab2.reporting.Reporter;

/**
 * This singleton class provides methods which are useful for validating rows parsed
 * from a CSV file.
 * @author Dan Blais
 * @version Java 17
 */
public class InputValidation {
    private static InputValidation inputValidation = null;
    private boolean rowValid = true;
    
    /**
     * Default constructor
     */
    private InputValidation() {
        
    }
    
    /**
     * Ensures that only a single instance of this class can exist at one time.
     * Returns an instance of this class.
     * @return An instance of this class.
     */
    public static InputValidation getInstance() {
        if(inputValidation == null) {
            inputValidation = new InputValidation();
        }
        return inputValidation;
    }
    
    /**
     * This method calls each of the other validation methods of this class. For each 
     * method, the function will add the corresponding column name to the Reporter
     * classes error list and change the rowValid variable to false if the method fails to 
     * validate the passed string. Returns true if all columns validate successfully, returns false
     * if a single column fails to validate.
     * @param values An ordered map representing the parsed data of a row from a CSV file.
     * @return A Boolean representing whether all of the column data validated successfully.
     */
    public boolean validate(LinkedHashMap<String, String> values) {
        if(!validateStudentID(values.get("studentId"))) {
            Reporter.getInstance().addErrorToList("studentId"); 
            rowValid = false;
        }
        if(!validateCourseID(values.get("courseId"))) {
            Reporter.getInstance().addErrorToList("courseId");
            rowValid = false;
        } 
        if(!validateTerm(values.get("term"))) {
            Reporter.getInstance().addErrorToList("term");
            rowValid = false;
        } 
        if(!validateYear(values.get("year"))) {
            Reporter.getInstance().addErrorToList("year");
            rowValid = false;    
        }
        if(rowValid) {
            return true;
        }
        rowValid = true;
        return false;
    }
    
    /**
     * This method validates the studentId column from a CSV file. Ensures
     * that the length of the studentId is 9 and that it does not contain letters.
     * @param studentID The studentId to validate.
     * @return A Boolean representing the validation of the studentId.
     */
    private boolean validateStudentID(String studentID) {
        return !(studentID.length() != 9 || studentID.matches(".*[a-zA-Z].*"));
    }
    
    /**
     * This method validates the courseId column from a CSV file. Ensures that the courseId
     * is 7 characters long, consisting of 3 letters followed by 4 numbers.
     * @param courseID The courseId to validate.
     * @return A Boolean representing the validation of the courseId.
     */
    private boolean validateCourseID(String courseID) {
        return courseID.matches("^[A-Za-z]{3}[0-9]{4}$");
    }
    
    /**
     * This method validates the term column from a CSV file. Ensures that the term
     * value is either 'WINTER', 'SUMMER', or 'FALL'.
     * @param term The term to validate.
     * @return A Boolean representing the validation of the term. 
     */
    private boolean validateTerm(String term) {
        return term.equals("WINTER") || term.equals("SUMMER") || term.equals("FALL");
    }
    
    /**
     * This method validates the year column from a CSV file. Ensures that the year
     * consists of 4 digits, and falls within the range of 1967 - 2024 inclusive.
     * @param year The year to validate.
     * @return A Boolean representing the validation of the year.
     */
    private boolean validateYear(String year) {
        return !(year.length() != 4 || (Integer.parseInt(year) < 1967 || Integer.parseInt(year) > 2024));
    } 
    
}
