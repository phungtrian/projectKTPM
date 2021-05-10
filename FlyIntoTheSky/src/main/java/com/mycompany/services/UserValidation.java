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
public class UserValidation {
    private Connection conn;
   
    public UserValidation(Connection conn){
        this.conn = conn;
    }
   
    private Pattern pattern; 
    private static final String USERNAME_PATTERN = "^[a-z0-9._-]{3,15}$"; 
   
    public boolean validate(final String username) {
        pattern = Pattern.compile(USERNAME_PATTERN); 
        return pattern.matcher(username).matches(); 
    } 
}
