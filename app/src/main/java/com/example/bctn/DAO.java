package com.example.bctn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.bctn.domain.monan;
import com.example.bctn.domain.quanan;
import com.example.bctn.domain.taikhoan;
import com.example.bctn.domain.vitri;

import java.util.ArrayList;
import java.util.List;

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
    public boolean isExistTK(String SDT) {
        Cursor tro = mDatabase.Get("SELECT * FROM TaiKhoan WHERE SDT='" + SDT + "'");
        while (tro.moveToNext()) {
            return true;
        }
        return false;
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
                    tro.getString(5),
                    tro.getDouble(6),
                    tro.getDouble(7),
                    tro.getString(8));
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

    public void CapNhatViTriTK(int IDTK, String ViTri, double LatPos, double LongPos) {
        mDatabase.Query("UPDATE TaiKhoan SET ViTri = " + ViTri + ", ViDo = "
                + LatPos + " , KinhDo = " + LongPos + " WHERE IDTK = " + IDTK);
    }

    public void CapNhatHinhTK(int IDTK, byte[] HinhTK) {
        String sql = "UPDATE TaiKhoan SET HinhTK = ? WHERE IDTK = " + IDTK;
        SQLiteDatabase database = mDatabase.WritableData();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, HinhTK);
        statement.executeInsert();
    }

    public void TaoTK(String SDT, String MK, String TENTK, String QUYEN) {
        mDatabase.Query("INSERT INTO TaiKhoan(SDT, MATKHAU, TENTK, QUYEN) VALUES ('"
                + SDT + "','" + MK + "','" + TENTK + "','" + QUYEN + "')");
    }
    // endregion


    // region Quán Ăn
    public quanan QA(int IDQA){

        List<monan> list = new ArrayList<>();
        Cursor tro1 = mDatabase.Get("SELECT * FROM MonAn WHERE IDQA = " + IDQA);
        while (tro1.moveToNext()){
            list.add(new monan(tro1.getInt(1),
                        tro1.getString(3),
                    tro1.getDouble(4)));
        }

        Cursor tro2 = mDatabase.Get("SELECT * FROM QuanAn WHERE IDQA = " + IDQA);
        while (tro2.moveToNext()) {
            return new quanan(
                    tro2.getInt(0),
                    tro2.getString(1),
                    null,
                    new vitri(tro2.getString(3),0,0),
                    list
            );
        }
        return null;
    }

    public List<quanan> ListQAGanBan(){
        List<quanan> list = new ArrayList<>();
        Cursor tro = mDatabase.Get("SELECT * FROM QuanAn LIMIT 8");
        while (tro.moveToNext()) {
            list.add(new quanan(
                    tro.getInt(0),
                    tro.getString(1),
                    null,
                    new vitri(tro.getString(3),0,0)
            ));
        }
        return list;
    }

    // endregion

    // region Món Ăn


    // endregion
}
