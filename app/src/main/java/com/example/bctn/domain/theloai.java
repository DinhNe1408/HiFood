package com.example.bctn.domain;

public class theloai {
    private int idTL, hinhTL;
    private String noidungTL;

    public theloai(int idTL, int hinhTL, String noidungTL) {
        this.idTL = idTL;
        this.hinhTL = hinhTL;
        this.noidungTL = noidungTL;
    }

    public int getIdTL() {
        return idTL;
    }

    public void setIdTL(int idTL) {
        this.idTL = idTL;
    }

    public int getHinhTL() {
        return hinhTL;
    }

    public void setHinhTL(int hinhTL) {
        this.hinhTL = hinhTL;
    }

    public String getNoidungTL() {
        return noidungTL;
    }

    public void setNoidungTL(String noidungTL) {
        this.noidungTL = noidungTL;
    }
}
