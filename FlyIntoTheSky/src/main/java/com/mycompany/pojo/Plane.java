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

    
    private int id;
    private String name;
    private int seatTotal;
      


    @Override
    public String toString() {
        return String.valueOf(this.id); //To change body of generated methods, choose Tools | Templates.
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the seatTotal
     */
    public int getSeatTotal() {
        return seatTotal;
    }

    /**
     * @param seatTotal the seatTotal to set
     */
    public void setSeatTotal(int seatTotal) {
        this.seatTotal = seatTotal;
    }

}
