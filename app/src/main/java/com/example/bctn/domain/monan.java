package com.example.bctn.domain;

public class monan {
    private int idMA, loaiMA;
    private String tenMA;
    private double giaMA;
    private byte[] HinhMA;
    private boolean Khoa;

    public monan() {
    }

    public monan(int idMA, byte[] HinhMA, String tenMA, double giaMA) {
        this.idMA = idMA;
        this.HinhMA = HinhMA;
        this.giaMA = giaMA;
        this.tenMA = tenMA;
    }

    public monan(int idMA,byte[] HinhMA, String tenMA, double giaMA,boolean Khoa) {
        this.idMA = idMA;
        this.HinhMA = HinhMA;
        this.giaMA = giaMA;
        this.tenMA = tenMA;
        this.Khoa = Khoa;
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

    public byte[] getHinhMA() {
        return HinhMA;
    }

    public void setHinhMA(byte[] hinhMA) {
        HinhMA = hinhMA;
    }

    public int getLoaiMA() {
        return loaiMA;
    }

    public void setLoaiMA(int loaiMA) {
        this.loaiMA = loaiMA;
    }

    public String getTenMA() {
        return tenMA;
    }

    public void setTenMA(String tenMA) {
        this.tenMA = tenMA;
    }

    public boolean isKhoa() {
        return Khoa;
    }

    public void setKhoa(boolean khoa) {
        Khoa = khoa;
    }
}
