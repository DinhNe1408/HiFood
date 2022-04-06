package com.example.bctn.domain;

import java.io.Serializable;

public class ctdh implements Serializable {
    private int IDMA, SLMA;
    private String GhiChu;

    public ctdh(int IDMA, int SLMA, String ghiChu) {
        this.IDMA = IDMA;
        this.SLMA = SLMA;
        this.GhiChu = ghiChu;
    }

    public int getIDMA() {
        return IDMA;
    }

    public void setIDMA(int IDMA) {
        this.IDMA = IDMA;
    }

    public int getSLMA() {
        return SLMA;
    }

    public void setSLMA(int SLMA) {
        this.SLMA = SLMA;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
