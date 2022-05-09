package com.example.bctn;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.bctn.activity.DanhGia;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.danhgia;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.donhang_dhfrag;
import com.example.bctn.domain.key;
import com.example.bctn.domain.monan;
import com.example.bctn.domain.quanan;
import com.example.bctn.domain.taikhoan;
import com.example.bctn.domain.thongke;
import com.example.bctn.domain.vitri;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO {
    Context mContext;
    Database mDatabase;
    SQLiteDatabase sqLiteDatabase;

    public DAO(Context mContext) {
        this.mContext = mContext;
        mDatabase = new Database(mContext);
        //sqLiteDatabase = mDatabase.WritableData();
    }

    // Tìm kiếm

    public List<quanan> TimKiem(String timkiem) {
        List<quanan> quananList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM QuanAn A WHERE Khoa = 0 AND TenQA Like '%" + timkiem
                + "%' OR EXISTS (SELECT * FROM MonAn B WHERE Khoa = 0 TenMA Like '%" + timkiem + "%' AND A.IDQA = B.IDQA) LIMIT 50 ");
        while (tro.moveToNext()) {
            List<monan> monanList = new ArrayList<>();
            Cursor tro2 = mDatabase.Get("SELECT * FROM MonAn WHERE Khoa = 0 AND TenMA Like '%" + timkiem + "%' AND IDQA = " + tro.getInt(0));

            while (tro2.moveToNext()) {
                monanList.add(new monan(tro2.getInt(1),
                        tro2.getBlob(2),
                        tro2.getString(3),
                        tro2.getDouble(4)));
            }

            quananList.add(new quanan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    new vitri(tro.getString(3), 0, 0),
                    monanList));
        }
        return quananList;
    }

    // region Tài khoản
    public List<taikhoan> GetListTK() {
        List<taikhoan> taikhoanList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM TaiKhoan");
        while (tro.moveToNext()) {
            taikhoanList.add(new taikhoan(tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getBlob(4),
                    new vitri(tro.getString(5),
                            tro.getDouble(6),
                            tro.getDouble(7)),
                    tro.getString(8),
                    tro.getInt(9) == 1));
        }
        return taikhoanList;
    }

    public boolean isExistTK(String SDT) {
        Cursor tro = mDatabase.Get("SELECT * FROM TaiKhoan WHERE SDT='" + SDT + "'");
        while (tro.moveToNext()) {
            return true;
        }
        return false;
    }

    public taikhoan GetTK(int IDTK) {
        Cursor tro = mDatabase.Get("SELECT * FROM TaiKhoan WHERE IDTK = " + IDTK);
        while (tro.moveToNext()) {
            return new taikhoan(tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getBlob(4),
                    new vitri(tro.getString(5),
                            tro.getDouble(6),
                            tro.getDouble(7)),
                    tro.getString(8),
                    tro.getInt(9) == 1);
        }
        return null;
    }

    public taikhoan GetTK(String SDT, String MatKhau) {
        Cursor tro = mDatabase.Get("SELECT * FROM TaiKhoan WHERE SDT = '"
                + SDT + "' AND MatKhau = '" + MatKhau + "'");
        while (tro.moveToNext()) {
            return new taikhoan(tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getBlob(4),
                    new vitri(tro.getString(5),
                            tro.getDouble(6),
                            tro.getDouble(7)),
                    tro.getString(8),
                    tro.getInt(9) == 1);
        }
        return null;
    }

    public int TKQA(int IDTK) {
        Cursor tro = mDatabase.Get("SELECT IDQA FROM QuanAn WHERE IDTK = " + IDTK);
        while (tro.moveToNext()) {
            return tro.getInt(0);
        }
        return -1;
    }

    public int IDTK(String SDT, String MK) {
        Cursor tro = mDatabase.Get("SELECT * FROM TaiKhoan WHERE SDT='" + SDT + "' AND MatKhau = '" + MK + "'");
        while (tro.moveToNext()) {
            return tro.getInt(0);
        }
        return -1;
    }

    public void CapNhatViTriTK(int IDTK, String DiaChi, double ViDo, double KinhDo) {
        mDatabase.Query("UPDATE TaiKhoan SET DiaChi = '" + DiaChi + "', ViDo = "
                + ViDo + " , KinhDo = " + KinhDo + " WHERE IDTK = " + IDTK);
    }

    public void CapNhatHinhTK(int IDTK, byte[] HinhTK) {
        String sql = "UPDATE TaiKhoan SET HinhTK = ? WHERE IDTK = " + IDTK;
        SQLiteDatabase database = mDatabase.WritableData();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, HinhTK);
        statement.executeInsert();
    }

    public void CapNhatTK(int IDTK, String SDT, String MK, String TENTK, String Quyen, boolean Khoa) {
        mDatabase.Query("UPDATE TaiKhoan SET SDT = '" + SDT + "', MATKHAU = '"
                + MK + "' , TENTK = '" + TENTK + "', QUYEN = '" + Quyen + "', Khoa = " + (Khoa ? 1 : 0) + " WHERE IDTK = " + IDTK);
    }

    public void CapNhatMK(int IDTK, String MK) {
        mDatabase.Query("UPDATE TaiKhoan SET MATKHAU = '" + MK + "' WHERE IDTK = " + IDTK);
    }

    public void TaoTK(String SDT, String MK, String TENTK, String QUYEN) {
        mDatabase.Query("INSERT INTO TaiKhoan (SDT, MATKHAU, TENTK, QUYEN, Khoa) VALUES ('"
                + SDT + "','" + MK + "','" + TENTK + "','" + QUYEN + "'," + 0 + ")");
    }

    public int isQAKhoa(int IDTK) {
        Cursor tro = mDatabase.Get("SELECT Khoa FROM QuanAn WHERE IDTK = " + IDTK);
        while (tro.moveToNext()) {
            return tro.getInt(0) == 1 ? 1 : 0;
        }
        return -1;
    }

    public List<taikhoan> GetListTKQA() {
        List<taikhoan> taikhoanList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT A.IDTK, A.SDT, A.MatKhau, A.TenTK, A.HinhTK, A.DiaChi, A.ViDo, A.KinhDo, " +
                "A.Quyen,A.Khoa FROM TaiKhoan A WHERE A.Quyen = 'dinner' AND IDTK NOT IN(SELECT IDTK FROM QuanAn)");
        while (tro.moveToNext()) {
            taikhoanList.add(new taikhoan(tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getBlob(4),
                    new vitri(tro.getString(5),
                            tro.getDouble(6),
                            tro.getDouble(7)),
                    tro.getString(8),
                    tro.getInt(9) == 1));
        }
        return taikhoanList;
    }
    // endregion


    // region Quán Ăn

    public quanan TTQA_DG(int IDDH) {
        Cursor tro = mDatabase.Get("SELECT B.IDQA, B.TenQA, B.HinhQA, B.DiaChiQA, B.ViDoQA, B.KinhDoQA " +
                "FROM DonHang A, QuanAn B WHERE A.IDQA = B.IDQA AND A.IDDH = " + IDDH);
        tro.moveToNext();
        return new quanan(tro.getInt(0),
                tro.getString(1),
                tro.getBlob(2),
                new vitri(tro.getString(3), tro.getDouble(4), tro.getDouble(5)));
    }

    public quanan ThongTinQA(int IDQA) {

        List<monan> list = new ArrayList<>();
        Cursor tro1 = mDatabase.Get("SELECT * FROM MonAn WHERE Khoa = 0 AND IDQA = " + IDQA);
        while (tro1.moveToNext()) {
            list.add(new monan(tro1.getInt(1),
                    tro1.getBlob(2),
                    tro1.getString(3),
                    tro1.getDouble(4)));
        }

        Cursor tro2 = mDatabase.Get("SELECT A.IDQA, A.TenQA, A.HinhQA, A.DiaChiQA, A.ViDoQA, A.KinhDoQA, A.Khoa, " +
                "(SELECT IFNULL(AVG(B.SaoDG),0) FROM DanhGia B, DonHang C WHERE B.IDDH = C.IDDH AND C.IDQA = A.IDQA) AS SaoQA" +
                " FROM QuanAn A WHERE IDQA = " + IDQA);
        while (tro2.moveToNext()) {
            return new quanan(
                    tro2.getInt(0),
                    tro2.getString(1),
                    tro2.getBlob(2),
                    new vitri(tro2.getString(3), tro2.getDouble(4), tro2.getDouble(5)),
                    tro2.getInt(6) == 1,
                    list,
                    tro2.getDouble(7)
            );
        }
        return null;
    }

    public quanan QA(int IDQA) {

        List<monan> list = new ArrayList<>();
        Cursor tro1 = mDatabase.Get("SELECT * FROM MonAn WHERE Khoa = 0 AND IDQA = " + IDQA);
        while (tro1.moveToNext()) {
            list.add(new monan(tro1.getInt(1),
                    tro1.getBlob(2),
                    tro1.getString(3),
                    tro1.getDouble(4)));
        }

        Cursor tro2 = mDatabase.Get("SELECT * FROM QuanAn WHERE IDQA = " + IDQA);
        while (tro2.moveToNext()) {
            return new quanan(
                    tro2.getInt(0),
                    tro2.getString(1),
                    tro2.getBlob(2),
                    new vitri(tro2.getString(3), tro2.getDouble(4), tro2.getDouble(5)),
                    tro2.getInt(6) == 1,
                    list,
                    tro2.getInt(7)
            );
        }
        return null;
    }

    public List<quanan> ListQAGanBan() {
        List<quanan> list = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT A.IDQA, A.TenQA, A.HinhQA, A.DiaChiQA, A.ViDoQA, A.KinhDoQA,(SELECT IFNULL(AVG(B.SaoDG),0) " +
                "FROM DanhGia B, DonHang C WHERE B.IDDH = C.IDDH AND C.IDQA = A.IDQA) AS SaoQA FROM QuanAn A " +
                "WHERE Khoa = 0 LIMIT 8");
        while (tro.moveToNext()) {
            list.add(new quanan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    new vitri(tro.getString(3), tro.getDouble(4), tro.getDouble(5)),
                    tro.getDouble(6)
            ));
        }
        return list;
    }

    public List<quanan> ListQADanhGiaTot() {
        List<quanan> list = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT A.IDQA, A.TenQA, A.HinhQA, A.DiaChiQA, A.ViDoQA, A.KinhDoQA,(SELECT IFNULL(AVG(B.SaoDG),0) " +
                "FROM DanhGia B, DonHang C WHERE B.IDDH = C.IDDH AND C.IDQA = A.IDQA) AS SaoQA FROM QuanAn A " +
                "WHERE Khoa = 0 ORDER BY SaoQA DESC LIMIT 8");
        while (tro.moveToNext()) {
            list.add(new quanan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    new vitri(tro.getString(3), tro.getDouble(4), tro.getDouble(5)),
                    tro.getDouble(6)
            ));
        }
        return list;
    }

    public List<quanan> GetListQA() {
        List<quanan> quananList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM QuanAn");
        while (tro.moveToNext()) {
            quananList.add(new quanan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    new vitri(tro.getString(3), 0, 0)
            ));
        }
        return quananList;
    }

    public int TaoIDQA() {
        Cursor tro = mDatabase.Get("SELECT IDQA FROM QuanAn ORDER BY IDQA DESC LIMIT 1");
        tro.moveToNext();
        return tro.getInt(0) + 1;
    }

    public void TaoQA(int IDQA, String TenQA, int IDTK) {
        mDatabase.Query("INSERT INTO QuanAn( IDQA, TenQA, Khoa, IDTK) VALUES ("
                + IDQA + " ,'" + TenQA + "', " + 0 + "," + IDTK + ")");
    }

    public void CapNhatQA(int IDQA, String TenQA, boolean Khoa, int IDTK) {
        mDatabase.Query("UPDATE QuanAn SET TenQA = "
                + DatabaseUtils.sqlEscapeString(TenQA) + ", Khoa = " + (Khoa ? 1 : 0) + ", IDTK =" + IDTK + " WHERE IDQA = " + IDQA);
    }

    public void CapNhatViTriQA(int IDQA, String DiaChiQA, double ViDoQA, double KinhDoQA) {
        mDatabase.Query("UPDATE QuanAn SET DiaChiQA = '"
                + DiaChiQA + "', ViDoQA = " + ViDoQA + " , KinhDoQA = " + KinhDoQA + " WHERE IDQA = " + IDQA);
    }

    public vitri LayViTriQA(int IDQA) {
        Cursor tro = mDatabase.Get("SELECT DiaChiQA, ViDoQA, KinhDoQA FROM QuanAn WHERE IDQA = " + IDQA);
        while (tro.moveToNext()) {
            return new vitri(tro.getString(0),
                    tro.getDouble(1),
                    tro.getDouble(2));
        }
        return null;
    }

    public void CapNhatHinhQA(int IDQA, byte[] HinhQA) {
        String sql = "UPDATE QuanAn SET HinhQA = ? WHERE IDQA = " + IDQA;
        SQLiteDatabase database = mDatabase.WritableData();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, HinhQA);
        statement.executeInsert();
    }
    // endregion

    // region Món Ăn
    public monan MA(int IDMA) {
        Cursor tro = mDatabase.Get("SELECT * FROM MonAn WHERE IDMA = " + IDMA);
        tro.moveToNext();
        return new monan(tro.getInt(1), tro.getBlob(2), tro.getString(3), tro.getDouble(4), tro.getInt(5) == 1);
    }

    public int TaoIDMA() {
        Cursor tro = mDatabase.Get("SELECT IDMA FROM MonAn ORDER BY IDMA DESC LIMIT 1");
        tro.moveToNext();
        return tro.getInt(0) + 1;
    }

    public void TaoMA(int IDQA, int IDMA, String TenMA, double GiaMA) {
        mDatabase.Query("INSERT INTO MonAn( IDQA, IDMA, TenMa, GiaMA, Khoa) VALUES ("
                + IDQA + " , " + IDMA + " ,'" + TenMA + "', " + GiaMA + " ," + 0 + ")");
    }

    public void CapNhatMA(int IDMA, String TenMA, double GiaMA, boolean Khoa) {
        mDatabase.Query("UPDATE MonAn SET TenMA = '"
                + TenMA + "', GiaMA = " + GiaMA + ", Khoa = " + (Khoa ? 1 : 0) + " WHERE IDMA = " + IDMA);
    }

    public void CapNhatHinhMA(int IDMA, byte[] HinhMA) {
        String sql = "UPDATE MonAn SET HinhMA = ? WHERE IDMA = " + IDMA;
        SQLiteDatabase database = mDatabase.WritableData();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, HinhMA);
        statement.executeInsert();
    }

    public List<monan> ListMA(int IDQA) {
        List<monan> monanList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM MonAn WHERE IDQA = " + IDQA);
        while (tro.moveToNext()) {
            monanList.add(new monan(tro.getInt(1), tro.getBlob(2), tro.getString(3),
                    tro.getDouble(4), tro.getInt(5) == 1));
        }
        return monanList;
    }

    // endregion

    // region Đơn hàng
    public List<donhang> ListQADH(int IDQA, String TTGiao) {
        List<donhang> donhangList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM DonHang WHERE TTGiao = '" + TTGiao
                + "' AND IDQA = " + IDQA + " ORDER BY TGDat DESC");
        while (tro.moveToNext()) {
            donhangList.add(new donhang(tro.getInt(0),
                    tro.getInt(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getDouble(5),
                    tro.getDouble(6),
                    tro.getDouble(7),
                    tro.getDouble(8),
                    tro.getString(9),
                    key.DateFromSQL(tro.getString(10)),
                    key.DateFromSQL(tro.getString(11)),
                    tro.getString(12)));
        }
        return donhangList;
    }

    public List<ctdh> ListQACTDH(int IDDH) {
        List<ctdh> ctdhList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT A.IDMA, B.TenMA, B.GiaMA, A.SoLuong, A.GhiChu FROM CTDonHang A, MonAn B WHERE A.IDMA = B.IDMA AND IDDH =" + IDDH);
        while (tro.moveToNext()) {
            ctdhList.add(new ctdh(tro.getInt(0),
                    tro.getString(1),
                    tro.getDouble(2),
                    tro.getInt(3),
                    tro.getString(4)));
        }
        return ctdhList;
    }

    public donhang DH(int IDDH, String TTGiao) {
        donhang donhang = null;
        Cursor tro = mDatabase.Get("SELECT * FROM DonHang WHERE IDDH = " + IDDH + " AND TTGiao ='" + TTGiao + "'");

        while (tro.moveToNext()) {
            Cursor tro2 = mDatabase.Get("SELECT B.IDMA,C.HinhMA,C.TenMA, C.GiaMa, B.SoLuong, B.GhiChu" +
                    "  FROM DonHang A,CTDonHang B, MonAn C WHERE A.IDDH = B.IDDH AND C.IDMA = B.IDMA AND C.IDQA = A.IDQA AND A.IDDH =" + IDDH);
            Map<Integer, ctdh> ctdhMap = new HashMap<>();
            while (tro2.moveToNext()) {
                ctdhMap.put(tro2.getInt(0), new ctdh(tro2.getInt(0),
                        tro2.getBlob(1),
                        tro2.getString(2),
                        tro2.getDouble(3),
                        tro2.getInt(4),
                        tro2.getString(5) == null ? "" : tro2.getString(5)));
            }

            donhang = new donhang(IDDH,
                    tro.getInt(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getDouble(5),
                    tro.getDouble(6),
                    tro.getDouble(7),
                    tro.getDouble(8),
                    tro.getString(9),
                    key.DateFromSQL(tro.getString(10)),
                    tro.getString(11) != null ? key.DateFromSQL(tro.getString(11)) : null,
                    tro.getString(12),
                    ctdhMap);
        }

        return donhang;
    }

    public List<donhang_dhfrag> DHFrag(int IDTK, String TTGiao) {
        List<donhang_dhfrag> donhang_dhfragList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT A.IDDH,A.IDQA, B.HinhQA, B.TenQA, B.DiaChiQA, A.TongTien," +
                " (SELECT SUM(SoLuong) FROM CTDonHang C WHERE C.IDDH = A.IDDH) AS 'SoLuong', A.TTGiao" +
                " FROM DonHang A, QuanAn B WHERE A.IDQA = B.IDQA  AND A.IDTK = " + IDTK + " AND TTGiao = '" + TTGiao + "' ORDER BY TGGiao DESC");

        while (tro.moveToNext()) {
            donhang_dhfragList.add(new donhang_dhfrag(tro.getInt(0),
                    tro.getInt(1),
                    tro.getBlob(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getDouble(5),
                    tro.getInt(6),
                    tro.getString(7)));
        }
        return donhang_dhfragList;
    }

    public List<donhang_dhfrag> DHHoanThanhFrag(int IDTK, String TTGiao) {
        List<donhang_dhfrag> donhang_dhfragList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT A.IDDH,A.IDQA, B.HinhQA, B.TenQA, B.DiaChiQA, A.TongTien," +
                " (SELECT SUM(SoLuong) FROM CTDonHang C WHERE C.IDDH = A.IDDH) AS 'SoLuong', A.TTGiao," +
                " EXISTS (SELECT IDDH FROM DanhGia C WHERE C.IDDH = A.IDDH) AS 'DanhGia'" +
                " FROM DonHang A, QuanAn B WHERE A.IDQA = B.IDQA  AND A.IDTK = " + IDTK + " AND TTGiao = '" + TTGiao + "' ORDER BY TGGiao DESC");

        while (tro.moveToNext()) {
            donhang_dhfragList.add(new donhang_dhfrag(tro.getInt(0),
                    tro.getInt(1),
                    tro.getBlob(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getDouble(5),
                    tro.getInt(6),
                    tro.getString(7),
                    tro.getInt(8) == 1));
        }
        return donhang_dhfragList;
    }

    public int isExistDonNhap(int IDTK, int IDQA, String TTGiao) {
        Cursor tro = mDatabase.Get("SELECT * FROM DonHang WHERE IDTK = "
                + IDTK + " AND IDQA = " + IDQA + " AND TTGiao = '" + TTGiao + "'");
        while (tro.moveToNext()) {
            return tro.getInt(0);
        }
        return -1;
    }

    public boolean isExistMonAninDH(int IDDH, int IDMA) {
        Cursor tro = mDatabase.Get("SELECT * FROM CTDonHang WHERE IDDH = "
                + IDDH + " AND IDMA = " + IDMA);
        while (tro.moveToNext()) {
            return true;
        }
        return false;
    }

    public void XoaDH(int IDDH) {
        mDatabase.Query("DELETE FROM DonHang WHERE IDDH =" + IDDH);
    }

    public void CapNhatDH(int IDDH, double TongTienMA, double TongTien, String TGDat, String TTGiao) {
        mDatabase.Query("UPDATE DonHang SET TongTienMA = " + TongTienMA + ", TongTien = " + TongTien
                + ", TGDat = '" + TGDat + "', TTGiao = '" + TTGiao + "' WHERE IDDH = " + IDDH);
    }

    public void CapNhatTTGiao(int IDDH, String TTGiao) {
        mDatabase.Query("UPDATE DonHang SET TTGiao = '" + TTGiao + "' WHERE IDDH =" + IDDH);
    }

    public void CapNhatDH(int IDDH, String TenNN, String SDTNN,
                          double TongTienMA, double PhiVC, double TienGiam, double TongTien,
                          String ViTriGiao, String TGDat, String TGGiao, String TTGiao) {

        mDatabase.Query("UPDATE DonHang SET TenNN ='" + TenNN + "', SDTNN ='" + SDTNN + "', TongTienMA = '" + TongTienMA + "', PhiVanChuyen =" +
                PhiVC + ", TienGiam = " + TienGiam + ", TongTien = " + TongTien + ", ViTriGiao = '" + ViTriGiao
                + "', TGDat = '" + TGDat + "', TGGiao = '" + TGGiao + "', TTGiao ='" + TTGiao
                + "' WHERE IDDH = " + IDDH);
    }

    public void ThemMonAninDH(int IDDH, int IDMA, int SoL, double GiaMA, String GhiChu) {
        mDatabase.Query("INSERT INTO CTDonHang( IDDH, IDMA, SoLuong, GiaMA, GhiChu) VALUES ( "
                + IDDH + " , " + IDMA + " , " + SoL + " , " + GiaMA + " ,'" + GhiChu + "')");
    }

    public void CapNhatMonAninDH(int IDDH, int IDMA, int SoL, double GiaMA, String GhiChu) {
        mDatabase.Query("UPDATE CTDonHang SET SoLuong = " + SoL +
                ", GiaMA = " + GiaMA + " , GhiChu = '" + GhiChu + "' WHERE IDDH = " + IDDH + " AND IDMA = " + IDMA);
    }

    public void XoaMonAninDH(int IDDH, int IDMA) {
        mDatabase.Query("DELETE FROM CTDonHang WHERE IDDH = " + IDDH + " AND IDMA = " + IDMA);
    }

    public int TaoIDDH() {
        Cursor tro = mDatabase.Get("SELECT * FROM DonHang ORDER BY IDDH DESC LIMIT 1");
        tro.moveToNext();
        return tro.getInt(0) + 1;
    }

    public void TaoDonHang(int IDDH, int IDTK, int IDQA, double TongTienMA, double TongTien, String TTGiao) {
        mDatabase.Query("INSERT INTO DonHang(IDDH, IDTK, IDQA, TongTienMA, TongTien , TGDat,TTGiao) VALUES ( "
                + IDDH + " , " + IDTK + " , " + IDQA + " , " + TongTienMA + " , " + TongTien + ",'"
                + key.DateFormatSQL(Calendar.getInstance().getTime()) + "','" + TTGiao + "')");
    }

    // endregion

    // region Yêu thích
    public boolean isThich(int IDTK, int IDQA) {
        Cursor tro = mDatabase.Get("SELECT * FROM YeuThich WHERE IDTK = "
                + IDTK + " AND IDQA = " + IDQA);
        while (tro.moveToNext()) {
            return true;
        }
        return false;
    }

    public void ThemYT(int IDTK, int IDQA) {
        mDatabase.Query("INSERT INTO YeuThich(IDTK,IDQA,ThoiGian) VALUES ("
                + IDTK + " , " + IDQA + " , '" + key.DateFormatSQL(Calendar.getInstance().getTime()) + "')");
    }

    public void XoaYT(int IDTK, int IDQA) {
        mDatabase.Query("DELETE FROM YeuThich WHERE IDTK = " + IDTK + " AND IDQA = " + IDQA);
    }

    public List<quanan> ListQAYT(int IDTK) {
        List<quanan> list = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT  A.IDQA, A.TenQA, A.HinhQA, A.DiaChiQA, A.ViDoQA, A.KinhDoQA," +
                "(SELECT IFNULL(AVG(B.SaoDG),0) FROM DanhGia B, DonHang C WHERE B.IDDH = C.IDDH AND C.IDQA = A.IDQA) AS SaoQA " +
                "FROM QuanAn A, YeuThich B WHERE  B.IDTK = " + IDTK + " AND A.IDQA = B.IDQA ORDER BY ThoiGian DESC");
        while (tro.moveToNext()) {
            list.add(new quanan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    new vitri(tro.getString(3), 4, 5),
                    tro.getDouble(6)));
        }

        return list;
    }
    // endregion

    // region Đánh giá
    public List<danhgia> ListDG(int IDQA) {
        List<danhgia> danhgiaList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT C.HinhTK,C.TenTK, B.TGDG, B.NoiDungDG, B.SaoDG " +
                "FROM DonHang A, DanhGia B, TaiKhoan C WHERE A.IDTK = C.IDTK AND A.IDDH = B.IDDH AND A.IDQA = " + IDQA + " ORDER BY B.TGDG DESC");
        while (tro.moveToNext()) {
            danhgiaList.add(new danhgia(tro.getBlob(0), tro.getString(1), (tro.getString(2)), tro.getString(3), tro.getDouble(4)));
        }
        return danhgiaList;
    }

    public void TaoDG(int IDDH, String NoiDungDG, double SaoDG) {
        mDatabase.Query("INSERT INTO DanhGia(IDDH,NoiDungDG,SaoDG,TGDG) VALUES ("
                + IDDH + " ,'" + NoiDungDG + "'," + SaoDG + ",'" + key.DateFormatSQL(Calendar.getInstance().getTime()) + "')");
    }

    public danhgia DG(int IDDH) {
        Cursor tro = mDatabase.Get("SELECT NoiDungDG, SaoDG FROM DanhGia WHERE IDDH =" + IDDH);
        tro.moveToNext();
        return new danhgia(tro.getString(0), tro.getDouble(1));
    }
    // endregion


    // region Thống kê

    public ArrayList<thongke> ThongKe(String ThangNam) { // * C.GiaMA có thể nhân thêm ngay số lượng
        Cursor tro = mDatabase.Get("SELECT A.IDQA, A.TenQA, " +
                "(SELECT IFNULL(SUM(B.TongTien),0)  FROM DonHang B WHERE A.IDQA = B.IDQA AND TTGiao = 'HoanThanh' " +
                "AND strftime('%Y %m',TGDat) = '" + ThangNam + "') AS DoanhThu FROM QuanAn A WHERE DoanhThu > 0 ORDER BY DoanhThu DESC LIMIT 20");
        ArrayList<thongke> thongke = new ArrayList<>();
        while (tro.moveToNext()) {
            thongke.add(new thongke(tro.getString(1), tro.getDouble(2)));
        }
        return thongke;
    }

    public ArrayList<PieEntry> ThongKeQA(int IDQA, String Ngay) { // * C.GiaMA có thể nhân thêm ngay số lượng
        Cursor tro = mDatabase.Get("SELECT C.IDMA,D.TenMA, IFNULL(SUM(C.SoLuong),0) " +
                "FROM CTDonHang C, MonAn D,DonHang E WHERE E.IDDH = C.IDDH AND C.IDMA = D.IDMA AND " +
                " strftime('%Y %m %d',E.TGDat) = '" + Ngay + "' AND E.TTGiao Like 'HoanThanh' " +
                " AND C.IDMA IN (SELECT DISTINCT(IDMA) FROM DonHang A, MonAn B WHERE A.IDQA = B.IDQA AND A.IDQA = "
                + IDQA + ") GROUP BY C.IDMA");
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        while (tro.moveToNext()) {
            pieEntries.add(new PieEntry(tro.getInt(2), tro.getString(1)));
        }
        return pieEntries;
    }

    public int TongDonHangQA(int IDQA, String Ngay) {
        Cursor tro = mDatabase.Get("SELECT IFNULL(COUNT(IDDH),0) AS TongDon FROM DonHang " +
                "WHERE TTGiao Like 'HoanThanh' AND IDQA = " + IDQA + " AND strftime('%Y %m %d',TGDat) = '" + Ngay + "'");
        tro.moveToNext();
        return tro.getInt(0);
    }

    public double TongDoanhThuQA(int IDQA, String Ngay) {
        Cursor tro = mDatabase.Get("SELECT IFNULL(SUM(TongTien),0) AS TongDon FROM DonHang " +
                "WHERE TTGiao Like 'HoanThanh' AND IDQA = " + IDQA + " AND strftime('%Y %m %d',TGDat) = '" + Ngay + "'");
        tro.moveToNext();
        return tro.getDouble(0);
    }

    // endregion
}
