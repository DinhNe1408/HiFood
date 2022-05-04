package com.example.bctn.activity.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.LayViTri;
import com.example.bctn.activity.quanan.ThongTinQA;
import com.example.bctn.adapter.ChonTKAdap;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;
import com.example.bctn.domain.taikhoan;
import com.example.bctn.domain.vitri;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UpQuanAn extends AppCompatActivity {

    private Toolbar tool3_QLQuanAn_Up;
    private LinearLayout iclu_get_img_upqa;
    private ImageView imgV_HinhQA_upqa;
    private Button btn_Folder_upqa, btn_Camera_upqa, btn_XacNhan_upqa;
    private TextInputLayout txtL_TenQA_upqa;
    private TextInputEditText editT_TenQA_upqa, editT_ViTri_upqa;
    private ImageButton imgB_LayViTri_upqa;
    private CheckBox checkB_KhoaQA_upqa;
    private String loai;
    private quanan mQuanan;
    private static TextView txtV_MaTK_upqa;
    private static TextView txtV_TenTK_upqa;
    private RelativeLayout relative1_upqa;
    static ChonTKAdap chonTKAdap;
    static Dialog dialog;
    private static taikhoan newTK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_quan_an);
        AnhXa();

        tool3_QLQuanAn_Up.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_QLQuanAn_Up.findViewById(R.id.txtV_toolbar_title);

        Intent intent = getIntent();
        loai = intent.getStringExtra(key.key_LoaiCS);
        if (loai == null)
            return;

        if (loai.equals(key.key_Them)) {
            mQuanan = new quanan();
            MyAppication.mViTri = null;
            txtV_toolbar_title.setText("Thêm quán ăn");
        } else {
            int IDQA = intent.getIntExtra(key.key_IDQA, -1);
            mQuanan = MyAppication.mDao.QA(IDQA);

            editT_TenQA_upqa.setText(mQuanan.getTenQA());
            editT_ViTri_upqa.setText(mQuanan.getVitriQA().getVitri());
            MyAppication.mViTri = mQuanan.getVitriQA();
            imgV_HinhQA_upqa.setImageBitmap(key.Byte2Bitmap(mQuanan.getHinhQA()));
            checkB_KhoaQA_upqa.setChecked(mQuanan.isKhoa());
            txtV_toolbar_title.setText("Chỉnh sửa quán ăn");
        }
        newTK = MyAppication.mDao.GetTK(mQuanan.getIDTK());
        if (newTK != null) {
            txtV_MaTK_upqa.setText("Mã tài khoản: " + newTK.getIdTK() + " - " + newTK.getSdtTK());
            txtV_TenTK_upqa.setText("Họ và tên: " + newTK.getTenTK());
        }
        SuKien();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (MyAppication.mViTri != null) {
            editT_ViTri_upqa.setText(MyAppication.mViTri.getVitri());
        }
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

        btn_XacNhan_upqa.setOnClickListener(view -> {
            if (editT_TenQA_upqa.getText() != null && editT_TenQA_upqa.getText().length() != 0) {
                if (editT_ViTri_upqa.getText() != null && editT_ViTri_upqa.getText().length() != 0) {

                    mQuanan.setTenQA(editT_TenQA_upqa.getText().toString().trim());
                    mQuanan.setKhoa(checkB_KhoaQA_upqa.isChecked());
                    mQuanan.setHinhQA(key.BitmapDrawable2Byte((BitmapDrawable) imgV_HinhQA_upqa.getDrawable()));

                    if (loai.equals(key.key_Them)) {
                        mQuanan.setIdQA(MyAppication.mDao.TaoIDQA());
                        MyAppication.mDao.TaoQA(mQuanan.getIdQA(), mQuanan.getTenQA(), newTK.getIdTK());
                        Toast.makeText(this, "Tạo quán ăn thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        MyAppication.mDao.CapNhatQA(mQuanan.getIdQA(), mQuanan.getTenQA(), mQuanan.isKhoa(), newTK.getIdTK());
                        Toast.makeText(this, "Cập nhật quán ăn thành công", Toast.LENGTH_SHORT).show();
                    }
                    MyAppication.mDao.CapNhatViTriQA(mQuanan.getIdQA(), MyAppication.mViTri.getVitri(), MyAppication.mViTri.getVido(), MyAppication.mViTri.getKinhdo());
                    MyAppication.mDao.CapNhatHinhQA(mQuanan.getIdQA(), mQuanan.getHinhQA());

                    MyAppication.mViTri = null;
                } else {
                    Toast.makeText(this, "Vui lòng chọn vị trí", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
            }
        });

        imgB_LayViTri_upqa.setOnClickListener(view -> {
            Intent intent = new Intent(UpQuanAn.this, LayViTri.class);
            startActivity(intent);
        });

        relative1_upqa.setOnClickListener(view -> {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_chon_tk);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            EditText editT_ChonTK = dialog.findViewById(R.id.editT_ChonTK);
            RecyclerView recV_DSChonTK = dialog.findViewById(R.id.recV_DSChonTK);

            editT_ChonTK.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    chonTKAdap.getFilter().filter(charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            List<taikhoan> taikhoanList = MyAppication.mDao.GetListTKQA();
            chonTKAdap = new ChonTKAdap(this, taikhoanList);
            recV_DSChonTK.setAdapter(chonTKAdap);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recV_DSChonTK.setLayoutManager(linearLayoutManager);
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recV_DSChonTK.addItemDecoration(itemDecoration);

            dialog.show();
        });
    }

    @SuppressLint("SetTextI18n")
    public static void dismiss() {
        newTK = MyAppication.mDao.GetTK(chonTKAdap.getIDTK());
        txtV_MaTK_upqa.setText("Mã tài khoản: " + newTK.getIdTK() + " - " + newTK.getSdtTK());
        txtV_TenTK_upqa.setText("Họ và tên: " + newTK.getTenTK());
        dialog.dismiss();
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

        iclu_get_img_upqa = findViewById(R.id.iclu_get_img_upqa);
        imgV_HinhQA_upqa = iclu_get_img_upqa.findViewById(R.id.imgV_Hinh);
        btn_Folder_upqa = iclu_get_img_upqa.findViewById(R.id.btn_Folder);
        btn_Camera_upqa = iclu_get_img_upqa.findViewById(R.id.btn_Camera);

        btn_XacNhan_upqa = findViewById(R.id.btn_XacNhan_upqa);

        txtL_TenQA_upqa = findViewById(R.id.txtL_TenQA_upqa);
        imgB_LayViTri_upqa = findViewById(R.id.imgB_LayViTri_upqa);

        editT_TenQA_upqa = findViewById(R.id.editT_TenQA_upqa);
        editT_ViTri_upqa = findViewById(R.id.editT_ViTri_upqa);

        checkB_KhoaQA_upqa = findViewById(R.id.checkB_KhoaQA_upqa);
        txtV_MaTK_upqa = findViewById(R.id.txtV_MaTK_upqa);
        txtV_TenTK_upqa = findViewById(R.id.txtV_TenTK_upqa);
        relative1_upqa = findViewById(R.id.relative1_upqa);
    }
}