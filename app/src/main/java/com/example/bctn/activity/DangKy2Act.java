package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.DAO;
import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.google.android.material.textfield.TextInputEditText;

public class DangKy2Act extends AppCompatActivity {

    private TextView txtV_CoTK_dk2;
    private Button btn_DK2_dk;
    private TextInputEditText editT_SDT_dk2;
    private DAO mDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky2_act);
        AnhXa();

        txtV_CoTK_dk2.setOnClickListener(view -> onBackPressed());

        mDao = new DAO(this);

        btn_DK2_dk.setOnClickListener(view -> {

            if (editT_SDT_dk2.length() > 0 && editT_SDT_dk2.getText() != null){
                String sdt = editT_SDT_dk2.getText().toString();
                if (key.isSDT(sdt)){
                    if ( !mDao.isExistTK(sdt)){

                        Intent mIntent = new Intent(DangKy2Act.this, DangKy1Act.class);
                        mIntent.putExtra("sdt_dk2_2_dk1", sdt);

                        startActivity(mIntent);

                    } else {
                        Toast.makeText(DangKy2Act.this, "Số điện thoại này đã được sử dụng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DangKy2Act.this, "Vui lòng nhập đúng định dạng Số điện thoại ", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(DangKy2Act.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void AnhXa() {
        txtV_CoTK_dk2 = findViewById(R.id.txtV_CoTK_dk2);
        btn_DK2_dk = findViewById(R.id.btn_DK2_dk);
        editT_SDT_dk2 = findViewById(R.id.editT_SDT_dk2);
    }
}