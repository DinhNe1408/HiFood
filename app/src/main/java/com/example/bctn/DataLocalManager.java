package com.example.bctn;

import android.content.Context;

import com.example.bctn.domain.taikhoan;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataLocalManager {
    private static final String PREF_TAIKHOAN = "PREF_TAIKHOAN";
    private static DataLocalManager instances;
    private static String key_DaDangNhap;
    private MySharedPreferences mySharedPreferences;
    private static String key_DNTK = "DNTK";
    private static String key_DNMK = "DNMK";

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

    public static void setLogin(boolean isLogin){
        DataLocalManager.getInstance().mySharedPreferences.putBoolean(key_DaDangNhap,isLogin);
    }

    public static boolean getLogin(){
        return DataLocalManager.getInstance().mySharedPreferences.getBoolean(key_DaDangNhap);
    }

    public static void setTaiKhoan(String SDT, String MK){
        DataLocalManager.getInstance().mySharedPreferences.putString(key_DNTK,SDT);
        DataLocalManager.getInstance().mySharedPreferences.putString(key_DNMK,MK);
    }

    public static List<String> getTaiKhoan(){
        List<String> stringList = new ArrayList<>();
        stringList.add(DataLocalManager.getInstance().mySharedPreferences.getString(key_DNTK));
        stringList.add(DataLocalManager.getInstance().mySharedPreferences.getString(key_DNMK));
        return stringList;
    }



//    public static void setTaiKhoan(taikhoan mTaikhoan){
//        Gson gson = new Gson();
//        String strJson = gson.toJson(mTaikhoan);
//        DataLocalManager.getInstance().mySharedPreferences.putString(PREF_TAIKHOAN, strJson);
//    }
//
//    public static taikhoan getTaiKhoan(){
//        String strJson = DataLocalManager.getInstance().mySharedPreferences.getString(PREF_TAIKHOAN);
//        Gson gson = new Gson();
//        taikhoan mTaikhoan = gson.fromJson(strJson, taikhoan.class);
//        return  mTaikhoan;
//    }
}
