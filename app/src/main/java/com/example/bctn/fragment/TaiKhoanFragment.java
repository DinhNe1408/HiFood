package com.example.bctn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bctn.R;
import com.example.bctn.key;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanFragment extends Fragment {

    private View mView;
    List<Integer> listKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tai_khoan,container,false);

        listKey = new ArrayList<>();
        listKey.add(key.key_THONGTINCANHAN);
        listKey.add(key.key_LICHSUDONHANG);
        listKey.add(key.key_GOPY);
        listKey.add(key.key_GIOITHIEU);
        listKey.add(key.key_CAIDAT);
        return mView;
    }
}
