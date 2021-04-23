/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;
/**
 *
 * @author phung
 */
public class Plane {

    /**
     * @return the c
     */
    public Class getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Class c) {
        this.c = c;
    }
    private int id;
    private Class c;
      
    public Plane(int maMayBay, Class khoangMayBay) {
        this.id = maMayBay;
        this.c = khoangMayBay;
    }
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
