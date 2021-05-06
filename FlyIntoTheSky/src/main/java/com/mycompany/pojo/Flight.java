/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    
    public BigDecimal unitPrice(){
        if(this.origin == "HN"){
            switch(this.destination){
                    case "SG":
                        return BigDecimal.valueOf(1000000);
                    case "DL":
                        return BigDecimal.valueOf(1300000);
                    case "PQ":
                        return BigDecimal.valueOf(2000000);
                default:
            }
        }
        else if(this.origin == "SG"){
                switch(this.destination){
                    case "HN":
                        return BigDecimal.valueOf(900000);
                    case "DL":
                        return BigDecimal.valueOf(700000);
                    case "PQ":
                        return BigDecimal.valueOf(1000000);
                default:
            }
                }
        else if(this.origin == "DL"){
                switch(this.destination){
                    case "HN":
                        return BigDecimal.valueOf(1500000);
                    case "SG":
                        return BigDecimal.valueOf(800000);
                    case "PQ":
                        return BigDecimal.valueOf(1200000);
                default:
            }
                }
        else if(this.origin == "PQ"){
                switch(this.destination){
                    case "HN":
                        return BigDecimal.valueOf(2200000);
                    case "DL":
                        return BigDecimal.valueOf(1700000);
                    case "SG":
                        return BigDecimal.valueOf(1000000);
                default:
            }
                }
        return BigDecimal.ZERO;
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
