package com.example.bctn.domain;

public class donhang_dhfrag {
    private int IDDH, IDQA;
    private byte[] HinhQA;
    private String TenQA, ViTriQA,TTGiao;
    private double TongTienMA;
    private int SoPhan;

    public donhang_dhfrag(int IDDH,int IDQA, byte[] hinhQA, String tenQA, String viTriQA, double tongTienMA, int soPhan, String tTGiao) {
        this.IDDH = IDDH;
        this.IDQA = IDQA;
        this.HinhQA = hinhQA;
        this.TenQA = tenQA;
        this.ViTriQA = viTriQA;
        this.TongTienMA = tongTienMA;
        this.SoPhan = soPhan;
        this.TTGiao = tTGiao;
    }

    public int getIDDH() {
        return IDDH;
    }

    public void setIDDH(int IDDH) {
        this.IDDH = IDDH;
    }

    public byte[] getHinhQA() {
        return HinhQA;
    }

    public void setHinhQA(byte[] hinhQA) {
        HinhQA = hinhQA;
    }

    public String getTenQA() {
        return TenQA;
    }

    public void setTenQA(String tenQA) {
        TenQA = tenQA;
    }

    public String getViTriQA() {
        return ViTriQA;
    }

    public void setViTriQA(String viTriQA) {
        ViTriQA = viTriQA;
    }

    public double getTongTienMA() {
        return TongTienMA;
    }

    public void setTongTienMA(double tongTienMA) {
        TongTienMA = tongTienMA;
    }

    public int getSoPhan() {
        return SoPhan;
    }

    public void setSoPhan(int soPhan) {
        SoPhan = soPhan;
    }

    public String getTTGiao() {
        return TTGiao;
    }

    public void setTTGiao(String TTGiao) {
        this.TTGiao = TTGiao;
    }

    public int getIDQA() {
        return IDQA;
    }

    public void setIDQA(int IDQA) {
        this.IDQA = IDQA;
    }
}
