package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.danhgia;
import com.example.bctn.domain.key;

public class DanhGia extends AppCompatActivity {

    private Toolbar tool3_DanhGia;
    private Button btn_GuiDG;
    private EditText editT_NoiDungDG;
    private RatingBar rate3_SaoDG;
    int IDDH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);

        AnhXa();

        Intent intent = getIntent();
        IDDH = intent.getIntExtra(key.key_IDDH, -1);
        int danhgia = intent.getIntExtra("DanhGia", -1);
        if (IDDH == -1 || danhgia == -1)
            return;


        tool3_DanhGia.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_DanhGia.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Đánh giá");

        danhgia danhGia = new danhgia();
        if (danhgia == 1){
            danhGia = MyAppication.mDao.DG(IDDH);
            editT_NoiDungDG.setEnabled(false);
            rate3_SaoDG.setEnabled(false);
            rate3_SaoDG.setRating((float) danhGia.getSaoDG());
            editT_NoiDungDG.setText(danhGia.getNoiDungDG());
            btn_GuiDG.setVisibility(View.INVISIBLE);
        }


        SuKien();
    }

    private void SuKien() {
        btn_GuiDG.setOnClickListener(view -> {
            if (editT_NoiDungDG.getText().toString().length() > 0 && rate3_SaoDG.getRating() > 0) {
                MyAppication.mDao.TaoDG(IDDH, editT_NoiDungDG.getText().toString(), rate3_SaoDG.getRating());
                Toast.makeText(this, "Cảm ơn bạn đã đánh giá", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

        });
    }

    private void AnhXa() {
        tool3_DanhGia = findViewById(R.id.tool3_DanhGia);
        btn_GuiDG = findViewById(R.id.btn_GuiDG);

        editT_NoiDungDG = findViewById(R.id.editT_NoiDungDG);
        rate3_SaoDG = findViewById(R.id.rate3_SaoDG);
    }
}