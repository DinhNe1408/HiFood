package com.example.bctn;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.widget.Toast;

import com.example.bctn.activity.admin.QuanTri;
import com.example.bctn.domain.key;
import com.example.bctn.domain.taikhoan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyAppication extends Application {

    public static DAO mDao;
    public static taikhoan mTaiKhoan;

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
        mDao = new DAO(getApplicationContext());
        List<String> stringList =  DataLocalManager.getTaiKhoan();

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(2,2);

        map.get(3);

        if (!stringList.get(0).equals("")){
            mTaiKhoan = MyAppication.mDao.GetTK(stringList.get(0), stringList.get(1));
        } else {
            mTaiKhoan = new taikhoan();
        }


        TaoKenhTB();
    }

    private void TaoKenhTB(){
        CharSequence tentb = getString(R.string.thongbao);
        String noidungtb = getString(R.string.app_name);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel kenh = new NotificationChannel(key.key_ThongBao, tentb, importance);

        kenh.setDescription(noidungtb);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if(notificationManager != null){
            notificationManager.createNotificationChannel(kenh);
        }

    }
}
