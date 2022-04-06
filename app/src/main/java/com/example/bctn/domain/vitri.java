package com.example.bctn.domain;

import java.io.Serializable;

public class vitri implements Serializable {
    private String vitri;
    private double vido, kinhdo;

    public vitri(String vitri, double vido, double kinhdo) {
        this.vitri = vitri;
        this.vido = vido;
        this.kinhdo = kinhdo;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }
}
