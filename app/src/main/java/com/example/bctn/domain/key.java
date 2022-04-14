package com.example.bctn.domain;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.Spanned;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.text.HtmlCompat;

import com.example.bctn.R;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class key {
    public static final String key_ThongBao = "ThongBao";

    public static final int REQUEST_CODE_CAMERA = 111;
    public static final int REQUEST_CODE_FOLDER = 222;

    private static final String PATTERN_DATE_TIME = "dd.MM.yyyy HH:mm:ss";
    private static final String PATTERN_TIME_DATE = "HH:mm:ss dd.MM.yyyy";
    // key Menu
    public static final int key_THONGTINCANHAN = 861;
    public static final int key_CAIDAT = 280;
    public static final int key_GOPY = 778;
    public static final int key_GIOITHIEU = 120;
    public static final int key_KHUYENMAI = 809;
    public static final int key_PHUONGTHUCTHANHTOAN = 132;
    public static final int key_DANGXUAT = 103;
    public static final int key_QLTaiKhoan = 123;
    public static final int key_QLQuanAn = 432;
    public static final int key_QLDonHang = 980;
    public static final int key_ThongKe = 983;
    // Đơn hàng
    public static final String key_dh_HoanThanh = "HoanThanh";
    public static final String key_dh_DangGiao = "DangGiao";
    public static final String key_dh_Nhap = "Nhap";

    // Chuyển Dữ liệu
    public static final String key_IDQA = "IDQA";
    public static final String key_IDTK = "IDTK";
    public static final String key_IDDH = "IDDH";
    public static final String key_TTGiao = "TTGiao";

    public static final String key_Them = "Them";
    public static final String key_Sua = "Sua";
    //

    public static LocalDateTime String2DateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm\n dd-MM-yyyy");
        return LocalDateTime.parse(time, formatter);
    }

    public static String Date2String(LocalDateTime time) {
        return DateTimeFormatter.ofPattern("HH:mm\n dd-MM-yyyy", Locale.getDefault()).format(time);
    }

    public static String TinhThoiGian(LocalDateTime mTime) {
        Duration CalTime = Duration.between(mTime, LocalDateTime.now());
        if (CalTime.toDays() >= 7) {
            return Date2String(mTime);
        } else if (CalTime.toDays() > 0) {
            return "Khoảng " + String.valueOf(CalTime.toDays()) + " ngày trước";
        } else if (CalTime.toHours() > 0) {
            return "Khoảng " + String.valueOf(CalTime.toHours()) + " tiếng trước";
        } else if (CalTime.toMinutes() > 0) {
            return "Khoảng " + String.valueOf(CalTime.toMinutes()) + " phút trước";
        } else {
            return "Vài giây trước";
        }
        //return String.valueOf(Duration.between(mTime,LocalDateTime.now()));
    }

    public static Bitmap Byte2Bitmap(byte[] hinh) {
        return BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
    }

    public static Bitmap Drawable2Bitmap(Context context,int Drawable){
        return BitmapFactory.decodeResource(context.getResources(), Drawable);
    }

    public static String Dou2Money(double money) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 đ");
        return decimalFormat.format(money);
    }

    public static Spanned TienvaSL(double money, int SL) {
        return HtmlCompat.fromHtml("<b>" + Dou2Money(money) + "</b> (" + SL + " phần)", HtmlCompat.FROM_HTML_MODE_LEGACY);
    }

    public static void sendNotification(Context mContext, String title, String text) {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.hifood);
        Uri url = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

//        Intent intent = new Intent(this,ChiTietSanPham.class);
//        intent.putExtra("IDSP_CTSP",1);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,getThongbaoID(),intent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(mContext, key.key_ThongBao)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.khongchu)
                .setSound(url)
                .setAutoCancel(true)
                //.setContentIntent(pendingIntent)
                .setColor(mContext.getResources().getColor(R.color.hifood1))
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mContext);
        notificationManagerCompat.notify(getThongbaoID(), notification);
    }

    private static int getThongbaoID() {
        return (int) new Date().getTime();
    }
}
