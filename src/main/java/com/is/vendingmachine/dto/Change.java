/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author ibby4
 */
public class Change {    
    private final BigDecimal pennies = new BigDecimal("0.01");
    private final BigDecimal nickels = new BigDecimal("0.05");
    private final BigDecimal dimes   = new BigDecimal("0.10");
    private final BigDecimal quarters = new BigDecimal("0.25");
    private BigDecimal changeDue = new BigDecimal("0.00");

    private int amoutQrter;
    private int amountNick;
    private int amoutdime;
    private int amountpen;

    public int getAmoutQrter() {
        return amoutQrter;
    }

    public void setAmoutQrter(int amoutQrter) {
        this.amoutQrter = amoutQrter;
    }

    public int getAmountNick() {
        return amountNick;
    }

    public void setAmountNick(int amountNick) {
        this.amountNick = amountNick;
    }

    public int getAmoutdime() {
        return amoutdime;
    }

    public void setAmoutdime(int amoutdime) {
        this.amoutdime = amoutdime;
    }

    public int getAmountpen() {
        return amountpen;
    }

    public void setAmountpen(int amountpen) {
        this.amountpen = amountpen;
    }
    
    
    public BigDecimal getChangeDue() {
        return changeDue;
    }

    public void setChangeDue(BigDecimal changeDue) {
        this.changeDue = changeDue;
    }
    

    public BigDecimal getPennies() {
        return pennies;
    }

    public BigDecimal getNickels() {
        return nickels;
    }

    public BigDecimal getDimes() {
        return dimes;
    }

    public BigDecimal getQuarters() {
        return quarters;
    }
    
    

       
}
    
