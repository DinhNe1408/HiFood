package com.example.bctn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.donhang_dhfrag;
import com.example.bctn.domain.monan;
import com.example.bctn.domain.quanan;
import com.example.bctn.domain.taikhoan;
import com.example.bctn.domain.vitri;

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

    // region Tài khoản
    public List<taikhoan> GetListTK() {
        List<taikhoan> taikhoanList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM TaiKhoan ORDER BY TGCS DESC");
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

    public String GetMatKhauTK(String SDT) {
        Cursor tro = mDatabase.Get("SELECT * FROM TaiKhoan WHERE SDT = '"
                + SDT + "'");
        while (tro.moveToNext()) {
            return tro.getString(2);
        }
        return null;
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

    // endregion


    // region Quán Ăn
    public quanan QA(int IDQA) {

        List<monan> list = new ArrayList<>();
        Cursor tro1 = mDatabase.Get("SELECT * FROM MonAn WHERE IDQA = " + IDQA);
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
                    new vitri(tro2.getString(3), 0, 0),
                    tro2.getInt(6) == 1,
                    list
            );
        }
        return null;
    }

    public List<quanan> ListQAGanBan() {
        List<quanan> list = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM QuanAn LIMIT 8");
        while (tro.moveToNext()) {
            list.add(new quanan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    new vitri(tro.getString(3), 0, 0)
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

    public int TaoIDQA (){
        Cursor tro = mDatabase.Get("SELECT IDQA FROM QuanAn ORDER BY IDQA DESC LIMIT 1");
        tro.moveToNext();
        return tro.getInt(0) + 1;
    }

    public void TaoQA(int IDQA, String TenQA) {
        mDatabase.Query("INSERT INTO QuanAn( IDQA, TenQA, Khoa) VALUES ("
                + IDQA + " ,'" + TenQA + "', " + 0 + ")");
    }

    public void CapNhatQA(int IDQA, String TenQA, boolean Khoa) {
        mDatabase.Query("UPDATE QuanAn SET TenQA = '"
                + TenQA + "', Khoa = " + (Khoa ? 1 : 0) + " WHERE IDQA = " + IDQA);
    }

    public void CapNhatViTriQA(int IDQA, String DiaChiQA, double ViDoQA, double KinhDoQA) {
        mDatabase.Query("UPDATE QuanAn SET DiaChiQA = '"
                + DiaChiQA + "', ViDoQA = " + ViDoQA + " , KinhDoQA = " + KinhDoQA + " WHERE IDQA = " + IDQA);
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

    public int TaoIDMA (){
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

    public List<monan> ListMA(int IDQA){
        List<monan> monanList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM MonAn WHERE IDQA = " + IDQA);
        while (tro.moveToNext()){
            monanList.add(new monan(tro.getInt(1), tro.getBlob(2), tro.getString(3),
                    tro.getDouble(4), tro.getInt(5) == 1));
        }
        return monanList;
    }

    // endregion

    // region Đơn hàng
    public donhang DH(int IDDH, String TTGiao) {
        donhang donhang = null;
        Cursor tro = mDatabase.Get("SELECT * FROM DonHang WHERE IDDH = " + IDDH + " AND TTGiao ='" + TTGiao + "'");

        while (tro.moveToNext()) {
            Cursor tro2 = mDatabase.Get("SELECT B.IDMA,HinhMA,TenMA, GiaMa, SoLuong, GhiChu" +
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
                    tro.getLong(10),
                    tro.getLong(11),
                    tro.getString(12),
                    ctdhMap);
        }

        return donhang;
    }

    public List<donhang_dhfrag> DHFrag(int IDTK, String TTGiao) {
        List<donhang_dhfrag> donhang_dhfragList = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT A.IDDH,A.IDQA, B.HinhQA, B.TenQA, B.DiaChiQA, A.TongTienMA," +
                " (SELECT SUM(SoLuong) FROM CTDonHang C WHERE C.IDDH = A.IDDH) AS 'SoLuong', A.TTGiao" +
                " FROM DonHang A, QuanAn B WHERE A.IDQA = B.IDQA  AND IDTK = " + IDTK + " AND TTGiao = '" + TTGiao + "' ORDER BY TGGiao DESC");

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

    public boolean isExistCTDH(int IDDH) {
        Cursor tro = mDatabase.Get("SELECT * FROM CTDonHang WHERE IDDH = " + IDDH);
        while (tro.moveToNext()) {
            return true;
        }
        return false;
    }

    public void CapNhatDH(int IDDH, Long TGDat, String TTGiao) {

        mDatabase.Query("UPDATE DonHang SET TGDat = '" + TGDat + "', TTGiao = '" + TTGiao + "' WHERE IDDH = " + IDDH);
    }

    public void CapNhatDH(int IDDH, String TenNN, String SDTNN,
                          double TongTienMA, double PhiVC, double TienGiam, double TongTien,
                          String ViTriGiao, Long TGDat, Long TGGiao, String TTGiao) {

        mDatabase.Query("UPDATE DonHang SET TenNN ='" + TenNN + "', SDTNN ='" + SDTNN + "', TongTienMA = '" + TongTienMA + "', PhiVanChuyen =" +
                PhiVC + ", TienGiam = " + TienGiam + ", TongTien = " + TongTien + ", ViTriGiao = '" + ViTriGiao
                + "', TGDat = '" + TGDat + "', TGGiao = '" + TGGiao + "', TTGiao ='" + TTGiao
                + "' WHERE IDDH = " + IDDH);
    }

    public void ThemMonAninDH(int IDDH, int IDMA, int SoL, String GhiChu) {
        mDatabase.Query("INSERT INTO CTDonHang( IDDH, IDMA, SoLuong, GhiChu) VALUES ( "
                + IDDH + " , " + IDMA + " , " + SoL + " ,'" + GhiChu + "')");
    }

    public void CapNhatMonAninDH(int IDDH, int IDMA, int SoL, String GhiChu) {
        mDatabase.Query("UPDATE CTDonHang SET SoLuong = "
                + SoL + " , GhiChu = '" + GhiChu + "' WHERE IDDH = " + IDDH + " AND IDMA = " + IDMA);
    }

    public void XoaMonAninDH(int IDDH, int IDMA) {
        mDatabase.Query("DELETE FROM CTDonHang WHERE IDDH = " + IDDH + " AND IDMA = " + IDMA);
    }

    public int TaoIDDH() {
        Cursor tro = mDatabase.Get("SELECT * FROM DonHang ORDER BY IDDH DESC LIMIT 1");
        tro.moveToNext();
        return tro.getInt(0) + 1;
    }

    public void TaoDonHang(int IDDH, int IDTK, int IDQA, String TTGiao) {
        mDatabase.Query("INSERT INTO DonHang(IDDH, IDTK, IDQA, TGDat,TTGiao) VALUES ( "
                + IDDH + " , " + IDTK + " , " + IDQA + " , '" + Calendar.getInstance().getTime() + "','" + TTGiao + "')");
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
                + IDTK + " , " + IDQA + " , '" + Calendar.getInstance().getTime() + "')");
    }

    public void XoaYT(int IDTK, int IDQA) {
        mDatabase.Query("DELETE FROM YeuThich WHERE IDTK = " + IDTK + " AND IDQA = " + IDQA);
    }

    public List<quanan> ListQAYT(int idTK) {
        List<quanan> list = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM QuanAn B, YeuThich A WHERE  A.IDTK = "
                + idTK + " AND A.IDQA = B.IDQA ORDER BY ThoiGian DESC");
        while (tro.moveToNext()) {
            list.add(new quanan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    new vitri(tro.getString(3), 0, 0)));
        }
        ;
        return list;
    }
    // endregion
}
