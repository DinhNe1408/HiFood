package com.example.bctn.domain;

public class monan {
    private int idMA,giaMA,gtgMA,hMA, saoMA, loaiMA;
    private String maMA, tenMA, motaMA;

    public monan(int hMA, String tenMA) {
        this.hMA = hMA;
        this.tenMA = tenMA;
    }

    public monan(int idMA, int giaMA, int gtgMA, int hMA, int saoMA, int loaiMA, String maMA, String tenMA, String motaMA) {
        this.idMA = idMA;
        this.giaMA = giaMA;
        this.gtgMA = gtgMA;
        this.hMA = hMA;
        this.saoMA = saoMA;
        this.loaiMA = loaiMA;
        this.maMA = maMA;
        this.tenMA = tenMA;
        this.motaMA = motaMA;
    }

    public int getIdMA() {
        return idMA;
    }

    public void setIdMA(int idMA) {
        this.idMA = idMA;
    }

    public int getGiaMA() {
        return giaMA;
    }

    public void setGiaMA(int giaMA) {
        this.giaMA = giaMA;
    }

    public int getGtgMA() {
        return gtgMA;
    }

    public void setGtgMA(int gtgMA) {
        this.gtgMA = gtgMA;
    }

    public int getHMA() {
        return hMA;
    }

    public void setHMA(int hMA) {
        this.hMA = hMA;
    }

    public int getSaoMA() {
        return saoMA;
    }

    public void setSaoMA(int saoMA) {
        this.saoMA = saoMA;
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

    public String getMotaMA() {
        return motaMA;
    }

    public void setMotaMA(String motaMA) {
        this.motaMA = motaMA;
    }
}
