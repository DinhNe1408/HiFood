package com.example.bctn.check_internet;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.bctn.R;

public class NetworkChangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.isConnectInternet(context)){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            View mView = LayoutInflater.from(context).inflate(R.layout.dialog_check_internet,null);
            builder.setView(mView);

            AppCompatButton btnThuLai = mView.findViewById(R.id.btn_ThuLai);

            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnThuLai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    onReceive(context, intent);
                }
            });
        }
    }
}
