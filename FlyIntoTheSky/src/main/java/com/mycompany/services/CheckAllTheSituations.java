/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author phung
 */
public class CheckAllTheSituations {
    private Connection conn;
    
    public CheckAllTheSituations (Connection conn) {
        this.conn = conn;
    }
    
//    public class NameValidator { //tên chỉ đúng khi nằm trong vùng có ký tự thường và hoa và có khoảng trắng.
//        private Pattern pattern; 
//        private static final String NAME_PATTERN = "((^[a-zA-Z\\s]*$).{2,50})"; 
//        
//        public NameValidator() { 
//            pattern = Pattern.compile(NAME_PATTERN); 
//        } 
//    
//        public boolean validate(String name) { 
//            return pattern.matcher(name).matches(); 
//        } 
//    } 
//    
//    
//    public class PhoneValidator { //số điện thoại đúng là khi có đúng 10 chữ số
//        private Pattern pattern; 
//        private static final String PHONE_PATTERN = "((^\\d{10}$))"; 
//        
//        public PhoneValidator() { 
//            pattern = Pattern.compile(PHONE_PATTERN); 
//        } 
//    
//        public boolean validate(String phone) { 
//            return pattern.matcher(phone).matches(); 
//        } 
//    }
    
}
