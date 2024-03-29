package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.DAO;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKy1Act extends AppCompatActivity {

    private TextInputEditText editT_HoTen_dk1, editT_MK_dk1, editT_NLMK_dk1;
    private TextView txtV_SDT_dk1;
    private Button btn_DK1_dk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_ky1_act);

        AnhXa();
        Intent intent = getIntent();
        String sdt = intent.getStringExtra("sdt_dk2_2_dk1");


        txtV_SDT_dk1.setText(sdt);

        btn_DK1_dk.setOnClickListener(view -> {
            if (editT_HoTen_dk1.length() > 0 && editT_MK_dk1.length() > 0 && editT_NLMK_dk1.length() > 0
                    && editT_HoTen_dk1.getText() != null && editT_MK_dk1.getText() != null && editT_NLMK_dk1.getText() != null) {
                String mk = editT_MK_dk1.getText().toString();
                String nlmk = editT_NLMK_dk1.getText().toString();

                if (key.isMk(mk)) {
                    if (mk.equals(nlmk)) {
                        MyAppication.mDao.TaoTK(sdt, mk, editT_HoTen_dk1.getText().toString(), "user");
                        int IDTK = MyAppication.mDao.IDTK(sdt, mk);

                        MyAppication.mDao.CapNhatHinhTK(IDTK, key.BitmapDrawable2Byte((BitmapDrawable) getResources().getDrawable(R.drawable.khongchu)));
                        Intent mIntent = new Intent(DangKy1Act.this, DangNhapAct.class);
                        startActivity(mIntent);
                    } else {
                        Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Mật khẩu phải dài hơn 8 ký tự, có chữ in hoa, chữ thường và số", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Vui lòng điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        btn_DK1_dk = findViewById(R.id.btn_DK1_dk);

        editT_HoTen_dk1 = findViewById(R.id.editT_HoTen_dk1);
        editT_MK_dk1 = findViewById(R.id.editT_MK_dk1);
        editT_NLMK_dk1 = findViewById(R.id.editT_NLMK_dk1);

        txtV_SDT_dk1 = findViewById(R.id.txtV_SDT_dk1);
    }
}