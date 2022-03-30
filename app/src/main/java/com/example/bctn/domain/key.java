package com.example.bctn.domain;

import android.os.Build;

import com.example.bctn.DAO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class key {
    private static final String PATTERN_DATE_TIME = "dd.MM.yyyy HH:mm:ss";
    private static final String PATTERN_TIME_DATE = "HH:mm:ss dd.MM.yyyy";
    // key TaiKhoan
    public static final int key_THONGTINCANHAN= 861;
    public static final int key_CAIDAT = 280;
    public static final int key_LICHSUDONHANG = 956;
    public static final int key_GOPY = 778;
    public static final int key_GIOITHIEU = 120;
    public static final int key_KHUYENMAI = 809;
    public static final int key_PHUONGTHUCTHANHTOAN = 132;

    //

    public static LocalDateTime String2DateTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm\n dd-MM-yyyy");
        return LocalDateTime.parse(time, formatter);
    }

    public static String Date2String(LocalDateTime time){
        return DateTimeFormatter.ofPattern("HH:mm\n dd-MM-yyyy", Locale.getDefault()).format(time);
    }

    public static String TinhThoiGian(LocalDateTime mTime){
        Duration CalTime = Duration.between(mTime,LocalDateTime.now());
        if (CalTime.toDays() >= 7){
            return Date2String(mTime);
        } else if (CalTime.toDays() > 0){
            return "Khoảng " +String.valueOf(CalTime.toDays()) + " ngày trước";
        } else if (CalTime.toHours() > 0){
            return "Khoảng " + String.valueOf(CalTime.toHours()) + " tiếng trước";
        } else if (CalTime.toMinutes() > 0){
            return "Khoảng " +String.valueOf(CalTime.toMinutes()) + " phút trước";
        } else {
            return "Vài giây trước";
        }
        //return String.valueOf(Duration.between(mTime,LocalDateTime.now()));
    }
}
