package com.example.bctn.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class taikhoan {
    private int idTK;
    private byte[] hinhTK;
    private String tenTK, sdtTK,mkTK, role;
    private boolean khoa;
    private vitri Vitri, CurVitri;
    private List<donhang> donhangList = new ArrayList<>();
    private Map<Integer,donhang> donhangMap;


    public taikhoan() {
        this.idTK = -1;
    }

    public taikhoan(int idTK,String sdtTK,String mkTK, String tenTK , byte[] hinhTK,vitri vitri, String role,boolean khoa) {
        this.idTK = idTK;
        this.hinhTK = hinhTK;
        this.role = role;
        this.tenTK = tenTK;
        this.sdtTK = sdtTK;
        this.Vitri = vitri;
        this.mkTK = mkTK;
        this.khoa = khoa;
    }

    public boolean isdonhangList(){
        return donhangList.size() == 0;
    }

    public ctdh getListHDinTK(int IDQA,int IDMA){
        if(!isdonhangList()){
            if(donhangMap.get(IDQA) != null){
                ctdh ctdh = donhangMap.get(IDQA).getCTDHinDH(IDMA);
                if(ctdh != null){
                    return ctdh;
                }
            }
        }
        return null;
    }

    public void setDonhangList(List<donhang> donhangList) {
        this.donhangList = donhangList;
        donhangMap = new HashMap<>();
        for (donhang val : donhangList) {
            donhangMap.put(val.getIDDH(), val);
        }
    }

    public int getIdTK() {
        return idTK;
    }

    public void setIdTK(int idTK) {
        this.idTK = idTK;
    }

    public byte[] getHinhTK() {
        return hinhTK;
    }

    public void setHinhTK(byte[] hinhTK) {
        this.hinhTK = hinhTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getSdtTK() {
        return sdtTK;
    }

    public void setSdtTK(String sdtTK) {
        this.sdtTK = sdtTK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMkTK() {
        return mkTK;
    }

    public void setMkTK(String mkTK) {
        this.mkTK = mkTK;
    }

    public boolean isKhoa() {
        return khoa;
    }

    public void setKhoa(boolean khoa) {
        this.khoa = khoa;
    }

    public vitri getVitri() {
        return Vitri;
    }

    public void setVitri(vitri vitri) {
        Vitri = vitri;
    }

    public vitri getCurVitri() {
        return CurVitri;
    }

    public void setCurVitri(vitri curVitri) {
        CurVitri = curVitri;
    }

    public List<donhang> getDonhangList() {
        return donhangList;
    }
}
