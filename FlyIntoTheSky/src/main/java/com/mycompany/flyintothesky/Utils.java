/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import javafx.scene.control.Alert;

/**
 *
 * @author phung
 */
public class Utils {
    public static Alert getBox(String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(content);
        
        return alert;
    }
}
