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

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsMA_qlma_Adap;
import com.example.bctn.domain.key;
import com.example.bctn.domain.monan;

import java.util.List;

public class QLMonAn extends AppCompatActivity {

    private Toolbar tool3_QLMonAn;
    private RecyclerView recV_DSMonAn;
    private DsMA_qlma_Adap dsMAqlmaAdap;
    private List<monan> monanList;
    private int IDQA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlmon_an);
        AnhXa();

        setSupportActionBar(tool3_QLMonAn);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        tool3_QLMonAn.setTitle("Quản lý quán ăn");
        tool3_QLMonAn.setNavigationOnClickListener(view -> onBackPressed());
        Intent intent = getIntent();
        IDQA = intent.getIntExtra(key.key_IDQA, -1);
        if (IDQA == -1)
            return;

        registerForContextMenu(recV_DSMonAn);
    }

    @Override
    protected void onStart() {
        monanList = MyAppication.mDao.ListMA(IDQA);
        dsMAqlmaAdap = new DsMA_qlma_Adap(this, monanList, IDQA);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recV_DSMonAn.setAdapter(dsMAqlmaAdap);
        recV_DSMonAn.setLayoutManager(linearLayoutManager);
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_ql, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView iTimkiem = (SearchView) menu.findItem(R.id.iTimkiem).getActionView();
        iTimkiem.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        iTimkiem.setMaxWidth(Integer.MAX_VALUE);

        iTimkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                dsMAqlmaAdap.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                dsMAqlmaAdap.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iAdd:
                Intent intent = new Intent(QLMonAn.this, UpMonAn.class);
                intent.putExtra(key.key_LoaiCS, key.key_Them);
                intent.putExtra(key.key_IDQA, IDQA);
                startActivity(intent);
                return true;
            case R.id.iTimkiem:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void AnhXa() {
        tool3_QLMonAn = findViewById(R.id.tool3_QLMonAn);
        recV_DSMonAn = findViewById(R.id.recV_DSMonAn);
    }
}