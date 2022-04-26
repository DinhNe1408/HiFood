package com.example.bctn.activity.quanan;

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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.admin.UpQuanAn;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;
import com.example.bctn.domain.vitri;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ThongTinQA extends AppCompatActivity {

    private Toolbar tool3_ThongTinQA;
    private LinearLayout iclu_get_img_ttqa;
    private ImageView imgV_HinhQA_ttqa;
    private Button btn_Folder_ttqa, btn_Camera_ttqa, btn_XacNhan_ttqa;
    private TextInputLayout txtL_TenQA_ttqa, txtL_ViTri_ttqa;
    private TextInputEditText editT_TenQA_ttqa, editT_ViTri_ttqa;
    private quanan mQuanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_qa);
        AnhXa();
        tool3_ThongTinQA.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_ThongTinQA.findViewById(R.id.txtV_toolbar_title);

        Intent intent = getIntent();
        int IDQA = intent.getIntExtra(key.key_IDQA, -1);
        if (IDQA == -1)
            return;

        mQuanan = MyAppication.mDao.QA(IDQA);

        editT_TenQA_ttqa.setText(mQuanan.getTenQA());
        editT_ViTri_ttqa.setText(mQuanan.getVitriQA().getVitri());
        imgV_HinhQA_ttqa.setImageBitmap(key.Byte2Bitmap(mQuanan.getHinhQA()));
        txtV_toolbar_title.setText("Chỉnh sửa thông tin quán ăn");
        SuKien();
    }

    private void SuKien() {
        btn_Camera_ttqa.setOnClickListener(view -> ActivityCompat.requestPermissions(
                ThongTinQA.this,
                new String[]{Manifest.permission.CAMERA},
                key.REQUEST_CODE_CAMERA
        ));

        btn_Folder_ttqa.setOnClickListener(view -> ActivityCompat.requestPermissions(
                ThongTinQA.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                key.REQUEST_CODE_FOLDER
        ));

        btn_XacNhan_ttqa.setOnClickListener(view -> {
            if (editT_TenQA_ttqa.getText() != null && editT_ViTri_ttqa.getText() != null &&
                    editT_TenQA_ttqa.getText().length() != 0 && editT_ViTri_ttqa.getText().length() != 0) {

                mQuanan.setTenQA(editT_TenQA_ttqa.getText().toString().trim());
                mQuanan.setHinhQA(key.BitmapDrawable2Byte((BitmapDrawable) imgV_HinhQA_ttqa.getDrawable()));

                MyAppication.mDao.CapNhatQA(mQuanan.getIdQA(), mQuanan.getTenQA(), mQuanan.isKhoa());

                mQuanan.setVitriQA(new vitri(editT_ViTri_ttqa.getText().toString().trim(), 0.0, 0.0));
                MyAppication.mDao.CapNhatViTriQA(mQuanan.getIdQA(), mQuanan.getVitriQA().getVitri(), mQuanan.getVitriQA().getVido(), mQuanan.getVitriQA().getKinhdo());
                MyAppication.mDao.CapNhatHinhQA(mQuanan.getIdQA(), mQuanan.getHinhQA());

                Toast.makeText(this, "Cập nhật quán ăn thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case key.REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, key.REQUEST_CODE_CAMERA);
                } else {
                    Toast.makeText(ThongTinQA.this, "Bạn không cho phép mở camera", Toast.LENGTH_LONG).show();
                }
                break;
            case key.REQUEST_CODE_FOLDER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, key.REQUEST_CODE_FOLDER);
                } else {
                    Toast.makeText(ThongTinQA.this, "Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == key.REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgV_HinhQA_ttqa.setImageBitmap(bitmap);
        }

        if (requestCode == key.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgV_HinhQA_ttqa.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        tool3_ThongTinQA = findViewById(R.id.tool3_ThongTinQA);

        iclu_get_img_ttqa = findViewById(R.id.iclu_get_img_ttqa);
        imgV_HinhQA_ttqa = iclu_get_img_ttqa.findViewById(R.id.imgV_Hinh);
        btn_Folder_ttqa = iclu_get_img_ttqa.findViewById(R.id.btn_Folder);
        btn_Camera_ttqa = iclu_get_img_ttqa.findViewById(R.id.btn_Camera);

        btn_XacNhan_ttqa = findViewById(R.id.btn_XacNhan_ttqa);

        txtL_TenQA_ttqa = findViewById(R.id.txtL_TenQA_ttqa);
        txtL_ViTri_ttqa = findViewById(R.id.txtL_ViTri_ttqa);

        editT_TenQA_ttqa = findViewById(R.id.editT_TenQA_ttqa);
        editT_ViTri_ttqa = findViewById(R.id.editT_ViTri_ttqa);
    }
}