/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import java.sql.Connection;
import java.util.regex.Pattern;

/**
 *
 * @author phung
 */
public class NameValidation {
    private Connection conn;
    
    public NameValidation (Connection conn) {
        this.conn = conn;
    }
    public Pattern pattern; 
    public static String NAME_PATTERN = "(([a-zA-Z][a-zA-Z ]*).{1,50})"; 
        
    
    
    public boolean validate(String name) { 
        pattern = Pattern.compile(NAME_PATTERN); 
        return pattern.matcher(name).matches(); 
    } 
}
