package com.example.asm_mob204.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_mob204.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private MySQLite mySQLite;

    public KhachHangDAO(MySQLite mySQLite) {
        this.mySQLite = mySQLite;
    }

    public boolean xoaKhachHang(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        long kq = sqLiteDatabase.delete("khachhang", "makhachhang=?", new String[]{id});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean themKhachHang(KhachHang khachHang) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("makhachhang", khachHang.getMaKhachHang());
        contentValues.put("tenkhachhang", khachHang.getTenKhachHang());
        contentValues.put("sodienthoaikhachhang", khachHang.getSoDienThoaiKhachHang());
        long kq = sqLiteDatabase.insert("khachhang", null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> khachHangList = new ArrayList<>();
        String truyVan = "SELECT * FROM khachhang";
        Cursor cursor = mySQLite.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String maKhachHang = cursor.getString(cursor.getColumnIndex("makhachhang"));
                String tenKhachHang = cursor.getString(cursor.getColumnIndex("tenkhachhang"));
                String soDienThoaiKhachHang = cursor.getString(cursor.getColumnIndex("sodienthoaikhachhang"));
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(maKhachHang);
                khachHang.setTenKhachHang(tenKhachHang);
                khachHang.setSoDienThoaiKhachHang(soDienThoaiKhachHang);
                khachHangList.add(khachHang);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khachHangList;
    }

    public boolean suaKhachHang(KhachHang khachHang) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenkhachhang", khachHang.getTenKhachHang());
        contentValues.put("sodienthoaikhachhang", khachHang.getSoDienThoaiKhachHang());
        long kq = sqLiteDatabase.update("khachhang", contentValues, "makhachhang=?", new String[]{khachHang.getMaKhachHang()});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<KhachHang> timKiemKhachHang(String timSDT) {
        List<KhachHang> khachHangList = new ArrayList<>();
        String sql = "SELECT * FROM khachhang WHERE sodienthoaikhachhang LIKE '%" + timSDT + "%'";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maKhachHang = cursor.getString(cursor.getColumnIndex("makhachhang"));
                String tenKhachHang = cursor.getString(cursor.getColumnIndex("tenkhachhang"));
                String soDienThoaiKhachHang = cursor.getString(cursor.getColumnIndex("sodienthoaikhachhang"));
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(maKhachHang);
                khachHang.setTenKhachHang(tenKhachHang);
                khachHang.setSoDienThoaiKhachHang(soDienThoaiKhachHang);
                khachHangList.add(khachHang);
                cursor.moveToNext();
            }
        }
        return khachHangList;
    }
}
