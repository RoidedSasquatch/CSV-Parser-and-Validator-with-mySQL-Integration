/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.studentcourse;

/**
 * This class represents a course and stores values that will be inserted into
 * the database.
 * @author Dan Blais
 * @version Java 17
 */
public class Course {
    private final String courseID;
    private final String courseName;

    /**
     * Constructor
     * @param courseID courseID from CSV file
     * @param courseName  courseName from CSV file
     */
    public Course(String courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    /**
     * Getter for courseID
     * @return The courseId to be entered into the database. 
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Getter for courseName
     * @return The courseName to be entered into the database.
     */
    public String getCourseName() {
        return courseName;
    }
}
