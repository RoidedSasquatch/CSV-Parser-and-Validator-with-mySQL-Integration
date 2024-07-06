/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.dboperations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cst8288Lab2.connection.DBConnection;
import org.cst8288Lab2.reporting.Reporter;
import org.cst8288Lab2.studentcourse.Course;
import org.cst8288Lab2.studentcourse.Student;
import org.cst8288Lab2.studentcourse.StudentCourse;

/**
 * This class contains methods which will perform insert operations on a
 * database. Contains methods which will insert a Student, Course, or StudentCourse.
 *
 * @author Dan Blais
 * @version Java 17
 */
public class DBOperations {

    /**
     * Default constructor
     */
    public DBOperations() {
    }

    /**
     * This method inserts a Student into the database
     *
     * @param student The student to insert
     */
    public static void createStudent(Student student) {
        String insertSQL = "INSERT INTO student(studentId, firstName, lastName) Values(?, ?, ?)";
        try (PreparedStatement insertStatement = DBConnection.connect().prepareStatement(insertSQL)) {
            insertStatement.setInt(1, student.getStudentID());
            insertStatement.setString(2, student.getFirstName());
            insertStatement.setString(3, student.getLastName());
            insertStatement.execute();
            Reporter.getInstance().incrementSuccessMap("student");
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
        }
    }
    
    /**
     * This method inserts a Course into the database
     *
     * @param course The course to insert
     */
    public static void createCourse(Course course) {
        String insertSQL = "INSERT INTO course(courseId, courseName) Values(?, ?)";
        try (PreparedStatement insertStatement = DBConnection.connect().prepareStatement(insertSQL)) {
            insertStatement.setString(1, course.getCourseID());
            insertStatement.setString(2, course.getCourseName());
            insertStatement.execute();
             Reporter.getInstance().incrementSuccessMap("course");
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
        }
    }
    
    /**
     * This method inserts a StudentCourse into the database based on values from
     * Student and Course parameters.
     *
     * @param studentCourse The studentCourse to insert
     * @param student The student related to the studentCourse
     * @param course The course related to the studentCourse
     */
    public static void createStudentCourse(StudentCourse studentCourse, Student student, Course course) {
        String insertSQL = "INSERT INTO studentcourse(studentId, courseId, term, year) Values(?, ?, ?, ?)";
        try (PreparedStatement insertStatement = DBConnection.connect().prepareStatement(insertSQL)) {
            insertStatement.setInt(1, student.getStudentID());
            insertStatement.setString(2, course.getCourseID());
            insertStatement.setInt(3, studentCourse.getTerm());
            insertStatement.setInt(4, studentCourse.getYear());
            insertStatement.execute();
             Reporter.getInstance().incrementSuccessMap("studentcourse");
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
        }
    }
}