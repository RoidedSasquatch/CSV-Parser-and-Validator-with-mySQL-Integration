/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.reporting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Singleton class contains methods and variables which are useful in generating
 * the success and error reports for validation and database row entry. 
 * @author Dan Blais
 * @version Java 17
 */
public class Reporter {
    private static Reporter dbLogger = null;
    private final Map<String, Integer> successMap; //Map to store the number of successful row entries
    private final Map<Integer, List<String>> rowErrors; //Map to store list of columns that failed to validate, by row number
    private List<String> failedCols; //List which stores columns that fail to validate
    
    /**
     * Constructor which instantiates maps and list and sets up the success map
     * for counting successful row entries.
     */
    private Reporter() {
        failedCols = new LinkedList<>();
        rowErrors = new HashMap<>();
        successMap = new HashMap<>();
        successMap.put("student", 0);
        successMap.put("course", 0);
        successMap.put("studentcourse", 0);
    }
    
    /**
     * Ensures that only a single instance of this class can exist at one time.
     * Returns an instance of this class.
     * @return An instance of this class.
     */
    public static Reporter getInstance() {
        if(dbLogger == null) {
            dbLogger = new Reporter();
        }
        return dbLogger;
    }
    
    /**
     * Obtains a value associated with a given key in the success map, increments
     * it by 1, and puts it back into the map; overwriting the original entry.
     * @param key The key associated with the target value.
     */
    public void incrementSuccessMap(String key) {
        int val = successMap.get(key);
        val += 1;
        successMap.put(key, val);
    }
    
    /**
     * Add the row number and corresponding list of errors to the rowErrors
     * HashMap. Then assign failedCols as a reference to a new ArrayList for 
     * processing of errors in the next row. 
     * @param row The number of a row that has a corresponding error list 
     */
    public void addErrorsToMap(int row) {
        if(!failedCols.isEmpty()) {
            rowErrors.put(row, failedCols);
        }
        failedCols = new LinkedList<>();
    }
    
    /**
     * Adds a string which, represents the name of a column that failed to validate,
     * to the list of errors. 
     * @param error A string which represents a column that failed to validate.
     */
    public void addErrorToList(String error) {
        failedCols.add(error);
    }
    
    /**
     * Generates both the success and failure reports for the application.
     */
    public void generateReports() {
        //Generate success report using the successMap which contains counts of successful
        //entries
        try (PrintStream ps = new PrintStream(new FileOutputStream(new File("import-report.md")))) {
            //Report header
            ps.println("-------------\nImport Report\n-------------\n");
            ps.println(LocalDateTime.now() + "\n");
            //Output counter values
            ps.println(successMap.get("student") + " record(s) added to table: student");
            ps.println(successMap.get("course") + " record(s) added to table: course");
            ps.println(successMap.get("studentcourse") + " record(s) added to table: studentcourse");
            ps.flush();
        } catch(FileNotFoundException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
        }
        
        //Generate error report
        try (PrintStream ps = new PrintStream(new FileOutputStream(new File("error-report.md")))) {
            //Report header
            ps.println("-------------\nErrors Report\n-------------\n");
            ps.println(LocalDateTime.now() + "\n");
            ps.printf("%s%d%s", "**Failed To load ", rowErrors.size(), " rows from provided CSV file:** \n\n");
            //Iterate through Map entries
            for(Map.Entry<Integer, List<String>> e : rowErrors.entrySet()) {
                String errors = "";
                //Iterate through list of errors associated with a row that contains errors
                for(int i = 0; i < e.getValue().size(); i++) {
                    //Append each value in list to a single string for output. String will be composed of only column names that fail to validate.
                    if(i != e.getValue().size() - 1) {
                        errors += (e.getValue().get(i) + ", ");
                    } else {
                        errors += e.getValue().get(i);
                    }
                }
                ps.printf("%s%d%s%s\n", "Error adding row: ", e.getKey(), ", Failed to add: ", errors);
                //Check error string for character sequences matching each column name
                //If a match is found, output details about the reason for the error
                if(errors.contains("studentId")) {
                    ps.println("\n*StudentId must be exactly 9 digits.*");
                }
                if(errors.contains("courseId")) {
                    ps.println("*CourseId must be exactly 7 characters consisting of 3 letters, followed by 4 digits.*");
                }
                if(errors.contains("term")) {
                    ps.println("*Term must match 'WINTER', 'SUMMER', or 'FALL'.*");
                }
                if(errors.contains("year")) {
                    ps.println("*Year must be exactly 4 digits, from 1967 - present year inclusive.*\n");
                }
            }
            ps.flush();
        } catch(FileNotFoundException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
        }
    }    
}
