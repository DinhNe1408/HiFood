package com.example.bctn.domain;

import com.example.bctn.activity.DangNhapAct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class donhang implements Serializable {
    private int IDDH, IDQA;
    private double PhiVC, TongDH, TienGiam;
    private Long TGDat, TGGiao;
    private String TTDH, TenNN, SDTNN,VitriDH;
    private List<ctdh> ctdhList = new ArrayList<>();
    private Map<Integer, ctdh> cthdMap = new HashMap<>();

    public donhang() {
    }

    public donhang(int IDDH, int IDQA, String TenNN, String SDTNN, double PhiVC, double TienGiam, double TongDH,String vitriDH, Long TGDat, Long TGGiao, String TTDH, List<ctdh> ctdhList) {
        this.IDDH = IDDH;
        this.IDQA = IDQA;
        this.TenNN = TenNN;
        this.SDTNN = SDTNN;
        this.PhiVC = PhiVC;
        this.TienGiam = TienGiam;
        this.TongDH = TongDH;
        this.VitriDH = vitriDH;
        this.ctdhList = ctdhList;
        this.TGDat = TGDat;
        this.TGGiao = TGGiao;
        this.TTDH = TTDH;

        cthdMap = new HashMap<>();
        for (ctdh val : ctdhList) {
            cthdMap.put(val.getIDMA(), val);
        }
    }

    public donhang(int IDDH, int IDQA, double TongDH, String vitriDH, Long TGDat, Long TGGiao, String TTDH, List<ctdh> ctdhList) {
        this.IDDH = IDDH;
        this.IDQA = IDQA;
        this.TongDH = TongDH;
        this.VitriDH = vitriDH;
        this.ctdhList = ctdhList;
        this.TGDat = TGDat;
        this.TGGiao = TGGiao;
        this.TTDH = TTDH;

        cthdMap = new HashMap<>();
        for (ctdh val : ctdhList) {
            cthdMap.put(val.getIDMA(), val);
        }
    }

    public int getTongSoL() {
        return ctdhList.stream().mapToInt(ctdh::getSLMA).sum();
    }
    public double getTongTienMA() {
        return cthdMap.values().stream().mapToDouble(ctdh -> ctdh.getGiaMA() * ctdh.getSLMA()).sum();
    }

    public int getTongSoLuong() {
        return cthdMap.values().stream().mapToInt(ctdh::getSLMA).sum();
    }

    public boolean isctdhList() {
        return ctdhList.size() == 0;
    }

    public ctdh getCTDHinDH(int IDDH) {
        return cthdMap.get(IDDH);
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

    public List<ctdh> getCtdhList() {
        return ctdhList;
    }

    public void setCtdhList(List<ctdh> ctdhList) {
        this.ctdhList = ctdhList;
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

    public void setTTDH(String TTDH) {
        this.TTDH = TTDH;
    }
}
