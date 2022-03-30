package com.example.bctn.domain;

public class menu_option {
    private int idMenu, hinhMenu, bgMenu;
    private String noidungMenu;

    public menu_option(int idMenu, int hinhMenu, int bgMenu, String noidungMenu) {
        this.idMenu = idMenu;
        this.hinhMenu = hinhMenu;
        this.bgMenu = bgMenu;
        this.noidungMenu = noidungMenu;
    }

    public int getBgMenu() {
        return bgMenu;
    }

    public void setBgMenu(int bgMenu) {
        this.bgMenu = bgMenu;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getHinhMenu() {
        return hinhMenu;
    }

    public void setHinhMenu(int hinhMenu) {
        this.hinhMenu = hinhMenu;
    }

    public String getNoidungMenu() {
        return noidungMenu;
    }

    public void setNoidungMenu(String noidungMenu) {
        this.noidungMenu = noidungMenu;
    }
}
