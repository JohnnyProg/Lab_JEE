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
    private double nrat;
    
    public double getRata() throws Exception {
        double p = (oprocentowanie/100) / 12;
        
        if(kwota  < 0 || oprocentowanie < 0 || nrat < 0) {
            throw new Exception("values are smaller than 0");
        }
        double res = (kwota*p) / (1 - (1 / Math.pow((1 + p), nrat)));
        System.out.println(kwota + " " + oprocentowanie + " " + nrat);
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

    
    

    public double getNrat() {
        return nrat;
    }

    public void setNrat(double lrat) {
        this.nrat = lrat;
    }
    
    
}
