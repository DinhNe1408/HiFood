package com.example.bctn.domain;

import java.io.Serializable;
import java.util.List;

public class quanan implements Serializable {
    private int idQA, IDTK;
    private String tenQA, thgianQA;
    private byte[] hinhQA;
    private vitri vitriQA;
    private double khcachQA, saoQA;
    private List<monan> dsMA;
    private boolean Khoa;

    public quanan() {
        this.IDTK = -2;
    }

    public quanan(int idQA, String tenQA, byte[] hinhQA, vitri vitriQA) {
        this.idQA = idQA;
        this.tenQA = tenQA;
        this.hinhQA = hinhQA;
        this.vitriQA = vitriQA;
    }

    public quanan(int idQA, String tenQA, byte[] hinhQA, vitri vitriQA, double saoQA) {
        this.idQA = idQA;
        this.tenQA = tenQA;
        this.hinhQA = hinhQA;
        this.vitriQA = vitriQA;
        this.saoQA = saoQA;
    }

    public quanan(int idQA, String tenQA, byte[] hinhQA, vitri vitriQA, List<monan> dsMA) {
        this.idQA = idQA;
        this.tenQA = tenQA;
        this.hinhQA = hinhQA;
        this.vitriQA = vitriQA;
        this.dsMA = dsMA;

    }

    public quanan(int idQA, String tenQA, byte[] hinhQA, vitri vitriQA, boolean Khoa, List<monan> dsMA,int IDTK) {
        this.idQA = idQA;
        this.tenQA = tenQA;
        this.hinhQA = hinhQA;
        this.vitriQA = vitriQA;
        this.Khoa = Khoa;
        this.dsMA = dsMA;
        this.IDTK = IDTK;
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

    public String getThgianQA() {
        return thgianQA;
    }

    public void setThgianQA(String thgianQA) {
        this.thgianQA = thgianQA;
    }

    public byte[] getHinhQA() {
        return hinhQA;
    }

    public void setHinhQA(byte[] hinhQA) {
        this.hinhQA = hinhQA;
    }

    public vitri getVitriQA() {
        return vitriQA;
    }

    public void setVitriQA(vitri vitriQA) {
        this.vitriQA = vitriQA;
    }

    public double getKhcachQA() {
        return khcachQA;
    }

    public void setKhcachQA(double khcachQA) {
        this.khcachQA = khcachQA;
    }

    public double getSaoQA() {
        return saoQA;
    }

    public void setSaoQA(double saoQA) {
        this.saoQA = saoQA;
    }

    public List<monan> getDsMA() {
        return dsMA;
    }

    public void setDsMA(List<monan> dsMA) {
        this.dsMA = dsMA;
    }

    public boolean isKhoa() {
        return Khoa;
    }

    public void setKhoa(boolean khoa) {
        Khoa = khoa;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }
}
