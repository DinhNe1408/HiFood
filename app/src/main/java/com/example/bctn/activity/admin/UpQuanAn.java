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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpQuanAn extends AppCompatActivity {

    private Toolbar tool3_QLQuanAn_Up;
    private ImageView imgV_HinhQA_upqa;
    private Button btn_Folder_upqa, btn_Camera_upqa, btn_LamMoi_upqa, btn_XacNhan_upqa;
    private TextInputLayout txtL_TenQA_upqa, txtL_ViTri_upqa;
    private TextInputEditText editT_TenQA_upqa, editT_ViTri_upqa;
    private CheckBox checkB_KhoaQA_upqa;
    private String loai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_quan_an);
        AnhXa();

        tool3_QLQuanAn_Up.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_QLQuanAn_Up.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("");

        Intent intent = getIntent();
        loai = intent.getStringExtra("LoaiCS");
        if (loai == null)
            return;

        if (loai.equals(key.key_Them)) {
            txtV_toolbar_title.setText("Thêm quán ăn");
        } else {
            txtV_toolbar_title.setText("Chỉnh sửa quán ăn");
        }

        SuKien();
    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    private void SuKien() {
        btn_Camera_upqa.setOnClickListener(view -> ActivityCompat.requestPermissions(
                UpQuanAn.this,
                new String[]{Manifest.permission.CAMERA},
                key.REQUEST_CODE_CAMERA
        ));

        btn_Folder_upqa.setOnClickListener(view -> ActivityCompat.requestPermissions(
                UpQuanAn.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                key.REQUEST_CODE_FOLDER
        ));

        btn_LamMoi_upqa.setOnClickListener(view -> {
            onStart();
        });

        btn_XacNhan_upqa.setOnClickListener(view -> {

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
                    Toast.makeText(UpQuanAn.this, "Bạn không cho phép mở camera", Toast.LENGTH_LONG).show();
                }
                break;
            case key.REQUEST_CODE_FOLDER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, key.REQUEST_CODE_FOLDER);
                } else {
                    Toast.makeText(UpQuanAn.this, "Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == key.REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgV_HinhQA_upqa.setImageBitmap(bitmap);
        }

        if (requestCode == key.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgV_HinhQA_upqa.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        tool3_QLQuanAn_Up = findViewById(R.id.tool3_QLQuanAn_Up);
        imgV_HinhQA_upqa = findViewById(R.id.imgV_HinhQA_upqa);

        btn_Folder_upqa = findViewById(R.id.btn_Folder_upqa);
        btn_Camera_upqa = findViewById(R.id.btn_Camera_upqa);
        btn_LamMoi_upqa = findViewById(R.id.btn_LamMoi_upqa);
        btn_XacNhan_upqa = findViewById(R.id.btn_XacNhan_upqa);

        txtL_TenQA_upqa = findViewById(R.id.txtL_TenQA_upqa);
        txtL_ViTri_upqa = findViewById(R.id.txtL_ViTri_upqa);

        editT_TenQA_upqa = findViewById(R.id.editT_TenQA_upqa);
        editT_ViTri_upqa = findViewById(R.id.editT_ViTri_upqa);

        checkB_KhoaQA_upqa = findViewById(R.id.checkB_KhoaQA_upqa);
    }
}