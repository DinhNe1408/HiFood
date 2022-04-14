package com.example.bctn.activity.admin;

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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.example.bctn.domain.taikhoan;
import com.example.bctn.domain.vitri;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpTaiKhoan extends AppCompatActivity {

    private ImageView imgV_HinhTK_uptk;
    private Button btn_Folder_uptk, btn_Camera_uptk, btn_Huy_uptk, btn_XacNhan_uptk;
    private CheckBox checkB_KhoaTK_uptk;
    private TextInputLayout txtL_SDT_uptk, txtL_MatKhau_uptk, txtL_Ten_uptk, txtL_ViTri_uptk;
    private TextInputEditText editT_SDT_uptk, editT_MatKhau_uptk, editT_Ten_uptk, editT_ViTri_uptk;
    private taikhoan mtaikhoan;
    private String loai;
    private Toolbar tool3_QLTaiKhoan_Up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_tai_khoan);
        AnhXa();

        tool3_QLTaiKhoan_Up.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_QLTaiKhoan_Up.findViewById(R.id.txtV_toolbar_title);

        Intent intent = getIntent();
        loai = intent.getStringExtra("LoaiCS");
        if (loai == null)
            return;

        if (loai.equals(key.key_Them)) {
            txtV_toolbar_title.setText("Thêm tài khoản");
            mtaikhoan = new taikhoan();
        } else {
            txtV_toolbar_title.setText("Chỉnh sửa tài khoản");
            int IDTK = intent.getIntExtra(key.key_IDTK, -1);
            if (IDTK == -1)
                return;

            txtL_SDT_uptk.setEnabled(false);
            mtaikhoan = MyAppication.mDao.GetTK(IDTK);
            setText(key.Byte2Bitmap(mtaikhoan.getHinhTK()), mtaikhoan.getSdtTK(),
                    mtaikhoan.getMkTK(), mtaikhoan.getTenTK(), mtaikhoan.getVitri().getVitri(), mtaikhoan.isKhoa());
        }


        SuKien();
        //setData();


    }

    private void SuKien() {
        btn_Camera_uptk.setOnClickListener(view -> ActivityCompat.requestPermissions(
                UpTaiKhoan.this,
                new String[]{Manifest.permission.CAMERA},
                key.REQUEST_CODE_CAMERA
        ));

        btn_Folder_uptk.setOnClickListener(view -> ActivityCompat.requestPermissions(
                UpTaiKhoan.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                key.REQUEST_CODE_FOLDER
        ));

        btn_Huy_uptk.setOnClickListener(view -> {
            if (loai.equals(key.key_Them)) {
                Bitmap Icon = BitmapFactory.decodeResource(getResources(), R.drawable.khongchu);
                setText(Icon, "", "", "", "", false);

            } else {
                setText(key.Byte2Bitmap(mtaikhoan.getHinhTK()), mtaikhoan.getSdtTK(),
                        mtaikhoan.getMkTK(), mtaikhoan.getTenTK(), mtaikhoan.getVitri().getVitri(), mtaikhoan.isKhoa());

            }
        });

        btn_XacNhan_uptk.setOnClickListener(view -> {

            if (loai.equals(key.key_Them)) {
                // Thêm thông tin cá nhân
                if (editT_SDT_uptk.getText() != null && editT_MatKhau_uptk.getText() != null
                        && editT_Ten_uptk.getText() != null && editT_ViTri_uptk.getText() != null &&
                        editT_SDT_uptk.getText().length() != 0 && editT_MatKhau_uptk.getText().length() != 0
                        && editT_Ten_uptk.getText().length() != 0 && editT_ViTri_uptk.getText().length() != 0) {
                    String SDT = editT_SDT_uptk.getText().toString().trim();

                    if (!MyAppication.mDao.isExistTK(SDT)) {
                        String MK = editT_MatKhau_uptk.getText().toString().trim();
                        String Ten = editT_Ten_uptk.getText().toString().trim();
                        String ViTri = editT_ViTri_uptk.getText().toString().trim();

                        MyAppication.mDao.TaoTK(SDT, MK, Ten, "user");

                        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgV_HinhTK_uptk.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);

                        byte[] hinhAnh = byteArray.toByteArray();
                        int IDTK = MyAppication.mDao.IDTK(SDT, MK);
                        MyAppication.mDao.CapNhatHinhTK(IDTK, hinhAnh);

                        MyAppication.mDao.CapNhatViTriTK(IDTK, ViTri, 0.0, 0.0);
                        Toast.makeText(this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Số điện thoại đã được sử dụng", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
                }

            } else {
                // Sửa thông tin cá nhân
                if (editT_SDT_uptk.getText() != null && editT_MatKhau_uptk.getText() != null
                        && editT_Ten_uptk.getText() != null && editT_ViTri_uptk.getText() != null &&
                        editT_SDT_uptk.getText().length() != 0 && editT_MatKhau_uptk.getText().length() != 0
                        && editT_Ten_uptk.getText().length() != 0 && editT_ViTri_uptk.getText().length() != 0) {

                    mtaikhoan.setSdtTK(editT_SDT_uptk.getText().toString().trim());
                    mtaikhoan.setMkTK(editT_MatKhau_uptk.getText().toString().trim());
                    mtaikhoan.setTenTK(editT_Ten_uptk.getText().toString().trim());
                    mtaikhoan.setVitri(new vitri(editT_ViTri_uptk.getText().toString().trim(), 0.0, 0.0));

                    mtaikhoan.setRole("user");
                    mtaikhoan.setKhoa(checkB_KhoaTK_uptk.isChecked());

                    MyAppication.mDao.CapNhatTK(mtaikhoan.getIdTK(), mtaikhoan.getSdtTK(), mtaikhoan.getMkTK(), mtaikhoan.getTenTK(), mtaikhoan.getRole(), mtaikhoan.isKhoa());

                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imgV_HinhTK_uptk.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);

                    byte[] hinhAnh = byteArray.toByteArray();
                    mtaikhoan.setHinhTK(hinhAnh);

                    int IDTK = MyAppication.mDao.IDTK(mtaikhoan.getSdtTK(), mtaikhoan.getMkTK());
                    MyAppication.mDao.CapNhatHinhTK(IDTK, hinhAnh);

                    MyAppication.mDao.CapNhatViTriTK(IDTK, mtaikhoan.getVitri().getVitri(), mtaikhoan.getVitri().getVido(), mtaikhoan.getVitri().getKinhdo());
                    Toast.makeText(this, "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setText(Bitmap hinhTK, String sdtTK, String mk, String tenTK, String vitri, boolean isKhoa) {
        imgV_HinhTK_uptk.setImageBitmap(hinhTK);
        editT_SDT_uptk.setText(sdtTK);
        editT_MatKhau_uptk.setText(mk);
        editT_Ten_uptk.setText(tenTK);
        editT_ViTri_uptk.setText(vitri);
        checkB_KhoaTK_uptk.setChecked(isKhoa);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case key.REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, key.REQUEST_CODE_CAMERA);
                } else {
                    Toast.makeText(UpTaiKhoan.this, "Bạn không cho phép mở camera", Toast.LENGTH_LONG).show();
                }
                break;
            case key.REQUEST_CODE_FOLDER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, key.REQUEST_CODE_FOLDER);
                } else {
                    Toast.makeText(UpTaiKhoan.this, "Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == key.REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgV_HinhTK_uptk.setImageBitmap(bitmap);
        }

        if (requestCode == key.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgV_HinhTK_uptk.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        imgV_HinhTK_uptk = findViewById(R.id.imgV_HinhTK_uptk);
        tool3_QLTaiKhoan_Up = findViewById(R.id.tool3_QLTaiKhoan_Up);

        btn_Folder_uptk = findViewById(R.id.btn_Folder_uptk);
        btn_Camera_uptk = findViewById(R.id.btn_Camera_uptk);
        btn_Huy_uptk = findViewById(R.id.btn_Huy_uptk);
        btn_XacNhan_uptk = findViewById(R.id.btn_XacNhan_uptk);

        checkB_KhoaTK_uptk = findViewById(R.id.checkB_KhoaTK_uptk);

        txtL_SDT_uptk = findViewById(R.id.txtL_SDT_uptk);
        txtL_MatKhau_uptk = findViewById(R.id.txtL_MatKhau_uptk);
        txtL_Ten_uptk = findViewById(R.id.txtL_Ten_uptk);
        txtL_ViTri_uptk = findViewById(R.id.txtL_ViTri_uptk);

        editT_SDT_uptk = findViewById(R.id.editT_SDT_uptk);
        editT_MatKhau_uptk = findViewById(R.id.editT_MatKhau_uptk);
        editT_Ten_uptk = findViewById(R.id.editT_Ten_uptk);
        editT_ViTri_uptk = findViewById(R.id.editT_ViTri_uptk);
    }
}