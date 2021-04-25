/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

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
    private String boardingTime;
    private String day;
    
    public Flight(int id, String origin, String destination, String boardingTime, String day) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.boardingTime = boardingTime;
        this.day = day;
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
        return boardingTime;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.boardingTime = time;
    }

    
    /**
     * @return the date
     */
    public String getDate() {
        return day;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.day = date;
    }
    
}
