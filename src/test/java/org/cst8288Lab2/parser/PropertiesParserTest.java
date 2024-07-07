/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.cst8288Lab2.parser;

import java.util.Map;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class to test the methods of the PropertiesParser class.
 * @author Dan Blais
 * @version Java 17
 */
public class PropertiesParserTest {
    
    /**
     * Default constructor
     */
    public PropertiesParserTest() {
    }
    
    /**
     * Test of getInstance method, of class PropertiesParser. The test will pass
     * if both variables reference the same memory address. 
     */
    @org.junit.jupiter.api.Test
    public void testGetInstance() {
        PropertiesParser expResult = PropertiesParser.getInstance();
        PropertiesParser result = PropertiesParser.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of parseRow method, of class PropertiesParser.
     */
    @org.junit.jupiter.api.Test
    public void testParseRow() {
        String row = "user=Dan";
        PropertiesParser instance = PropertiesParser.getInstance();
        instance.parseRow(row);
        var expectedProp = new Properties();
        var actualProp = instance.getProperties();
        var expected = true;
        var actual = false;
        expectedProp.put("user", "Dan");
        if(expectedProp.get("user").equals(actualProp.get("user"))) {
            actual = true;
        }
        assertEquals(expected, actual);
    }
}
