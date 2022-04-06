package com.example.bctn.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class donhang implements Serializable {
    private int IDDH, IDQA;
    private double GiaHD;
    private vitri VitriDH;
    private List<ctdh> ctdhList = new ArrayList<>();
    private Long TGDat, TGGiao;
    private String TTDH;
    private Map<Integer,ctdh> cthdMap;
    public donhang() {
    }

    public donhang(int IDDH, int IDQA, double giaHD, vitri vitriDH, Long TGDat, Long TGGiao, String TTDH, List<ctdh> ctdhList) {
        this.IDDH = IDDH;
        this.IDQA = IDQA;
        this.GiaHD = giaHD;
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
        return ctdhList.stream().mapToInt(com.example.bctn.domain.ctdh::getSLMA).sum();
    }

    public boolean isctdhList(){
        return ctdhList.size() == 0;
    }

    public ctdh getCTDHinDH(int IDDH){
        if(!isctdhList())
        {
            return cthdMap.get(IDDH);
        }
        return null;
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

    public double getGiaHD() {
        return GiaHD;
    }

    public void setGiaHD(double giaHD) {
        GiaHD = giaHD;
    }

    public vitri getVitriDH() {
        return VitriDH;
    }

    public void setVitriDH(vitri vitriDH) {
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

    public void setTTDH(String TTDH) {
        this.TTDH = TTDH;
    }
}
