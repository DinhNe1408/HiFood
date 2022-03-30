package com.example.bctn;

import android.content.Context;

import com.example.bctn.domain.taikhoan;
import com.google.gson.Gson;

public class DataLocalManager {
    private static final String PREF_TAIKHOAN = "PREF_TAIKHOAN";
    private static DataLocalManager instances;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context mContext){
        instances = new DataLocalManager();
        instances.mySharedPreferences = new MySharedPreferences(mContext);
    }

    public  static  DataLocalManager getInstance(){
        if (instances == null){
            instances = new DataLocalManager();
        }
        return instances;
    }

    public static void setTaiKhoan(taikhoan mTaikhoan){
        Gson gson = new Gson();
        String strJson = gson.toJson(mTaikhoan);
        DataLocalManager.getInstance().mySharedPreferences.putString(PREF_TAIKHOAN, strJson);
    }

    public static taikhoan getTaiKhoan(){
        String strJson = DataLocalManager.getInstance().mySharedPreferences.getString(PREF_TAIKHOAN);
        Gson gson = new Gson();
        taikhoan mTaikhoan = gson.fromJson(strJson, taikhoan.class);
        return  mTaikhoan;
    }
}
