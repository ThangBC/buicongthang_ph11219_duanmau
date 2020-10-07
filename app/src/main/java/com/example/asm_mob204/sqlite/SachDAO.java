package com.example.asm_mob204.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_mob204.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private MySQLite mySQLite;

    public SachDAO(MySQLite mySQLite) {
        this.mySQLite = mySQLite;
    }

    public boolean xoaSach(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();

        long kq = sqLiteDatabase.delete("sach", "masach=?", new String[]{id});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean themSach(Sach sach) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masach", sach.getMaSach());
        contentValues.put("tensach", sach.getTenSach());
        contentValues.put("giasach", sach.getGiaSach());
        contentValues.put("tacgia", sach.getTacGia());
        contentValues.put("nhaxuatban", sach.getNhaXuatBan());
        contentValues.put("idtheloai", sach.getTheLoai());
        contentValues.put("soluong", sach.getSoLuong());
        contentValues.put("thoigiannhap", sach.getThoiGianNhap());
        long kq = sqLiteDatabase.insert("sach", null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Sach> getAllSach() {
        List<Sach> sachList = new ArrayList<>();
        String truyVan = "SELECT * FROM sach";
        Cursor cursor = mySQLite.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String maSach = cursor.getString(cursor.getColumnIndex("masach"));
                String tenSach = cursor.getString(cursor.getColumnIndex("tensach"));
                String tacGia = cursor.getString(cursor.getColumnIndex("tacgia"));
                String nhaXuatBan = cursor.getString(cursor.getColumnIndex("nhaxuatban"));
                String idTheLoai = cursor.getString(cursor.getColumnIndex("idtheloai"));
                String soLuong = cursor.getString(cursor.getColumnIndex("soluong"));
                String thoiGianNhap = cursor.getString(cursor.getColumnIndex("thoigiannhap"));
                Sach sach = new Sach();
                sach.setMaSach(maSach);
                sach.setTenSach(tenSach);
                sach.setTacGia(tacGia);
                sach.setNhaXuatBan(nhaXuatBan);
                sach.setTheLoai(idTheLoai);
                sach.setSoLuong(Integer.parseInt(soLuong));
                sach.setThoiGianNhap(thoiGianNhap);
                sachList.add(sach);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return sachList;
    }

    public boolean suaSach(Sach sach) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach", sach.getTenSach());
        contentValues.put("giasach", sach.getGiaSach());
        contentValues.put("tacgia", sach.getTacGia());
        contentValues.put("nhaxuatban", sach.getNhaXuatBan());
        contentValues.put("idtheloai", sach.getTheLoai());
        contentValues.put("soluong", sach.getSoLuong());
        contentValues.put("thoigiannhap", sach.getThoiGianNhap());
        long kq = sqLiteDatabase.update("sach", contentValues, "masach=?", new String[]{sach.getMaSach()});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }
}
