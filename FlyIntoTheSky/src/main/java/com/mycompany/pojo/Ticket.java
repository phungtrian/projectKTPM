/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;

/**
 *
 * @author phung
 */
public class Ticket extends Seat{
    private int id;
    private String dateOfIssue;
    private BigDecimal price;
    private int customerId;
    private int flightID;
    private int seatID;
    private String status;
    
    
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
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    /**
     * @return the flightID
     */
    public int getFlightID() {
        return flightID;
    }

    /**
     * @param flightID the flightID to set
     */
    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    /**
     * @return the seatID
     */
    public int getSeatID() {
        return seatID;
    }

    /**
     * @param seatID the seatID to set
     */
    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the dateOfIssue
     */
    public String getDateOfIssue() {
        return dateOfIssue;
    }

    /**
     * @param dateOfIssue the dateOfIssue to set
     */
    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    /**
     * @return the customerID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerId = customerID;
    }

}

