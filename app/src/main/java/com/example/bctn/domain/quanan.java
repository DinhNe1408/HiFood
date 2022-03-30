package com.example.bctn.domain;

public class quanan {
    private int idQA;
    private String tenQA;
    private double poslat,poslong, khcachQA;


    public quanan(int idQA, String tenQA, double poslat, double poslong) {
        this.idQA = idQA;
        this.tenQA = tenQA;
        this.poslat = poslat;
        this.poslong = poslong;
    }

    public int getIdQA() {
        return idQA;
    }

    public void setIdQA(int idQA) {
        this.idQA = idQA;
    }

    public String getTenQA() {
        return tenQA;
    }

    public void setTenQA(String tenQA) {
        this.tenQA = tenQA;
    }

    public double getPoslat() {
        return poslat;
    }

    public void setPoslat(double poslat) {
        this.poslat = poslat;
    }

    public double getPoslong() {
        return poslong;
    }

    public void setPoslong(double poslong) {
        this.poslong = poslong;
    }
}
