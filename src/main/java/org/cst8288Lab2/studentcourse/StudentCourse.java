/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.studentcourse;

/**
 * This class represents a studentCourse and stores values that will be inserted into
 * the database.
 * @author Dan Blais
 * @version Java 17
 */
public class StudentCourse {
    private int term;
    private final int year;

    /**
     * Constructor. Takes a term String and a year String and converts them into
     * integers and stores them in the class variables for inserting into the database.
     * @param term term from CSV file
     * @param year year from CSV file
     */
    public StudentCourse(String term, String year) {
        switch(term) {
            case "WINTER" -> this.term = 2;
            case "SUMMER" -> this.term = 3;
            case "FALL" -> this.term = 1;
        }
        this.year = Integer.parseInt(year);
    }

    /**
     * Getter for term
     * @return The term to be entered into the database.
     */
    public int getTerm() {
        return term;
    }

    /**
     * Getter for year
     * @return The year to be entered into the database.
     */
    public int getYear() {
        return year;
    }
}
