package com.example.bctn.domain;

import java.io.Serializable;

public class ctdh implements Serializable {
    private byte[] HinhMA;
    private int IDMA, SLMA;
    private String GhiChu,TenMA;
    private double GiaMA;

    public ctdh(int IDMA,byte[] HinhMA, String TenMA,double GiaMA, int SLMA,String GhiChu) {
        this.HinhMA = HinhMA;
        this.GhiChu = GhiChu;
        this.IDMA = IDMA;
        this.SLMA = SLMA;
        this.TenMA = TenMA;
        this.GiaMA = GiaMA;
    }

    public ctdh(int IDMA, String TenMA,double GiaMA, int SLMA,String GhiChu) {
        this.GhiChu = GhiChu;
        this.IDMA = IDMA;
        this.SLMA = SLMA;
        this.TenMA = TenMA;
        this.GiaMA = GiaMA;
    }

    public ctdh(int IDMA, int SLMA, String ghiChu) {
        this.IDMA = IDMA;
        this.SLMA = SLMA;
        this.GhiChu = ghiChu;
    }

    public String getTenMA() {
        return TenMA;
    }

    public void setTenMA(String tenMA) {
        TenMA = tenMA;
    }

    public double getGiaMA() {
        return GiaMA;
    }

    public void setGiaMA(double giaMA) {
        GiaMA = giaMA;
    }

    public int getIDMA() {
        return IDMA;
    }

    public void setIDMA(int IDMA) {
        this.IDMA = IDMA;
    }

    public int getSLMA() {
        return SLMA;
    }

    public void setSLMA(int SLMA) {
        this.SLMA = SLMA;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
