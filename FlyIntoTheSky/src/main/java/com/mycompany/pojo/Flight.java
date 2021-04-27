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
    private String time;
    private String day;
    private int planeId;
//    public Flight(int id, int origin, int destination, String boardingTime, String day) {
//        this.id = id;
//        this.origin = origin;
//        this.destination = destination;
//        this.boardingTime = boardingTime;
//        this.day = day;
//    }

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
