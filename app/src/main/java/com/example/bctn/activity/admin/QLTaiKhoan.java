package com.example.bctn.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsTK_qltk_Adap;
import com.example.bctn.domain.key;
import com.example.bctn.domain.taikhoan;

import java.util.List;

public class QLTaiKhoan extends AppCompatActivity {

    private RecyclerView recV_DSTaiKhoan;
    private Toolbar tool3_QLTaiKhoan;
    private SearchView iTimkiem;
    private DsTK_qltk_Adap dsTK_qltk_adap;
    private List<taikhoan> taikhoanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltai_khoan);
        AnhXa();

        setSupportActionBar(tool3_QLTaiKhoan);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        tool3_QLTaiKhoan.setTitle("Quản lý tài khoản");
        tool3_QLTaiKhoan.setNavigationOnClickListener(view -> onBackPressed());

        registerForContextMenu(recV_DSTaiKhoan);
    }

    @Override
    protected void onStart() {
        taikhoanList = MyAppication.mDao.GetListTK();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dsTK_qltk_adap = new DsTK_qltk_Adap(this, taikhoanList);

        recV_DSTaiKhoan.setAdapter(dsTK_qltk_adap);
        recV_DSTaiKhoan.setLayoutManager(linearLayoutManager);
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_ql, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        iTimkiem = (SearchView) menu.findItem(R.id.iTimkiem).getActionView();
        iTimkiem.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        iTimkiem.setMaxWidth(Integer.MAX_VALUE);

        iTimkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                dsTK_qltk_adap.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                dsTK_qltk_adap.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iAdd:
                Intent intent = new Intent(QLTaiKhoan.this, UpTaiKhoan.class);
                intent.putExtra(key.key_LoaiCS, key.key_Them);
                startActivity(intent);
                return true;
            case R.id.iTimkiem:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void AnhXa() {
        recV_DSTaiKhoan = findViewById(R.id.recV_DSTaiKhoan);
        tool3_QLTaiKhoan = findViewById(R.id.tool3_QLTaiKhoan);
    }
}