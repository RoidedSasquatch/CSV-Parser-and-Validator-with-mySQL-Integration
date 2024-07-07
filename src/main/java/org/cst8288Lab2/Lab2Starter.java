package org.cst8288Lab2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cst8288Lab2.connection.DBConnection;
import org.cst8288Lab2.dboperations.DBOperations;
import org.cst8288Lab2.parser.CSVParser;
import org.cst8288Lab2.parser.PropertiesParser;
import org.cst8288Lab2.reporting.Reporter;
import org.cst8288Lab2.studentcourse.Course;
import org.cst8288Lab2.studentcourse.Student;
import org.cst8288Lab2.studentcourse.StudentCourse;
import validation.InputValidation;

/**
 * This class contains the main method which drives the program. The program will
 * read a CSV file, validate it, add the values to a database, and then generate reports
 * based on what was added. JUnit tests in Test Packages.
 * 
 * @author Dan Blais
 * @version Java 17
 */
public class Lab2Starter {

    /**
     * Default constructor
     */
    public Lab2Starter() {
        
    }
    
    /**
     * Parses the file: bulk-import.csv Validates each item in each row and
     * updates the database accordingly. Then generates error and success reports
     * in markdown format.
     *
     * @param args - Command line args, not used.
     */
    public static void main(String[] args) {
        Properties properties; //For storing properties from parsed file
        DBConnection con; //Reference to database connection
        PropertiesParser propertiesParser = PropertiesParser.getInstance(); //Reference to singleton parser
        CSVParser csvParser = CSVParser.getInstance(); //Reference to singleton parser
        InputValidation inputValidation = InputValidation.getInstance(); //Reference to singleton InputValidation
        LinkedHashMap<String, String> parsedString; //Reference to hold map output by parser, maps column names to their parsed and split values

       //Parse database properties file and connect to DB
        try (InputStream in = new FileInputStream("data/database.properties")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String out;
                int rowCounter = 0;
                
                while ((out = br.readLine()) != null) {
                    if(rowCounter != 0) {
                        propertiesParser.parseRow(out);
                    }
                    rowCounter ++;
                }
                properties = propertiesParser.getProperties();
                con = DBConnection.getInstance(properties);
                con.connect();
            }
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
        }

        //Parse, Validate, and Insert CSV. Generate reports.
        try (InputStream in = new FileInputStream("data/bulk-import.csv")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String out; //Holds row read by BufferedReader
                int rowCounter = 0; //The row being read
                while ((out = br.readLine()) != null) {
                    if (rowCounter == 0) {
                        csvParser.setKeys(out); //Get the column names from first line of file
                    } else {
                        parsedString = csvParser.parseRow(out);
                        if (inputValidation.validate(parsedString)) { //Validate and then create new objects to hold values. Insert into DB.
                            Student student = new Student(Integer.parseInt(parsedString.get("studentId")), parsedString.get("firstName"), parsedString.get("lastName"));
                            Course course = new Course(parsedString.get("courseId"), parsedString.get("courseName"));
                            StudentCourse studentCourse = new StudentCourse(parsedString.get("term"), parsedString.get("year"));
                            DBOperations.createStudent(student);
                            DBOperations.createCourse(course);
                            DBOperations.createStudentCourse(studentCourse, student, course);
                        }
                    }
                    Reporter.getInstance().addErrorsToMap(rowCounter); //Add row errors to map in Reporter class
                    rowCounter++;
                }
                Reporter.getInstance().generateReports(); //Generate import and error Sreports
            }
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
        }
    }
}
