package com.example.asm_mob204.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.asm_mob204.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    private MySQLite mySQLite;

    public TaiKhoanDAO(MySQLite mySQLite) {
        this.mySQLite = mySQLite;
    }

    public boolean xoaHoaDon(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        long kq = sqLiteDatabase.delete("taikhoan", "tentaikhoan=?", new String[]{id});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean themTaiKhoan(TaiKhoan taiKhoan) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentaikhoan", taiKhoan.getTenTaiKhoan());
        contentValues.put("hovaten", taiKhoan.getHoVaTen());
        contentValues.put("matkhau", taiKhoan.getMatKhau());
        contentValues.put("email", taiKhoan.getEmai());
        contentValues.put("sodienthoai", taiKhoan.getSoDienThoai());
        contentValues.put("ngaysinh", taiKhoan.getNgaySinh());
        contentValues.put("gioitinh", taiKhoan.getGioiTinh());
        long kq = sqLiteDatabase.insert("taikhoan", null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> taiKhoanList = new ArrayList<>();
        String truyVan = "SELECT * FROM taikhoan";
        Cursor cursor = mySQLite.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String tenTaiKhoan = cursor.getString(cursor.getColumnIndex("tentaikhoan"));
                String hoVaTen = cursor.getString(cursor.getColumnIndex("hovaten"));
                String matKhau = cursor.getString(cursor.getColumnIndex("matkhau"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String soDienThoai = cursor.getString(cursor.getColumnIndex("sodienthoai"));
                String ngaySinh = cursor.getString(cursor.getColumnIndex("ngaysinh"));
                String gioiTinh = cursor.getString(cursor.getColumnIndex("gioitinh"));
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTenTaiKhoan(tenTaiKhoan);
                taiKhoan.setHoVaTen(hoVaTen);
                taiKhoan.setMatKhau(matKhau);
                taiKhoan.setEmai(email);
                taiKhoan.setSoDienThoai(soDienThoai);
                taiKhoan.setNgaySinh(ngaySinh);
                taiKhoan.setGioiTinh(gioiTinh);
                taiKhoanList.add(taiKhoan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return taiKhoanList;
    }

    public boolean suaTaiKhoan(TaiKhoan taiKhoan) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hovaten", taiKhoan.getHoVaTen());
        contentValues.put("matkhau", taiKhoan.getMatKhau());
        contentValues.put("email", taiKhoan.getEmai());
        contentValues.put("sodienthoai", taiKhoan.getSoDienThoai());
        contentValues.put("ngaysinh", taiKhoan.getNgaySinh());
        contentValues.put("gioitinh", taiKhoan.getGioiTinh());
        long kq = sqLiteDatabase.update("taikhoan", contentValues, "tentaikhoan=?", new String[]{taiKhoan.getTenTaiKhoan()});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }
}
