package com.example.bctn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.bctn.domain.key;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        key.sendNotification(context, "Thông báo", "Nội dung");
    }
}
