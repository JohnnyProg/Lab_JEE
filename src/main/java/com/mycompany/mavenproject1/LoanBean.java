/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;

/**
 *
 * @author student
 */
public class LoanBean implements Serializable {
    private double kwota;
    private double oprocentowanie;
    private double lrat;
    
    public double getRata() throws Exception {
        double p = oprocentowanie / 12;
        
        if(kwota  < 0 || oprocentowanie < 0 || lrat < 0) {
            throw new Exception("values are smaller than 0");
        }
        double res = (kwota*(p/12)) / (1 - (1 / Math.pow((1 + p), lrat)));
        System.out.println(kwota + " " + oprocentowanie + " " + lrat);
        return res;
    }
    
    
    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public double getOprocentowanie() {
        return oprocentowanie;
    }

    public void setOprocentowanie(double oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    
    

    public double getLrat() {
        return lrat;
    }

    public void setLrat(double lrat) {
        this.lrat = lrat;
    }
    
    
}
