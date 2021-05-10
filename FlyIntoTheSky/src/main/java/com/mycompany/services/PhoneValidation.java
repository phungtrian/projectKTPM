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
public class PhoneValidation {
    private Connection conn;
    
    public PhoneValidation (Connection conn) {
        this.conn = conn;
    }
    
    public Pattern pattern; 
    public static String PHONE_PATTERN = "((^\\d{10}$))"; 

    public boolean validate(String phone) { 
        pattern = Pattern.compile(PHONE_PATTERN); 
        return pattern.matcher(phone).matches(); 
    } 
}
