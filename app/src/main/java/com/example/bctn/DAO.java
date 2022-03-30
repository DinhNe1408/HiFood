package com.example.bctn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.bctn.domain.taikhoan;

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
}
