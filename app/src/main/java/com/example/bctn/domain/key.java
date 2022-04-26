package com.example.bctn.domain;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.Spanned;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.text.HtmlCompat;

import com.example.bctn.R;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class key {
    public static final String key_ThongBao = "ThongBao";

    public static final int REQUEST_CODE_CAMERA = 111;
    public static final int REQUEST_CODE_FOLDER = 222;

    private static final String PATTERN_DATE_TIME = "dd.MM.yyyy HH:mm:ss";
    private static final String PATTERN_TIME_DATE = "HH:mm:ss dd.MM.yyyy";
    // key Menu
    public static final int key_ThongTinNguoiDung = 861;
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
    public static final int key_ThongTinQA = 678;
    public static final int key_QLMonAn = 7091;
    public static final int key_DonHangQA = 3021;

    // Đơn hàng
    public static final String key_dh_HoanThanh = "HoanThanh";
    public static final String key_dh_DangGiao = "DangGiao";
    public static final String key_dh_Nhap = "Nhap";

    // Chuyển Dữ liệu
    public static final String key_IDQA = "IDQA";
    public static final String key_IDTK = "IDTK";
    public static final String key_IDDH = "IDDH";
    public static final String key_IDMA = "IDMA";
    public static final String key_TTGiao = "TTGiao";

    public static final String key_LoaiCS = "LoaiCS";
    public static final String key_Them = "Them";
    public static final String key_Sua = "Sua";
    //

    public static LocalDateTime String2DateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm\n dd-MM-yyyy");
        return LocalDateTime.parse(time, formatter);
    }


    public static byte[] BitmapDrawable2Byte(BitmapDrawable bitmapDrawable) {
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);

        return byteArray.toByteArray();
    }


    public static String DateFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return  simpleDateFormat.format(date);
    }

    public static String DateTimeFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return  simpleDateFormat.format(date);
    }

    public static String DateFormatSQL(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  simpleDateFormat.format(date);
    }

    public static Date DateFromSQL(String date){
        Date temp = null;
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
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

    public static Bitmap Drawable2Bitmap(Context context, int Drawable) {
        return BitmapFactory.decodeResource(context.getResources(), Drawable);
    }

    public static String Dou2Money(double money) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 đ");
        return decimalFormat.format(money);
    }

    public static Spanned TienvaSL(double money, int SL) {
        return HtmlCompat.fromHtml("<b>" + Dou2Money(money) + "</b> (" + SL + " phần)", HtmlCompat.FROM_HTML_MODE_LEGACY);
    }

    public static boolean isSDT(String SDT) {
        if (SDT.length() == 10) {
            return SDT.matches("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}");
        }
        return false;
    }

    public static boolean isMk(String MK) {
        return MK.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,20}$");
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
