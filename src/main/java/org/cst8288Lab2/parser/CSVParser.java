/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.parser;

import java.util.LinkedHashMap;

/**
 * This Singleton class contains methods that will parse a row of a CSV file and split the row.
 * The split row is then mapped to the corresponding column names and returned in 
 * a Map. 
 * @author Dan Blais
 * @version Java 17
 */
public class CSVParser {
    private static CSVParser csvParser = null; //Reference to this class
    private String[] keys; //Store the column names to be used as Map keys

    /**
     * Default constructor
     */
    private CSVParser() {

    }

    /**
     * Ensures that only one instance of this class can be created, returns the
     * instance.
     * @return An instance of this class
     */
    public static CSVParser getInstance() {
        if (csvParser == null) {
            csvParser = new CSVParser();
        }
        return csvParser;
    }

    /**
     * Split a string of column names and store the result in the keys array.
     * @param keyRow The row that contains the column names
     */
    public void setKeys(String keyRow) {
        if (keyRow != null) {
            keys = keyRow.split(", ");
        }
    }

    /**
     * Splits a row String and maps each value to the corresponding column name.
     * @param row The row that contains the values.
     * @return An ordered Map containing mappings of column names to a given
     * row's values.
     */
    public LinkedHashMap<String, String> parseRow(String row) {
        String[] parsedRow = row.split(", ");
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();
        if (parsedRow != null) {
            for (int i = 0; i < parsedRow.length; i++) {
                if (keys[i] != null && parsedRow[i] != null) {
                    valueMap.put(keys[i], parsedRow[i]);
                }
            }
        }
        return valueMap;
    }

    /**
     * Getter for the keys variable
     * @return An array of strings to be used as Map keys
     */
    public String[] getKeys() {
        return keys;
    }
}
