package com.example.asm_mob204.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.asm_mob204.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private MySQLite mySQLite;

    public HoaDonDAO(MySQLite mySQLite) {
        this.mySQLite = mySQLite;
    }

    public boolean xoaHoaDon(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        long kq = sqLiteDatabase.delete("hoadon", "mahoadon=?", new String[]{id});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean themHoaDon(HoaDon hoaDon) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDon.getMaHoaDon());
        contentValues.put("ngaytao", hoaDon.getNgayTao());
        contentValues.put("tenguoimua", hoaDon.getTenNguoiMua());
        contentValues.put("tenguoitao", hoaDon.getTenNguoiTao());
        contentValues.put("tensach", hoaDon.getTenSach());
        contentValues.put("gia", hoaDon.getGia());
        contentValues.put("soluong", hoaDon.getSoLuong());
        long kq = sqLiteDatabase.insert("hoadon", null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        String truyVan = "SELECT * FROM hoadon";
        Cursor cursor = mySQLite.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String maHoaDon = cursor.getString(cursor.getColumnIndex("mahoadon"));
                String ngayTao = cursor.getString(cursor.getColumnIndex("ngaytao"));
                String tenNguoiMua = cursor.getString(cursor.getColumnIndex("tenguoimua"));
                String tenNguoiTao = cursor.getString(cursor.getColumnIndex("tenguoitao"));
                String tenSach = cursor.getString(cursor.getColumnIndex("tensach"));
                String gia = cursor.getString(cursor.getColumnIndex("gia"));
                String soLuong = cursor.getString(cursor.getColumnIndex("soluong"));
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(maHoaDon);
                hoaDon.setNgayTao(ngayTao);
                hoaDon.setTenNguoiMua(tenNguoiMua);
                hoaDon.setTenNguoiTao(tenNguoiTao);
                hoaDon.setTenSach(tenSach);
                hoaDon.setGia(Double.parseDouble(gia));
                hoaDon.setSoLuong(Integer.parseInt(soLuong));
                hoaDonList.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hoaDonList;
    }

    public boolean suaHoaDon(HoaDon hoaDon) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ngaytao", hoaDon.getNgayTao());
        contentValues.put("tenguoimua", hoaDon.getTenNguoiMua());
        contentValues.put("tenguoitao", hoaDon.getTenNguoiTao());
        contentValues.put("tensach", hoaDon.getTenSach());
        contentValues.put("gia", hoaDon.getGia());
        contentValues.put("soluong", hoaDon.getSoLuong());
        long kq = sqLiteDatabase.update("hoadon", contentValues, "mahoadon=?", new String[]{hoaDon.getMaHoaDon()});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }
}
