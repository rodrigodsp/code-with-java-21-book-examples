package com.codewithjava21.weatherapp;

public class CloudLayer {
    
    private Measurement base;
    private String amount;


    public Measurement getBase() {
        return this.base;
    }

    public void setBase(Measurement base) {
        this.base = base;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
