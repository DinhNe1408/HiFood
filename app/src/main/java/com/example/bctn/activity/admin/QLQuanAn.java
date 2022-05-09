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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsQA_qlqa_Adap;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;

import java.util.ArrayList;

public class QLQuanAn extends AppCompatActivity {

    private RecyclerView recV_DSQuanAn;
    private Toolbar tool3_QLQuanAn;
    private SearchView iTimkiem;
    private DsQA_qlqa_Adap dsQA_qlqa_adap;
    private ArrayList<quanan> quananList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlquan_an);

        AnhXa();

        setSupportActionBar(tool3_QLQuanAn);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        tool3_QLQuanAn.setTitle("Quản lý quán ăn");
        tool3_QLQuanAn.setNavigationOnClickListener(view -> onBackPressed());

        registerForContextMenu(recV_DSQuanAn);
    }

    @Override
    protected void onStart() {
        quananList = (ArrayList<quanan>) MyAppication.mDao.GetListQA();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dsQA_qlqa_adap = new DsQA_qlqa_Adap(this, quananList);

        recV_DSQuanAn.setAdapter(dsQA_qlqa_adap);
        recV_DSQuanAn.setLayoutManager(linearLayoutManager);
        super.onStart();
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = dsQA_qlqa_adap.getPosition();
        } catch (Exception e) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iSua:
                Intent intent = new Intent(QLQuanAn.this, UpQuanAn.class);
                intent.putExtra(key.key_LoaiCS, key.key_Sua);
                intent.putExtra(key.key_IDQA, dsQA_qlqa_adap.quananList.get(position).getIdQA());
                startActivity(intent);
                return true;
            case R.id.iQuanLy:
                int pos = position;
                Intent intent1 = new Intent(QLQuanAn.this, QLMonAn.class);
                intent1.putExtra(key.key_IDQA, dsQA_qlqa_adap.quananList.get(position).getIdQA());
                startActivity(intent1);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
                dsQA_qlqa_adap.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                dsQA_qlqa_adap.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iAdd:
                Intent intent = new Intent(QLQuanAn.this, UpQuanAn.class);
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
        recV_DSQuanAn = findViewById(R.id.recV_DSQuanAn);
        tool3_QLQuanAn = findViewById(R.id.tool3_QLQuanAn);
    }
}