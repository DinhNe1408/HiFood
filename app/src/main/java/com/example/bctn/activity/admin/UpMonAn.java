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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.example.bctn.domain.monan;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpMonAn extends AppCompatActivity {

    private Toolbar tool3_UpMonAn;
    private LinearLayout iclu_get_img_upma;
    private Button btn_XacNhan_upma, btn_Folder, btn_Camera;
    private TextInputEditText editT_TenMA_upma, editT_GiaMa_upma;
    private ImageView imgV_Hinh;
    private CheckBox checkB_Khoa_upma;
    String loai;
    monan mMonan;
    int IDQA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_mon_an);
        AnhXa();

        tool3_UpMonAn.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_UpMonAn.findViewById(R.id.txtV_toolbar_title);


        SuKien();
        Intent intent = getIntent();
        loai = intent.getStringExtra(key.key_LoaiCS);
        if (loai == null)
            return;
        IDQA = intent.getIntExtra(key.key_IDQA, -1);

        if (loai.equals(key.key_Them)) {
            mMonan = new monan();
            txtV_toolbar_title.setText("Thêm món ăn");
        } else {
            int IDMA = intent.getIntExtra(key.key_IDMA, -1);
            mMonan = MyAppication.mDao.MA(IDMA);
            txtV_toolbar_title.setText("Chỉnh sửa món ăn");
            editT_TenMA_upma.setText(mMonan.getTenMA());
            editT_GiaMa_upma.setText(String.valueOf((int) mMonan.getGiaMA()));
            checkB_Khoa_upma.setChecked(mMonan.isKhoa());
            imgV_Hinh.setImageBitmap(key.Byte2Bitmap(mMonan.getHinhMA()));
        }
    }

    private void SuKien() {
        btn_Camera.setOnClickListener(view -> ActivityCompat.requestPermissions(
                UpMonAn.this,
                new String[]{Manifest.permission.CAMERA},
                key.REQUEST_CODE_CAMERA
        ));

        btn_Folder.setOnClickListener(view -> ActivityCompat.requestPermissions(
                UpMonAn.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                key.REQUEST_CODE_FOLDER
        ));

        btn_XacNhan_upma.setOnClickListener(view -> {
            if (editT_TenMA_upma.getText() != null && editT_GiaMa_upma.getText() != null &&
                    editT_TenMA_upma.getText().length() != 0 && editT_GiaMa_upma.getText().length() != 0) {

                mMonan.setTenMA(editT_TenMA_upma.getText().toString().trim());
                mMonan.setGiaMA(Double.parseDouble(editT_GiaMa_upma.getText().toString()));
                mMonan.setHinhMA(key.BitmapDrawable2Byte((BitmapDrawable) imgV_Hinh.getDrawable()));
                mMonan.setKhoa(checkB_Khoa_upma.isChecked());

                if (loai.equals(key.key_Them)) {
                    mMonan.setIdMA(MyAppication.mDao.TaoIDMA());

                    MyAppication.mDao.TaoMA(IDQA, mMonan.getIdMA(), mMonan.getTenMA(), mMonan.getGiaMA());
                    Toast.makeText(this, "Tạo món ăn thành công", Toast.LENGTH_SHORT).show();
                } else {
                    MyAppication.mDao.CapNhatMA(mMonan.getIdMA(), mMonan.getTenMA(), mMonan.getGiaMA(), mMonan.isKhoa());
                    Toast.makeText(this, "Cập nhật món ăn thành công", Toast.LENGTH_SHORT).show();
                }
                MyAppication.mDao.CapNhatHinhMA(mMonan.getIdMA(), mMonan.getHinhMA());
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
                    Toast.makeText(UpMonAn.this, "Bạn không cho phép mở camera", Toast.LENGTH_LONG).show();
                }
                break;
            case key.REQUEST_CODE_FOLDER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, key.REQUEST_CODE_FOLDER);
                } else {
                    Toast.makeText(UpMonAn.this, "Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == key.REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgV_Hinh.setImageBitmap(bitmap);
        }

        if (requestCode == key.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgV_Hinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        tool3_UpMonAn = findViewById(R.id.tool3_UpMonAn);

        iclu_get_img_upma = findViewById(R.id.iclu_get_img_upma);
        btn_Folder = iclu_get_img_upma.findViewById(R.id.btn_Folder);
        btn_Camera = iclu_get_img_upma.findViewById(R.id.btn_Camera);
        imgV_Hinh = iclu_get_img_upma.findViewById(R.id.imgV_Hinh);

        btn_XacNhan_upma = findViewById(R.id.btn_XacNhan_upma);

        editT_TenMA_upma = findViewById(R.id.editT_TenMA_upma);
        editT_GiaMa_upma = findViewById(R.id.editT_GiaMa_upma);

        checkB_Khoa_upma = findViewById(R.id.checkB_Khoa_upma);
    }
}