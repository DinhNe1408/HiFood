package com.example.bctn;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.example.bctn.domain.key;
import com.example.bctn.domain.taikhoan;

public class MyAppication extends Application {

    public static DAO mDao;
    public static taikhoan mTaiKhoan;


    @Override
    public void onCreate() {
        super.onCreate();

        mDao = new DAO(getApplicationContext());
        mTaiKhoan = new taikhoan();

        DataLocalManager.init(getApplicationContext());
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
