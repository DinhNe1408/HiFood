package com.example.bctn.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bctn.R;
import com.example.bctn.domain.donhang;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GioHangBottomSheet extends BottomSheetDialogFragment {

    private static final String KEY_GIOHANG = "KEY_GIOHANG";
    private donhang mDonhang;

    public static GioHangBottomSheet newInstance (donhang donhang){
        GioHangBottomSheet gioHangBottomSheet = new GioHangBottomSheet();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_GIOHANG, donhang);
        gioHangBottomSheet.setArguments(bundle);
        return gioHangBottomSheet;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null){
            mDonhang = (donhang) bundle.get(KEY_GIOHANG);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_gio_hang,null);
        bottomSheetDialog.setContentView(view);
        return bottomSheetDialog;
    }
}
