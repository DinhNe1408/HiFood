package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bctn.AlarmReceiver;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsMA_tt_Adap;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;
import com.example.bctn.fragment.QuanAn.QAThucDonFrag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ThanhToanAct extends AppCompatActivity {

    private RecyclerView recV_DsMA_tt;// recV_Menu_tt;
    private Toolbar tool3_ThanhToan;
    private Button btn_DatHang_tt;
    private TextView txtV_TenTK_tt, txtV_DiaChiNhan_tt, txtV_DoiDiaChiNhan_tt, txtV_tongTienMA_tt, phiVC_tt, tongTien_tt, txtV_ThoiGianNhan_tt, txtV_tienGiam_tt;
    private donhang mDonhang;
    TextView txtV_toolbar_title;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    static Calendar betime, aftime;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        AnhXa();
        betime = Calendar.getInstance();
        aftime = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent1 = new Intent(this, AlarmReceiver.class);

        Intent intent = getIntent();
        int IDDH = intent.getIntExtra(key.key_IDDH, -1);
        if (IDDH == -1)
            return;

        mDonhang = MyAppication.mDao.DH(IDDH, key.key_dh_Nhap);

        mDonhang.setTenNN(MyAppication.mTaiKhoan.getTenTK());
        mDonhang.setSDTNN(MyAppication.mTaiKhoan.getSdtTK());
        mDonhang.setPhiVC(key.CheckPhiVC((int) QuanAnAct.quanan.getKhcachQA()));
        mDonhang.setTienGiam(key.CheckGiam(mDonhang.getTongTienMA()));
        Log.e("đạkl", "" + mDonhang.getTongTienMAMap());
        mDonhang.setTongDH(mDonhang.getTongTienMAMap() + mDonhang.getPhiVC() - mDonhang.getTienGiam());
        mDonhang.setVitriDH(MyAppication.mTaiKhoan.getCurVitri().getVitri());

        setData();
//        betime = Calendar.getInstance();
//        Calendar aftime = betime;
//        int x = aftime.getTime().getMinutes() % 5;
//        aftime.add(Calendar.SECOND, 30);
//        //aftime.add(Calendar.MINUTE, 25 - x);
//        txtV_ThoiGianNhan_tt.setText("Thời gian giao: " + key.DateTimeFormat(aftime.getTime()));

        TinhTGGiaovaPhiVC(MyAppication.mTaiKhoan.getCurVitri().getKinhdo(), MyAppication.mTaiKhoan.getCurVitri().getVido());
        btn_DatHang_tt.setOnClickListener(view -> {

            MyAppication.mDao.CapNhatDH(IDDH, mDonhang.getTenNN(), mDonhang.getSDTNN(),
                    mDonhang.getTongTienMAMap(), mDonhang.getPhiVC(), mDonhang.getTienGiam(), mDonhang.getTongDH(),
                    mDonhang.getVitriDH(), key.DateFormatSQL(betime.getTime()), key.DateFormatSQL(aftime.getTime()), key.key_dh_DangGiao);

            Log.e("ThoiGian", key.DateFormatSQL(aftime.getTime()) + " == " + key.DateFormatSQL(betime.getTime()));

            pendingIntent = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.set(AlarmManager.RTC_WAKEUP, aftime.getTimeInMillis(), pendingIntent);

            QAThucDonFrag.mDonhang.newCtdhMap();
            Toast.makeText(this, "Đặt hàng thành công!!!", Toast.LENGTH_SHORT).show();
            Intent mIntent = new Intent(ThanhToanAct.this, TheoDoiDonHang.class);
            mIntent.putExtra(key.key_IDDH, IDDH);
            mIntent.putExtra(key.key_TTGiao, key.key_dh_DangGiao);
            startActivity(mIntent);
        });

        txtV_DoiDiaChiNhan_tt.setOnClickListener(view -> {
            openDialog();
        });
    }

    @SuppressLint("SetTextI18n")
    private void setThongTinNN() {
        txtV_TenTK_tt.setText(mDonhang.getTenNN() + " - " + mDonhang.getSDTNN());
        txtV_DiaChiNhan_tt.setText(mDonhang.getVitriDH());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (MyAppication.mViTri != null) {
            mDonhang.setVitriDH(MyAppication.mViTri.getVitri());
            TinhTGGiaovaPhiVC(MyAppication.mViTri.getKinhdo(), MyAppication.mViTri.getVido());
            Log.e("Khong null", MyAppication.mViTri.getVitri() + " = " + MyAppication.mViTri.getVido() + " = " + MyAppication.mViTri.getKinhdo());
        } else {
            Log.e("Null", "");
        }
        setThongTinNN();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyAppication.mViTri = null;
    }

    private void setData() {

        tool3_ThanhToan.setNavigationOnClickListener(view -> onBackPressed());
        txtV_toolbar_title = tool3_ThanhToan.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Đặt hàng");

        setThongTinNN();
        txtV_tongTienMA_tt.setText(key.Dou2Money(mDonhang.getTongTienMA()));
        phiVC_tt.setText(key.Dou2Money(mDonhang.getPhiVC()));
        tongTien_tt.setText(key.Dou2Money(mDonhang.getTongDH()));
        txtV_tienGiam_tt.setText(key.Dou2Money(mDonhang.getTienGiam()));

        List<ctdh> ctdhList = new ArrayList<>(mDonhang.getCthdMap().values());
        DsMA_tt_Adap dsMA_tt_adap = new DsMA_tt_Adap(this, ctdhList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recV_DsMA_tt.setLayoutManager(linearLayoutManager);
        recV_DsMA_tt.setAdapter(dsMA_tt_adap);

//        List<menu_option> mListMenuOp = new ArrayList<>();
//        mListMenuOp.add(new menu_option(key.key_KHUYENMAI, R.drawable.ic_round_paid_24, R.color.hifood5, "Chọn khuyến mãi, voucher"));
//        mListMenuOp.add(new menu_option(key.key_PHUONGTHUCTHANHTOAN, R.drawable.ic_round_credit_card_24, R.color.hifood5, "Chọn phương thức thanh toán"));
//
//        Menu_op1_Adap menu_op1_adap = new Menu_op1_Adap(this, mListMenuOp);
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//
//        recV_Menu_tt.setLayoutManager(linearLayoutManager1);
//        recV_Menu_tt.setAdapter(menu_op1_adap);
    }

    private void TinhTGGiaovaPhiVC(double KinhDo, double ViDo) {
        String url = key.url1 + QuanAnAct.quanan.getVitriQA().getKinhdo() + "%2C" +
                QuanAnAct.quanan.getVitriQA().getVido() + "%3B" +
                KinhDo + "%2C" +
                ViDo + key.url2 + key.token_mapbox;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("routes");
                    double Time = 0;
                    double Distance = 0;

                    // 0  là thời gian ngắn nhất  1 là quảng đường ngắn nhất
                    JSONObject jsonItem = ((JSONArray) jsonItems).getJSONObject(1);
                    Time = jsonItem.getDouble("duration");
                    Distance = jsonItem.getDouble("distance");

                    betime = Calendar.getInstance();
                    aftime = Calendar.getInstance();

                    aftime.add(Calendar.MINUTE, 15 + (int) (Time / 60));
                    int x = aftime.getTime().getMinutes() % 5;
                    aftime.add(Calendar.MINUTE, 5 - x);

                    mDonhang.setPhiVC(key.CheckPhiVC((int) Distance / 1000));
                    Log.e("PhiVC",mDonhang.getPhiVC() + " = " + Distance );
                    phiVC_tt.setText(key.Dou2Money(mDonhang.getPhiVC()));
                    mDonhang.setTongDH(mDonhang.getTongTienMAMap() + mDonhang.getPhiVC() - mDonhang.getTienGiam());
                    txtV_ThoiGianNhan_tt.setText("Thời gian giao: " + key.DateTimeFormat(aftime.getTime()));
                    tongTien_tt.setText(key.Dou2Money(mDonhang.getTongDH()));
                    Log.e("ThoiGian2", key.DateFormatSQL(aftime.getTime()) + " == " + key.DateFormatSQL(betime.getTime()) + " = " + Time / 60);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thanh_toan);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);

        EditText txtV_TenNN_dialog_tt = dialog.findViewById(R.id.txtV_TenNN_dialog_tt);
        EditText txtV_SDT_dialog_tt = dialog.findViewById(R.id.txtV_SDT_dialog_tt);
        EditText txtV_ViTri_dialog_tt = dialog.findViewById(R.id.txtV_ViTri_dialog_tt);
        ImageButton imgB_Dong_dialog_tt = dialog.findViewById(R.id.imgB_Dong_dialog_tt);
        Button btn_LuuThongTin_dialog_tt = dialog.findViewById(R.id.btn_LuuThongTin_dialog_tt);
        ImageButton imgB_LayViTri_tt = dialog.findViewById(R.id.imgB_LayViTri_tt);

        imgB_LayViTri_tt.setOnClickListener(view -> {
            if (MyAppication.mTaiKhoan.getVitri().getVitri() == null) {
                MyAppication.mViTri = MyAppication.mTaiKhoan.getCurVitri();
            } else {
                MyAppication.mViTri = MyAppication.mTaiKhoan.getVitri();
            }
            Intent intent = new Intent(ThanhToanAct.this, LayViTri.class);
            startActivity(intent);
            dialog.dismiss();
        });

        txtV_TenNN_dialog_tt.setText(mDonhang.getTenNN());
        txtV_SDT_dialog_tt.setText(mDonhang.getSDTNN());
        txtV_ViTri_dialog_tt.setText(mDonhang.getVitriDH());

        imgB_Dong_dialog_tt.setOnClickListener(view -> {
            dialog.dismiss();
        });

        btn_LuuThongTin_dialog_tt.setOnClickListener(view -> {
            if (txtV_TenNN_dialog_tt.getText().length() != 0 && txtV_SDT_dialog_tt.getText().length() != 0 && txtV_ViTri_dialog_tt.getText().length() != 0) {

                if (key.isSDT(txtV_SDT_dialog_tt.getText().toString())) {
                    mDonhang.setTenNN(txtV_TenNN_dialog_tt.getText().toString());
                    mDonhang.setSDTNN(txtV_SDT_dialog_tt.getText().toString());
                    mDonhang.setVitriDH(txtV_ViTri_dialog_tt.getText().toString());
                    setThongTinNN();
                    dialog.dismiss();
                    Toast.makeText(this, "Thông tin người nhận đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Vui lòng nhập đúng định dạng Số điện thoại ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void AnhXa() {

        txtV_TenTK_tt = findViewById(R.id.txtV_TenTK_tt);
        txtV_DiaChiNhan_tt = findViewById(R.id.txtV_DiaChiNhan_tt);
        txtV_tongTienMA_tt = findViewById(R.id.txtV_tongTienMA_tt);
        phiVC_tt = findViewById(R.id.phiVC_tt);
        tongTien_tt = findViewById(R.id.tongTien_tt);
        txtV_DoiDiaChiNhan_tt = findViewById(R.id.txtV_DoiDiaChiNhan_tt);
        txtV_ThoiGianNhan_tt = findViewById(R.id.txtV_ThoiGianNhan_tt);
        txtV_tienGiam_tt = findViewById(R.id.txtV_tienGiam_tt);

        btn_DatHang_tt = findViewById(R.id.btn_DatHang_tt);
        recV_DsMA_tt = findViewById(R.id.recV_DsMA_tt);
        tool3_ThanhToan = findViewById(R.id.tool3_ThanhToan);
        //recV_Menu_tt = findViewById(R.id.recV_Menu_tt);
    }
}