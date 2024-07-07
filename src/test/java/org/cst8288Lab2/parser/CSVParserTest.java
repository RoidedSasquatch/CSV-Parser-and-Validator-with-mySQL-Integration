/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.cst8288Lab2.parser;

import java.util.LinkedHashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class that tests the methods of CSVParser
 * @author Dan Blais
 * @version Java 17
 */
public class CSVParserTest {
    
    /**
     * Default Constructor
     */
    public CSVParserTest() {
    }

    /**
     * Test of getInstance method, of class CSVParser. The test will pass
     * if both variables reference the same memory address. 
     */
    @Test
    public void testGetInstance() {
        CSVParser expResult = CSVParser.getInstance();
        CSVParser result = CSVParser.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of setKeys method, of class CSVParser. Tests that the contents of
     * the actual array match the contents of the expected array
     */
    @Test
    public void testSetKeys() {
        System.out.println("setKeys");
        String keyRow = "firstName, lastName";
        CSVParser instance = CSVParser.getInstance();
        instance.setKeys(keyRow);
        var expectedKeys = new String[]{"firstName", "lastName"};
        var actualKeys = instance.getKeys();
        var expected = true;
        var actual = false;
        for(int i = 0; i < expectedKeys.length; i++) {
            if(expectedKeys[i].equals(actualKeys[i])) {
                actual = true;
            } else {
                actual = false;
                break;
            }
        }
        assertEquals(expected, actual);
    }
}
