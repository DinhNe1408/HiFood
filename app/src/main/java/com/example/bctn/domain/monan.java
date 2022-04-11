package com.example.bctn.domain;

public class monan {
    private int idMA,hMA, loaiMA;
    private String maMA, tenMA;
    private double giaMA;
    private byte[] HinhMA;

    public monan(int idMA, String maMA, double giaMA, String tenMA) {
        this.idMA = idMA;
        this.giaMA = giaMA;
        this.maMA = maMA;
        this.tenMA = tenMA;
    }

    public monan(int idMA,String tenMA, double giaMA) {
        this.idMA = idMA;
        this.giaMA = giaMA;
        this.tenMA = tenMA;
    }

    public monan(int idMA, double giaMA, int hMA, int loaiMA, String maMA, String tenMA) {
        this.idMA = idMA;
        this.giaMA = giaMA;
        this.hMA = hMA;
        this.loaiMA = loaiMA;
        this.maMA = maMA;
        this.tenMA = tenMA;
    }

    public int getIdMA() {
        return idMA;
    }

    public void setIdMA(int idMA) {
        this.idMA = idMA;
    }

    public double getGiaMA() {
        return giaMA;
    }

    public void setGiaMA(double giaMA) {
        this.giaMA = giaMA;
    }

    public int getHMA() {
        return hMA;
    }

    public byte[] getHinhMA() {
        return HinhMA;
    }

    public void setHinhMA(byte[] hinhMA) {
        HinhMA = hinhMA;
    }

    public void setHMA(int hMA) {
        this.hMA = hMA;
    }

    public int getLoaiMA() {
        return loaiMA;
    }

    public void setLoaiMA(int loaiMA) {
        this.loaiMA = loaiMA;
    }

    public String getMaMA() {
        return maMA;
    }

    public void setMaMA(String maMA) {
        this.maMA = maMA;
    }

    public String getTenMA() {
        return tenMA;
    }

    public void setTenMA(String tenMA) {
        this.tenMA = tenMA;
    }
}
