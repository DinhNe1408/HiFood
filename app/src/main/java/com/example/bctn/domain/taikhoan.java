package com.example.bctn.domain;

public class taikhoan {
    private int idTK;
    private byte[] hinhTK;
    private String tenTK, sdtTK, vitriTK,mkTK, role;
    private double posLat, posLong;

    public taikhoan() {
        this.idTK = -1;
    }

    public taikhoan(int idTK,String sdtTK,String mkTK, String tenTK , byte[] hinhTK, String vitriTK, double posLat, double posLong, String role) {
        this.idTK = idTK;
        this.hinhTK = hinhTK;
        this.role = role;
        this.tenTK = tenTK;
        this.sdtTK = sdtTK;
        this.vitriTK = vitriTK;
        this.posLat = posLat;
        this.posLong = posLong;
        this.mkTK = mkTK;
    }

    public int getIdTK() {
        return idTK;
    }

    public void setIdTK(int idTK) {
        this.idTK = idTK;
    }

    public byte[] getHinhTK() {
        return hinhTK;
    }

    public void setHinhTK(byte[] hinhTK) {
        this.hinhTK = hinhTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getSdtTK() {
        return sdtTK;
    }

    public void setSdtTK(String sdtTK) {
        this.sdtTK = sdtTK;
    }

    public Double getPosLat() {
        return posLat;
    }

    public void setPosLat(double posLat) {
        this.posLat = posLat;
    }

    public Double getPosLong() {
        return posLong;
    }

    public void setPosLong(double posLong) {
        this.posLong = posLong;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVitriTK() {
        return vitriTK;
    }

    public void setVitriTK(String vitriTK) {
        this.vitriTK = vitriTK;
    }

    public String getMkTK() {
        return mkTK;
    }

    public void setMkTK(String mkTK) {
        this.mkTK = mkTK;
    }
}
