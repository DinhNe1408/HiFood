package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.bctn.MyAppication;
import com.example.bctn.Pagination;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.TimKiem1Adap;
import com.example.bctn.domain.quanan;

import java.util.ArrayList;
import java.util.List;

public class TimKiemAct extends AppCompatActivity {

    private EditText editT_TimKiem_tk;
    private RecyclerView recV_TimKiem;
    private List<quanan> quananList;
    private TimKiem1Adap timKiem1Adap;

    private boolean isLoading;
    private boolean isLastPage;
    private int totalPage = 5;
    private int currentPage = 1;
    private int PageSize = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        AnhXa();
        quananList = new ArrayList<>();

        timKiem1Adap = new TimKiem1Adap(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recV_TimKiem.addItemDecoration(itemDecoration);
        recV_TimKiem.setLayoutManager(linearLayoutManager);
        recV_TimKiem.setAdapter(timKiem1Adap);

        recV_TimKiem.addOnScrollListener(new Pagination(linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading = true;

                currentPage += 1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });

        editT_TimKiem_tk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                quananList.clear();
                if (editT_TimKiem_tk.getText().toString().trim().length() > 0) {
                    quananList.addAll(MyAppication.mDao.TimKiem(editT_TimKiem_tk.getText().toString().trim()));
                    timKiem1Adap.setData(quananList);
                }
                timKiem1Adap.notifyDataSetChanged();
            }
        });
    }

    private void loadNextPage() {
//        new Handler().postDelayed(() -> { },1000);
//        List<quanan> nextPage = MyAppication.mDao.TimKiem(editT_TimKiem_tk.getText().toString().trim(), PageSize, currentPage - 1);
//        timKiem1Adap.removeLoading();
//        quananList.addAll(nextPage);
//        timKiem1Adap.notifyDataSetChanged();
//
//        if (currentPage < totalPage && quananList.size() > 10) {
//            timKiem1Adap.addLoading();
//        } else {
//            isLastPage = true;
//        }
    }

    private void setFirstData() {
//        if (editT_TimKiem_tk.getText().toString().trim().length() > 0) {
//            quananList.clear();
//            quananList.addAll(MyAppication.mDao.TimKiem(editT_TimKiem_tk.getText().toString().trim(), PageSize, currentPage - 1));
//            timKiem1Adap.setData(quananList);
//        }
    }

    private void AnhXa() {
        editT_TimKiem_tk = findViewById(R.id.editT_TimKiem_tk);
        recV_TimKiem = findViewById(R.id.recV_TimKiem_tk);
    }
}