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
public class PassWordValidation {
    private Connection conn;
   
    public PassWordValidation(Connection conn){
        this.conn = conn;
    }
    
    public class PasswordValidator { 
        private Pattern pattern; 
        private static final String PASSWORD_PATTERN = "((?=.*d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!.#$@_+,?-]).{8,50})"; 

        public boolean validate(final String password) { 
            pattern = Pattern.compile(PASSWORD_PATTERN);
            return pattern.matcher(password).matches(); 
        } 
    }
}
