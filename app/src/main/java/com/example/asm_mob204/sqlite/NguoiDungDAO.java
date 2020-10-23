package com.example.asm_mob204.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private MySQLite mySQLite;

    public NguoiDungDAO(MySQLite mySQLite) {
        this.mySQLite = mySQLite;
    }

    public boolean xoaNguoiDung(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        long kq = sqLiteDatabase.delete("nguoidung", "tentaikhoan=?", new String[]{id});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean themNguoiDung(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentaikhoan", nguoiDung.getTenTaiKhoan());
        contentValues.put("hovaten", nguoiDung.getHoVaTen());
        contentValues.put("matkhau", nguoiDung.getMatKhau());
        contentValues.put("email", nguoiDung.getEmai());
        contentValues.put("sodienthoai", nguoiDung.getSoDienThoai());
        contentValues.put("ngaysinh", nguoiDung.getNgaySinh());
        contentValues.put("gioitinh", nguoiDung.getGioiTinh());
        contentValues.put("diachi", nguoiDung.getDiaChi());
        long kq = sqLiteDatabase.insert("nguoidung", null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<NguoiDung> getAllNguoiDung() {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        String truyVan = "SELECT * FROM nguoidung";
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
                String diaChi = cursor.getString(cursor.getColumnIndex("diachi"));
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setTenTaiKhoan(tenTaiKhoan);
                nguoiDung.setHoVaTen(hoVaTen);
                nguoiDung.setMatKhau(matKhau);
                nguoiDung.setEmai(email);
                nguoiDung.setSoDienThoai(soDienThoai);
                nguoiDung.setNgaySinh(ngaySinh);
                nguoiDung.setGioiTinh(gioiTinh);
                nguoiDung.setDiaChi(diaChi);
                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return nguoiDungList;
    }

    public boolean suaNguoiDung(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hovaten", nguoiDung.getHoVaTen());
        contentValues.put("matkhau", nguoiDung.getMatKhau());
        contentValues.put("email", nguoiDung.getEmai());
        contentValues.put("sodienthoai", nguoiDung.getSoDienThoai());
        contentValues.put("ngaysinh", nguoiDung.getNgaySinh());
        contentValues.put("gioitinh", nguoiDung.getGioiTinh());
        contentValues.put("diachi", nguoiDung.getDiaChi());
        long kq = sqLiteDatabase.update("nguoidung", contentValues, "tentaikhoan=?", new String[]{nguoiDung.getTenTaiKhoan()});
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }
    public List<NguoiDung> timKiemNguoiDung(String timHovaTen) {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        String sql = "SELECT * FROM nguoidung WHERE hovaten LIKE '%" + timHovaTen + "%'";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenTaiKhoan = cursor.getString(cursor.getColumnIndex("tentaikhoan"));
                String HoVaTen = cursor.getString(cursor.getColumnIndex("hovaten"));
                String MatKhau = cursor.getString(cursor.getColumnIndex("matkhau"));
                String Email = cursor.getString(cursor.getColumnIndex("email"));
                String soDienThoai = cursor.getString(cursor.getColumnIndex("sodienthoai"));
                String ngaySinh = cursor.getString(cursor.getColumnIndex("ngaysinh"));
                String gioiTinh = cursor.getString(cursor.getColumnIndex("gioitinh"));
                String diaChi = cursor.getString(cursor.getColumnIndex("diachi"));
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setTenTaiKhoan(tenTaiKhoan);
                nguoiDung.setHoVaTen(HoVaTen);
                nguoiDung.setMatKhau(MatKhau);
                nguoiDung.setEmai(Email);
                nguoiDung.setSoDienThoai(soDienThoai);
                nguoiDung.setNgaySinh(ngaySinh);
                nguoiDung.setGioiTinh(gioiTinh);
                nguoiDung.setDiaChi(diaChi);
                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }
        return nguoiDungList;
    }
    public boolean dangNhap(String userName,String passWord){
        String sql = "SELECT * FROM nguoidung WHERE tentaikhoan = '" + userName + "' AND matkhau = '"+passWord+"'";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public NguoiDung gettOneNguoiDung(String userName, String passWord){
        NguoiDung nguoiDung = new NguoiDung();
        String sql = "SELECT * FROM nguoidung WHERE tentaikhoan = '" + userName + "' AND matkhau = '"+passWord+"'";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenTaiKhoan = cursor.getString(cursor.getColumnIndex("tentaikhoan"));
                String HoVaTen = cursor.getString(cursor.getColumnIndex("hovaten"));
                String MatKhau = cursor.getString(cursor.getColumnIndex("matkhau"));
                String Email = cursor.getString(cursor.getColumnIndex("email"));
                String soDienThoai = cursor.getString(cursor.getColumnIndex("sodienthoai"));
                String ngaySinh = cursor.getString(cursor.getColumnIndex("ngaysinh"));
                String gioiTinh = cursor.getString(cursor.getColumnIndex("gioitinh"));
                String diaChi = cursor.getString(cursor.getColumnIndex("diachi"));
                nguoiDung.setTenTaiKhoan(tenTaiKhoan);
                nguoiDung.setHoVaTen(HoVaTen);
                nguoiDung.setMatKhau(MatKhau);
                nguoiDung.setEmai(Email);
                nguoiDung.setSoDienThoai(soDienThoai);
                nguoiDung.setNgaySinh(ngaySinh);
                nguoiDung.setGioiTinh(gioiTinh);
                nguoiDung.setDiaChi(diaChi);
                cursor.moveToNext();
            }
        }
        return nguoiDung;
    }
}
