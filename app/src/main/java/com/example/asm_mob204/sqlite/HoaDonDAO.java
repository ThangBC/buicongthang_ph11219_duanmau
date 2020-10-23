package com.example.asm_mob204.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.asm_mob204.model.HoaDon;
import com.example.asm_mob204.model.Sach;

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
        contentValues.put("tennguoimua", hoaDon.getTenNguoiMua());
        contentValues.put("tennguoitao", hoaDon.getTenNguoiTao());
        contentValues.put("tensach", hoaDon.getTenSach());
        contentValues.put("gia", hoaDon.getGia());
        contentValues.put("soluong", hoaDon.getSoLuong());
        contentValues.put("tongtien", hoaDon.getTongTien());
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
                String tenNguoiMua = cursor.getString(cursor.getColumnIndex("tennguoimua"));
                String tenNguoiTao = cursor.getString(cursor.getColumnIndex("tennguoitao"));
                String tenSach = cursor.getString(cursor.getColumnIndex("tensach"));
                String gia = cursor.getString(cursor.getColumnIndex("gia"));
                String soLuong = cursor.getString(cursor.getColumnIndex("soluong"));
                String tongTien = cursor.getString(cursor.getColumnIndex("tongtien"));
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(maHoaDon);
                hoaDon.setNgayTao(ngayTao);
                hoaDon.setTenNguoiMua(tenNguoiMua);
                hoaDon.setTenNguoiTao(tenNguoiTao);
                hoaDon.setTenSach(tenSach);
                hoaDon.setGia(gia);
                hoaDon.setSoLuong(soLuong);
                hoaDon.setTongTien(tongTien);
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
        contentValues.put("tennguoimua", hoaDon.getTenNguoiMua());
        contentValues.put("tennguoitao", hoaDon.getTenNguoiTao());
        contentValues.put("tensach", hoaDon.getTenSach());
        contentValues.put("gia", hoaDon.getGia());
        contentValues.put("soluong", hoaDon.getSoLuong());
        contentValues.put("tongtien", hoaDon.getTongTien());
        long kq = sqLiteDatabase.update("hoadon", contentValues, "mahoadon=?", new String[]{hoaDon.getMaHoaDon()});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }
    public List<HoaDon> timKiemHoaDon(String timNgayTao) {
        List<HoaDon> hoaDonList = new ArrayList<>();
        String sql = "SELECT * FROM hoadon WHERE ngaytao LIKE '%" + timNgayTao + "%'";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maHoaDon = cursor.getString(cursor.getColumnIndex("mahoadon"));
                String ngayTao = cursor.getString(cursor.getColumnIndex("ngaytao"));
                String tenNguoiMua = cursor.getString(cursor.getColumnIndex("tennguoimua"));
                String tenNguoiTao = cursor.getString(cursor.getColumnIndex("tennguoitao"));
                String tenSach = cursor.getString(cursor.getColumnIndex("tensach"));
                String giaSach = cursor.getString(cursor.getColumnIndex("gia"));
                String soLuong = cursor.getString(cursor.getColumnIndex("soluong"));
                String tongTien = cursor.getString(cursor.getColumnIndex("tongtien"));
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(maHoaDon);
                hoaDon.setNgayTao(ngayTao);
                hoaDon.setTenNguoiMua(tenNguoiMua);
                hoaDon.setTenNguoiTao(tenNguoiTao);
                hoaDon.setTenSach(tenSach);
                hoaDon.setGia(giaSach);
                hoaDon.setSoLuong(soLuong);
                hoaDon.setTongTien(tongTien);
                hoaDonList.add(hoaDon);
                cursor.moveToNext();
            }
        }
        return hoaDonList;
    }
    public List<HoaDon> sachBanChay(String month){
        List<HoaDon> hoaDonList = new ArrayList<>();
        String sql = "SELECT tensach , SUM(soluong) as soluong FROM hoadon WHERE ngaytao LIKE '%/"+month+"/%' GROUP BY tensach ORDER BY soluong DESC LIMIT 10";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenSach = cursor.getString(cursor.getColumnIndex("tensach"));
                String soLuong = cursor.getString(cursor.getColumnIndex("soluong"));

                HoaDon hoaDon = new HoaDon();
                hoaDon.setTenSach(tenSach);
                hoaDon.setSoLuong(soLuong);
                hoaDonList.add(hoaDon);
                cursor.moveToNext();
            }
        }
        Log.e("AAAA",""+hoaDonList.size());
        return hoaDonList;
    }
}
