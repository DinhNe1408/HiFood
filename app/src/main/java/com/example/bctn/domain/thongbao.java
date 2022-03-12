package com.example.bctn.domain;


import java.time.LocalDateTime;

public class thongbao {
    private int hinhTB;
    private String noidungTB;
    private LocalDateTime thoigianTB;

    public thongbao(int hinhTB, String noidungTB,LocalDateTime thoigianTB) {
        this.hinhTB = hinhTB;
        this.noidungTB = noidungTB;
        this.thoigianTB = thoigianTB;
    }

    public int getHinhTB() {
        return hinhTB;
    }

    public void setHinhTB(int hinhTB) {
        this.hinhTB = hinhTB;
    }

    public String getNoidungTB() {
        return noidungTB;
    }

    public void setNoidungTB(String noidungTB) {
        this.noidungTB = noidungTB;
    }

    public LocalDateTime getThoigianTB() {
        return thoigianTB;
    }

    public void setThoigianTB(LocalDateTime thoigianTB) {
        this.thoigianTB = thoigianTB;
    }
}
