/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.cst8288Lab2.reporting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class that tests the methods of the Reporter Class
 * @author Dan Blais
 * @version Java 17
 */
public class ReporterTest {
    
    /**
     * Default constructor
     */
    public ReporterTest() {
    }
    
    /**
     * Test of getInstance method, of class Reporter.
     */
    @Test
    public void testGetInstance() {
        Reporter expResult = Reporter.getInstance();
        Reporter result = Reporter.getInstance();
        assertEquals(expResult, result);
    }
}
