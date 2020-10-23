package com.example.asm_mob204.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private MySQLite mySQLite;

    public TheLoaiDAO(MySQLite mySQLite) {
        this.mySQLite = mySQLite;
    }

    public boolean xoaTheLoai(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        long kq = sqLiteDatabase.delete("theloai", "matheloai=?", new String[]{id});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean themTheLoai(TheLoai theLoai) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matheloai", theLoai.getMaTheLoai());
        contentValues.put("tentheloai", theLoai.getTenTheLoai());
        contentValues.put("ngaythem", theLoai.getNgayThem());
        long kq = sqLiteDatabase.insert("theloai", null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<TheLoai> getAllTheLoai() {
        List<TheLoai> theLoaiList = new ArrayList<>();
        String truyVan = "SELECT * FROM theloai";
        Cursor cursor = mySQLite.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String maTheLoai = cursor.getString(cursor.getColumnIndex("matheloai"));
                String tenTheLoai = cursor.getString(cursor.getColumnIndex("tentheloai"));
                String ngayThem = cursor.getString(cursor.getColumnIndex("ngaythem"));
                TheLoai theLoai = new TheLoai();
                theLoai.setMaTheLoai(maTheLoai);
                theLoai.setTenTheLoai(tenTheLoai);
                theLoai.setNgayThem(ngayThem);
                theLoaiList.add(theLoai);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return theLoaiList;
    }

    public boolean suaTheLoai(TheLoai theLoai) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentheloai", theLoai.getTenTheLoai());
        contentValues.put("ngaythem", theLoai.getNgayThem());
        long kq = sqLiteDatabase.update("theloai", contentValues, "matheloai=?", new String[]{theLoai.getMaTheLoai()});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }
    public List<TheLoai> timKiemTheLoai(String timNgayThem) {
        List<TheLoai> theLoaiList = new ArrayList<>();
        String sql = "SELECT * FROM theloai WHERE ngaythem LIKE '%" + timNgayThem + "%'";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maTheLoai = cursor.getString(cursor.getColumnIndex("matheloai"));
                String tenTheLoai = cursor.getString(cursor.getColumnIndex("tentheloai"));
                String ngayThemTheLoai = cursor.getString(cursor.getColumnIndex("ngaythem"));
                TheLoai theLoai = new TheLoai();
                theLoai.setMaTheLoai(maTheLoai);
                theLoai.setTenTheLoai(tenTheLoai);
                theLoai.setNgayThem(ngayThemTheLoai);
                theLoaiList.add(theLoai);
                cursor.moveToNext();
            }
        }
        return theLoaiList;
    }
}
