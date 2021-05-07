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
public class Flight {

    private int id;
    private String origin;
    private String destination;
    private String time;
    private String day;
    private int planeId;
    
    
    public double unitPrice(){
        double price = 0;
        switch (this.origin) {
            case "HN":
                switch(this.destination){
                    case "SG":
                        price = 1000000;
                        break;
                    case "DL":
                        price = 1300000;
                        break;
                    case "PQ":
                        price = 2000000;
                        break;
                    default:
                }   break;
            case "SG":
                switch(this.destination){
                    case "HN":
                        price = 900000;
                        break;
                    case "DL":
                        price = 700000;
                        break;
                    case "PQ":
                        price = 1000000;
                        break;
                    default:
                }   break;
            case "DL":
                switch(this.destination){
                    case "HN":
                        price = 1500000;
                        break;
                    case "SG":
                        price = 800000;
                        break;
                    case "PQ":
                        price = 1200000;
                        break;
                    default:
                }   break;
            case "PQ":
                switch(this.destination){
                    case "HN":
                        price = 2200000;
                        break;
                    case "DL":
                        price = 1700000;
                        break;
                    case "SG":
                        price = 1000000;
                        break;
                    default:
                }   break;
            default:
                break;
        }
        return price;
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
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return the planeId
     */
    public int getPlaneId() {
        return planeId;
    }

    /**
     * @param planeId the planeId to set
     */
    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

 
    
}
