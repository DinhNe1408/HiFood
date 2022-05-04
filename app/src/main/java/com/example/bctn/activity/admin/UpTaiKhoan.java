package com.example.bctn.activity.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.DangKy2Act;
import com.example.bctn.activity.LayViTri;
import com.example.bctn.activity.quanan.ThongTinQA;
import com.example.bctn.adapter.QuyenAdapter;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;
import com.example.bctn.domain.taikhoan;
import com.example.bctn.domain.vitri;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UpTaiKhoan extends AppCompatActivity {

    private ImageView imgV_HinhTK_uptk;
    private Button btn_Folder_uptk, btn_Camera_uptk, btn_XacNhan_uptk;
    private CheckBox checkB_KhoaTK_uptk;
    private ImageButton imgB_LayViTri_uptk;
    private TextInputLayout txtL_SDT_uptk;
    private TextInputEditText editT_SDT_uptk, editT_MatKhau_uptk, editT_Ten_uptk, editT_ViTri_uptk;
    private taikhoan mtaikhoan;
    private String loai;
    private Toolbar tool3_QLTaiKhoan_Up;
    private Spinner spinner_Quyen_uptk;
    private QuyenAdapter quyenAdapter;
    private List<String> strQuyen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_tai_khoan);
        AnhXa();

        tool3_QLTaiKhoan_Up.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_QLTaiKhoan_Up.findViewById(R.id.txtV_toolbar_title);

        strQuyen.add("Người dùng");
        strQuyen.add("Quán ăn");

        quyenAdapter = new QuyenAdapter(this, R.layout.spinner_select, strQuyen);
        spinner_Quyen_uptk.setAdapter(quyenAdapter);

        Intent intent = getIntent();
        loai = intent.getStringExtra(key.key_LoaiCS);
        if (loai == null)
            return;

        if (loai.equals(key.key_Them)) {
            txtV_toolbar_title.setText("Thêm tài khoản");
            mtaikhoan = new taikhoan();
            MyAppication.mViTri = null;
        } else {
            txtV_toolbar_title.setText("Chỉnh sửa tài khoản");
            int IDTK = intent.getIntExtra(key.key_IDTK, -1);
            if (IDTK == -1)
                return;

            txtL_SDT_uptk.setEnabled(false);

            mtaikhoan = MyAppication.mDao.GetTK(IDTK);
            if (mtaikhoan.getRole().equals("user")) {
                spinner_Quyen_uptk.setSelection(0);
            } else if (mtaikhoan.getRole().equals("dinner")) {
                spinner_Quyen_uptk.setSelection(1);
            }
            setText(key.Byte2Bitmap(mtaikhoan.getHinhTK()), mtaikhoan.getSdtTK(),
                    mtaikhoan.getMkTK(), mtaikhoan.getTenTK(), mtaikhoan.getVitri().getVitri(), mtaikhoan.isKhoa());

            if (mtaikhoan.getVitri().getVitri() != null) {
                MyAppication.mViTri = mtaikhoan.getVitri();
            } else {
                MyAppication.mViTri = null;
            }
        }

        SuKien();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (MyAppication.mViTri != null) {
            editT_ViTri_uptk.setText(MyAppication.mViTri.getVitri());
        }
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

        btn_XacNhan_uptk.setOnClickListener(view -> {
            if (editT_SDT_uptk.getText() != null && editT_MatKhau_uptk.getText() != null
                    && editT_Ten_uptk.getText() != null
                    && editT_SDT_uptk.getText().length() != 0 && editT_MatKhau_uptk.getText().length() != 0
                    && editT_Ten_uptk.getText().length() != 0) {
                if (editT_ViTri_uptk.getText() != null && editT_ViTri_uptk.getText().length() != 0) {
                    mtaikhoan.setSdtTK(editT_SDT_uptk.getText().toString().trim());
                    mtaikhoan.setMkTK(editT_MatKhau_uptk.getText().toString().trim());

                    if (loai.equals(key.key_Them)) {
                        if (key.isSDT(mtaikhoan.getSdtTK())) {
                            if (!MyAppication.mDao.isExistTK(mtaikhoan.getSdtTK())) {
                                if (key.isMk(mtaikhoan.getMkTK())) {

                                    mtaikhoan.setTenTK(editT_Ten_uptk.getText().toString().trim());
                                    mtaikhoan.setKhoa(checkB_KhoaTK_uptk.isChecked());
                                    mtaikhoan.setHinhTK(key.BitmapDrawable2Byte((BitmapDrawable) imgV_HinhTK_uptk.getDrawable()));

                                    MyAppication.mDao.TaoTK(mtaikhoan.getSdtTK(), mtaikhoan.getMkTK(), mtaikhoan.getTenTK(), mtaikhoan.getRole());
                                    int IDTK = MyAppication.mDao.IDTK(mtaikhoan.getSdtTK(), mtaikhoan.getMkTK());
                                    mtaikhoan.setIdTK(IDTK);

                                    MyAppication.mDao.CapNhatHinhTK(mtaikhoan.getIdTK(), mtaikhoan.getHinhTK());
                                    MyAppication.mDao.CapNhatViTriTK(mtaikhoan.getIdTK(), MyAppication.mViTri.getVitri(), MyAppication.mViTri.getVido(), MyAppication.mViTri.getKinhdo());
                                    Toast.makeText(this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(this, "Mật khẩu phải dài hơn 8 ký tự, có chữ in hoa, chữ thường và số", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "Số điện thoại đã được sử dụng", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Vui lòng nhập đúng định dạng Số điện thoại ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (key.isMk(mtaikhoan.getMkTK())) {
                            mtaikhoan.setTenTK(editT_Ten_uptk.getText().toString().trim());
                            mtaikhoan.setKhoa(checkB_KhoaTK_uptk.isChecked());
                            mtaikhoan.setHinhTK(key.BitmapDrawable2Byte((BitmapDrawable) imgV_HinhTK_uptk.getDrawable()));

                            MyAppication.mDao.CapNhatTK(mtaikhoan.getIdTK(), mtaikhoan.getSdtTK(), mtaikhoan.getMkTK(), mtaikhoan.getTenTK(), mtaikhoan.getRole(), mtaikhoan.isKhoa());
                            MyAppication.mDao.CapNhatHinhTK(mtaikhoan.getIdTK(), mtaikhoan.getHinhTK());
                            Log.e("Quyen", mtaikhoan.getRole());
                            MyAppication.mDao.CapNhatViTriTK(mtaikhoan.getIdTK(), MyAppication.mViTri.getVitri(), MyAppication.mViTri.getVido(), MyAppication.mViTri.getKinhdo());
                            Toast.makeText(this, "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Mật khẩu phải dài hơn 8 ký tự, có chữ in hoa, chữ thường và số", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(this, "Vui lòng chọn vị trí", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
            }

        });

        imgB_LayViTri_uptk.setOnClickListener(view -> {
            Intent intent = new Intent(UpTaiKhoan.this, LayViTri.class);
            startActivity(intent);
        });

        spinner_Quyen_uptk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (quyenAdapter.getItem(i).equals("Người dùng")) {
                    mtaikhoan.setRole("user");
                } else if (quyenAdapter.getItem(i).equals("Quán ăn")) {
                    mtaikhoan.setRole("dinner");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        MyAppication.mViTri = null;
        super.onBackPressed();
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
        btn_XacNhan_uptk = findViewById(R.id.btn_XacNhan_uptk);

        checkB_KhoaTK_uptk = findViewById(R.id.checkB_KhoaTK_uptk);

        txtL_SDT_uptk = findViewById(R.id.txtL_SDT_uptk);
        imgB_LayViTri_uptk = findViewById(R.id.imgB_LayViTri_uptk);

        editT_SDT_uptk = findViewById(R.id.editT_SDT_uptk);
        editT_MatKhau_uptk = findViewById(R.id.editT_MatKhau_uptk);
        editT_Ten_uptk = findViewById(R.id.editT_Ten_uptk);
        editT_ViTri_uptk = findViewById(R.id.editT_ViTri_uptk);
        spinner_Quyen_uptk = findViewById(R.id.spinner_Quyen_uptk);
    }
}