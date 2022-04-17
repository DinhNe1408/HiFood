package com.example.bctn.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.admin.UpTaiKhoan;
import com.example.bctn.domain.key;
import com.example.bctn.domain.vitri;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThongTinNguoiDung extends AppCompatActivity {

    private Toolbar tool3_ThongTinNguoiDung;
    private ImageView imgV_HinhTK_ttnd;
    private TextInputLayout txtL_SDT_ttnd;
    private Button btn_Folder_ttnd, btn_Camera_ttnd, btn_DoiMatKhau_ttnd, btn_XacNhan_ttnd;
    private TextInputEditText editT_SDT_ttnd, editT_Ten_ttnd, editT_ViTri_ttnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nguoi_dung);
        AnhXa();
        tool3_ThongTinNguoiDung.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_ThongTinNguoiDung.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Thông tin người dùng");

        SuKien();
    }

    private void SuKien() {
        btn_Camera_ttnd.setOnClickListener(view -> ActivityCompat.requestPermissions(
                ThongTinNguoiDung.this,
                new String[]{Manifest.permission.CAMERA},
                key.REQUEST_CODE_CAMERA
        ));

        btn_Folder_ttnd.setOnClickListener(view -> ActivityCompat.requestPermissions(
                ThongTinNguoiDung.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                key.REQUEST_CODE_FOLDER
        ));

        btn_DoiMatKhau_ttnd.setOnClickListener(view -> {
            Intent intent = new Intent(this, DoiMatKhau.class);
            startActivity(intent);
        });

        btn_XacNhan_ttnd.setOnClickListener(view -> {
            if (editT_SDT_ttnd.getText() != null && editT_Ten_ttnd.getText() != null
                    && editT_ViTri_ttnd.getText() != null &&
                    editT_SDT_ttnd.getText().length() != 0 && editT_Ten_ttnd.getText().length() != 0
                    && editT_ViTri_ttnd.getText().length() != 0) {

                MyAppication.mTaiKhoan.setSdtTK(editT_SDT_ttnd.getText().toString().trim());
                MyAppication.mTaiKhoan.setTenTK(editT_Ten_ttnd.getText().toString().trim());
                MyAppication.mTaiKhoan.setVitri(new vitri(editT_ViTri_ttnd.getText().toString().trim(), 0.0, 0.0));

                MyAppication.mDao.CapNhatTK(MyAppication.mTaiKhoan.getIdTK(), MyAppication.mTaiKhoan.getSdtTK(), MyAppication.mTaiKhoan.getMkTK(),
                        MyAppication.mTaiKhoan.getTenTK(), MyAppication.mTaiKhoan.getRole(), MyAppication.mTaiKhoan.isKhoa());

                MyAppication.mTaiKhoan.setHinhTK(key.BitmapDrawable2Byte((BitmapDrawable) imgV_HinhTK_ttnd.getDrawable()));

                MyAppication.mDao.CapNhatHinhTK(MyAppication.mTaiKhoan.getIdTK(), MyAppication.mTaiKhoan.getHinhTK());

                MyAppication.mDao.CapNhatViTriTK(MyAppication.mTaiKhoan.getIdTK(),
                        MyAppication.mTaiKhoan.getVitri().getVitri(), MyAppication.mTaiKhoan.getVitri().getVido(), MyAppication.mTaiKhoan.getVitri().getKinhdo());
                Toast.makeText(this, "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        txtL_SDT_ttnd.setEnabled(false);
        imgV_HinhTK_ttnd.setImageBitmap(key.Byte2Bitmap(MyAppication.mTaiKhoan.getHinhTK()));
        editT_SDT_ttnd.setText(MyAppication.mTaiKhoan.getSdtTK());
        editT_Ten_ttnd.setText(MyAppication.mTaiKhoan.getTenTK());
        editT_ViTri_ttnd.setText(MyAppication.mTaiKhoan.getVitri().getVitri());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case key.REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, key.REQUEST_CODE_CAMERA);
                } else {
                    Toast.makeText(ThongTinNguoiDung.this, "Bạn không cho phép mở camera", Toast.LENGTH_LONG).show();
                }
                break;
            case key.REQUEST_CODE_FOLDER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, key.REQUEST_CODE_FOLDER);
                } else {
                    Toast.makeText(ThongTinNguoiDung.this, "Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == key.REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgV_HinhTK_ttnd.setImageBitmap(bitmap);
        }


        if (requestCode == key.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgV_HinhTK_ttnd.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        tool3_ThongTinNguoiDung = findViewById(R.id.tool3_ThongTinNguoiDung);

        imgV_HinhTK_ttnd = findViewById(R.id.imgV_HinhTK_ttnd);
        txtL_SDT_ttnd = findViewById(R.id.txtL_SDT_ttnd);

        btn_Folder_ttnd = findViewById(R.id.btn_Folder_ttnd);
        btn_Camera_ttnd = findViewById(R.id.btn_Camera_ttnd);
        btn_DoiMatKhau_ttnd = findViewById(R.id.btn_DoiMatKhau_ttnd);
        btn_XacNhan_ttnd = findViewById(R.id.btn_XacNhan_ttnd);

        editT_SDT_ttnd = findViewById(R.id.editT_SDT_ttnd);
        editT_Ten_ttnd = findViewById(R.id.editT_Ten_ttnd);
        editT_ViTri_ttnd = findViewById(R.id.editT_ViTri_ttnd);
    }
}