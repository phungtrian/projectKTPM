/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author phung
 */
public class Ticket {
    private int id;
    private BigDecimal price;
    private String time;
    private String date;
    private Agent nv;
    private Customer kh;
    private Flight cb;
    private Seat s;
    
    
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the kh
     */
    public Customer getKh() {
        return kh;
    }

    /**
     * @param kh the kh to set
     */
    public void setKh(Customer kh) {
        this.kh = kh;
    }

    /**
     * @return the cb
     */
    public Flight getCb() {
        return cb;
    }

    /**
     * @param cb the cb to set
     */
    public void setCb(Flight cb) {
        this.cb = cb;
    }

    /**
     * @return the s
     */
    public Seat getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(Seat s) {
        this.s = s;
    }

    /**
     * @return the nv
     */
    public Agent getNv() {
        return nv;
    }

    /**
     * @param nv the nv to set
     */
    public void setNv(Agent nv) {
        this.nv = nv;
    }
}

