package com.example.bctn.fragment.QuanAn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.QuanAnAct;
import com.example.bctn.activity.ThanhToanAct;
import com.example.bctn.adapter.RecyclerAdapter.MonAn1Adap;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class QAThucDonFrag extends Fragment {

    private static TextView txtV_TSoL_gh, txtV_TongGia_QA;
    private View mView;
    private RecyclerView recV_ThucDon_QA;
    private quanan quanan;
    private RelativeLayout relative1_QA;
    public static donhang mDonhang;
    public static Map<Integer, Integer> mapRemove = new HashMap<>();
    private int IDDH;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_qa_thuc_don, container, false);
        AnhXa();

        quanan = QuanAnAct.quanan;
        IDDH = MyAppication.mDao.isExistDonNhap(MyAppication.mTaiKhoan.getIdTK(), quanan.getIdQA(), key.key_dh_Nhap);
        if (IDDH == -1) {
            IDDH = MyAppication.mDao.TaoIDDH();
            mDonhang = new donhang();
            txtV_TSoL_gh.setText("0");
            txtV_TongGia_QA.setText("0");
        } else {
            mDonhang = MyAppication.mDao.DH(IDDH, key.key_dh_Nhap);
            SoLuong();
        }

        Toast.makeText(mView.getContext(), String.valueOf(IDDH), Toast.LENGTH_SHORT).show();
        //getData_RecV();

        relative1_QA.setOnClickListener(view -> {
            if(mDonhang.getTongSoLuong() > 0){
                Intent intent = new Intent(getContext(), ThanhToanAct.class);
                intent.putExtra(key.key_IDDH, IDDH);
                startActivity(intent);
            } else {
                Toast.makeText(mView.getContext(), "Giỏ hàng đang trống, hãy thêm món ăn để có thể đặt hàng", Toast.LENGTH_SHORT).show();
            }
           
        });

        //OpenBotSheet();
        return mView;
    }

    public static void SoLuong() {
        txtV_TSoL_gh.setText(String.valueOf(mDonhang.getTongSoLuong()));
        txtV_TongGia_QA.setText(key.Dou2Money(mDonhang.getTongTienMAMap()));
    }

    private void getData_RecV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        MonAn1Adap monAn1Adap = new MonAn1Adap(getContext(), quanan.getDsMA(), quanan.getIdQA());
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mView.getContext(), DividerItemDecoration.VERTICAL);

        recV_ThucDon_QA.setLayoutManager(linearLayoutManager);
        recV_ThucDon_QA.setAdapter(monAn1Adap);
        recV_ThucDon_QA.addItemDecoration(itemDecoration);
    }

    public static void CapNhapGhiChuMonAn(int IDMA, String GhiChu) {
        mDonhang.getCthdMap().get(IDMA).setGhiChu(GhiChu);
    }

    public static void TaoMonAn(int IDMA, byte[] HinhMA, String TenMA, double GiaMA, int SL) {
        mDonhang.getCthdMap().put(IDMA, new ctdh(IDMA, HinhMA, TenMA, GiaMA, SL, ""));
        mapRemove.remove(IDMA);
        SoLuong();
    }

    public static void CapNhapMonAn(int IDMA, int SL) {
        if (SL > 0) {
            mDonhang.getCthdMap().get(IDMA).setSLMA(SL);
        } else if (SL == 0) {
            mDonhang.getCthdMap().get(IDMA).setSLMA(SL);
            mapRemove.put(IDMA, IDMA);
            mDonhang.getCthdMap().remove(IDMA);
        }
        SoLuong();
    }


    @Override
    public void onPause() {
        super.onPause();

        int IDDH = MyAppication.mDao.isExistDonNhap(MyAppication.mTaiKhoan.getIdTK(), quanan.getIdQA(), key.key_dh_Nhap);
        if (IDDH != -1) {
            MyAppication.mDao.CapNhatDH(IDDH, Calendar.getInstance().getTime().getTime(), key.key_dh_Nhap);
        } else {
            IDDH = MyAppication.mDao.TaoIDDH();
            MyAppication.mDao.TaoDonHang(IDDH, MyAppication.mTaiKhoan.getIdTK(), quanan.getIdQA(), key.key_dh_Nhap);
        }

        for (ctdh cthd : mDonhang.getCthdMap().values()) {
            if (!MyAppication.mDao.isExistMonAninDH(IDDH, cthd.getIDMA())) {
                MyAppication.mDao.ThemMonAninDH(IDDH, cthd.getIDMA(), cthd.getSLMA(), cthd.getGhiChu());
            } else {
                MyAppication.mDao.CapNhatMonAninDH(IDDH, cthd.getIDMA(), cthd.getSLMA(), cthd.getGhiChu());
            }
        }

        for (Integer IDMA : mapRemove.values()) {
            MyAppication.mDao.XoaMonAninDH(IDDH, IDMA);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        getData_RecV();
    }

    private void OpenBotSheet() {
        View view = getLayoutInflater().inflate(R.layout.bottomsheet_gio_hang, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mView.getContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        ImageButton imgB_Dong = view.findViewById(R.id.imgB_Dong);

        //bottomSheetDialog.set
        imgB_Dong.setOnClickListener(view1 -> {
            bottomSheetDialog.dismiss();
        });

    }

    private void AnhXa() {
        recV_ThucDon_QA = mView.findViewById(R.id.recV_ThucDon_QA);
        relative1_QA = mView.findViewById(R.id.relative1_QA);
        txtV_TSoL_gh = mView.findViewById(R.id.txtV_TSoL_gh);
        txtV_TongGia_QA = mView.findViewById(R.id.txtV_TongGia_QA);
    }

}
