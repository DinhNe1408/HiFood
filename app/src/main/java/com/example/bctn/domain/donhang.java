package com.example.bctn.domain;

import com.example.bctn.activity.DangNhapAct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class donhang implements Serializable {
    private int IDDH, IDQA;
    private double PhiVC, TongDH, TienGiam, TongTienMA;
    private Long TGDat, TGGiao;
    private String TTDH, TenNN, SDTNN, VitriDH;
    private Map<Integer, ctdh> cthdMap = new HashMap<>();

    public donhang() {
    }

    public donhang(int IDDH, int IDQA, String TenNN, String SDTNN,
                   double TongTienMA, double PhiVC, double TienGiam, double TongDH,
                   String vitriDH, Long TGDat, Long TGGiao, String TTDH) {
        this.IDDH = IDDH;
        this.IDQA = IDQA;
        this.TenNN = TenNN;
        this.SDTNN = SDTNN;
        this.TongTienMA = TongTienMA;
        this.PhiVC = PhiVC;
        this.TienGiam = TienGiam;
        this.TongDH = TongDH;
        this.VitriDH = vitriDH;
        this.TGDat = TGDat;
        this.TGGiao = TGGiao;
        this.TTDH = TTDH;
    }

    public donhang(int IDDH, int IDQA, String TenNN, String SDTNN,
                   double TongTienMA, double PhiVC, double TienGiam, double TongDH,
                   String vitriDH, Long TGDat, Long TGGiao, String TTDH, Map<Integer, ctdh> cthdMap) {
        this.IDDH = IDDH;
        this.IDQA = IDQA;
        this.TenNN = TenNN;
        this.SDTNN = SDTNN;
        this.TongTienMA = TongTienMA;
        this.PhiVC = PhiVC;
        this.TienGiam = TienGiam;
        this.TongDH = TongDH;
        this.VitriDH = vitriDH;
        this.TGDat = TGDat;
        this.TGGiao = TGGiao;
        this.TTDH = TTDH;
        this.cthdMap = cthdMap;
    }

    public int getTongSoL() {
        return cthdMap.values().stream().mapToInt(ctdh::getSLMA).sum();
    }

    public double getTongTienMAMap() {
        return cthdMap.values().stream().mapToDouble(ctdh -> ctdh.getGiaMA() * ctdh.getSLMA()).sum();
    }

    public int getTongSoLuong() {
        return cthdMap.values().stream().mapToInt(ctdh::getSLMA).sum();
    }

    public int getIDDH() {
        return IDDH;
    }

    public void setIDDH(int IDDH) {
        this.IDDH = IDDH;
    }

    public int getIDQA() {
        return IDQA;
    }

    public void setIDQA(int IDQA) {
        this.IDQA = IDQA;
    }

    public double getPhiVC() {
        return PhiVC;
    }

    public void setPhiVC(double phiVC) {
        PhiVC = phiVC;
    }

    public double getTongDH() {
        return TongDH;
    }

    public void setTongDH(double tongDH) {
        TongDH = tongDH;
    }

    public double getTienGiam() {
        return TienGiam;
    }

    public void setTienGiam(double tienGiam) {
        TienGiam = tienGiam;
    }

    public String getTenNN() {
        return TenNN;
    }

    public void setTenNN(String tenNN) {
        TenNN = tenNN;
    }

    public String getSDTNN() {
        return SDTNN;
    }

    public void setSDTNN(String SDTNN) {
        this.SDTNN = SDTNN;
    }

    public String getVitriDH() {
        return VitriDH;
    }

    public void setVitriDH(String vitriDH) {
        VitriDH = vitriDH;
    }

    public Long getTGDat() {
        return TGDat;
    }

    public void setTGDat(Long TGDat) {
        this.TGDat = TGDat;
    }

    public Long getTGGiao() {
        return TGGiao;
    }

    public void setTGGiao(Long TGGiao) {
        this.TGGiao = TGGiao;
    }

    public String getTTDH() {
        return TTDH;
    }

    public Map<Integer, ctdh> getCthdMap() {
        return cthdMap;
    }

    public void setCthdMap(Map<Integer, ctdh> cthdMap) {
        this.cthdMap = cthdMap;
    }

    public void newCtdhMap(){ this.cthdMap = new HashMap<>();}

    public void setTTDH(String TTDH) {
        this.TTDH = TTDH;
    }

    public void setTongTienMA(double tongTienMA) {
        TongTienMA = tongTienMA;
    }

    public double getTongTienMA() {
        return TongTienMA;
    }
}
