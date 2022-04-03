package com.example.bctn.domain;

import java.io.Serializable;
import java.util.List;

public class donhang implements Serializable {
    private int idDH;
    private double giaHD;
    private vitri vitriDH;
    private List<ctdh> ctdh;

    public donhang() {
    }

    public donhang(int idDH, double giaHD, vitri vitriDH, List<ctdh> ctdh) {
        this.idDH = idDH;
        this.giaHD = giaHD;
        this.vitriDH = vitriDH;
        this.ctdh = ctdh;
    }

    public int getTongSoL() {
        return ctdh.stream().mapToInt(com.example.bctn.domain.ctdh::getSLMA).sum();
    }
}
