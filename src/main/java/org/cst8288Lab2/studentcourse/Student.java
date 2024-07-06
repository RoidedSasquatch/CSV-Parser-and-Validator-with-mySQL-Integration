/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.studentcourse;

/**
 * This class represents a student and stores values that will be inserted into
 * the database.
 * @author Dan Blais
 * @version Java 17
 */
public class Student {
    private int studentID;
    private String firstName;
    private String lastName;
    
    /**
     * Default constructor
     */
    public Student() {
        this(0, "", "");
    }

    /**
     * Overloaded constructor
     * @param studentID studentId from CSV file
     * @param firstName firstName from CSV file
     * @param lastName  lastName from CSV file
     */
    public Student(int studentID, String firstName, String lastName) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getter for studentId
     * @return The studentId to be entered into the database
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Setter for studentId
     * @param studentID The value to assign to studentId
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    
    
     /**
     * Getter for firstName
     * @return The firstName to be entered into the database
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName
     * @param firstName The value to assign to firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Getter for lastName
     * @return The lastName to be entered into the database
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName
     * @param lastName The value to assign to lastNAme
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
}
