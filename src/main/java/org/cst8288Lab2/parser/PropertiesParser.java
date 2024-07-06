/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.parser;

import java.util.Properties;

/**
 * This Singleton class parses database connection strings from a properties file.
 * It then maps each value to the correct kind of connection String
 * @author Dan Blais
 * @version Java 17
 */
public class PropertiesParser {
    private static PropertiesParser propertiesParser = null;
    private final Properties properties;
    
    /**
     * Constructor which instantiates properties variable
     */
    private PropertiesParser() {
        properties = new Properties();
    }
    
    /**
     * Ensure that only one instance of this class can exist at one time. Returns
     * the instance.
     * @return An instance of this class.
     */
    public static PropertiesParser getInstance() {
        if(propertiesParser == null) {
            propertiesParser = new PropertiesParser();
        }
        return propertiesParser;
    }

    /**
     * Parses a row of the properties file by splitting the string and storing
     * the two split values as a key-value pair. 
     * @param row The row to parse.
     */
    public void parseRow(String row) {
        String[] parsedRow = row.split("=");
        if (parsedRow != null) {
            properties.put(parsedRow[0], parsedRow[1]);
        }
    }

    /**
     * Getter for properties variable.
     * @return The properties variable.
     */
    public Properties getProperties() {
        return properties;
    }    
}
