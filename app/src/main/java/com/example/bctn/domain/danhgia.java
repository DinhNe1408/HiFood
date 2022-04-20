package com.example.bctn.domain;

import java.util.Date;

public class danhgia {
    private byte[] HinhNDG;
    private String ThoiGianDG;
    private String TenNDG, NoiDungDG;
    private double SaoDG;

    public danhgia() {
    }

    public danhgia(String noiDungDG, double saoDG) {
        this.NoiDungDG = noiDungDG;
        this.SaoDG = saoDG;
    }

    public danhgia(byte[] hinhNDG, String tenNDG, String thoiGianDG, String noiDungDG, double saoDG) {
        this.HinhNDG = hinhNDG;
        this.ThoiGianDG = thoiGianDG;
        this.TenNDG = tenNDG;
        this.NoiDungDG = noiDungDG;
        this.SaoDG = saoDG;
    }

    public String getTenNDG() {
        return TenNDG;
    }

    public void setTenNDG(String tenNDG) {
        TenNDG = tenNDG;
    }

    public String getNoiDungDG() {
        return NoiDungDG;
    }

    public void setNoiDungDG(String noiDungDG) {
        NoiDungDG = noiDungDG;
    }

    public byte[] getHinhNDG() {
        return HinhNDG;
    }

    public void setHinhNDG(byte[] hinhNDG) {
        HinhNDG = hinhNDG;
    }

    public String getThoiGianDG() {
        return ThoiGianDG;
    }

    public void setThoiGianDG(String thoiGianDG) {
        ThoiGianDG = thoiGianDG;
    }


    public double getSaoDG() {
        return SaoDG;
    }

    public void setSaoDG(double saoDG) {
        SaoDG = saoDG;
    }
}
