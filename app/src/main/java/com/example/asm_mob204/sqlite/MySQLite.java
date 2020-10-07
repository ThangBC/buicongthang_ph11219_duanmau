package com.example.asm_mob204.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLite extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "qls";
    SQLiteDatabase db1;
    Context context;
    public MySQLite( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        db1 = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String khach_hang = "CREATE TABLE khachhang (makhachhang char(5) primary key not null," +
                "tenkhachhang nvarchar(50)," +
                "sodienthoaikhachhang nvarchar(10) not null" +
                ")";
        String sach = "CREATE TABLE sach (masach nvarchar(5) primary key not null," +
                "tensach char," +
                "giasach FLOAT not null," +
                "tacgia char," +
                "nhaxuatban char," +
                "idtheloai nvarchar(5)," +
                "soluong INT not null," +
                "thoigiannhap date not null" +
                ")";
        String hoa_don = "CREATE TABLE hoadon (mahoadon nvarchar(5) primary key not null," +
                "ngaytao date not null," +
                "tennguoimua char," +
                "tennguoitao char," +
                "tensach char," +
                "gia FLOAT not null," +
                "soluong INT not null" +
                ")";
        String the_loai = "CREATE TABLE theloai (matheloai nvarchar(5) primary key not null," +
                "tentheloai char," +
                "ngaythem date not null" +
                ")";
        String tai_khoan = "CREATE TABLE taikhoan(tentaikhoan nvarchar(50) primary key not null," +
                "hovaten char," +
                "matkhau char," +
                "email char," +
                "sodienthoai char(10) not null," +
                "ngaysinh date not null," +
                "gioitinh char" +
                ")";
        sqLiteDatabase.execSQL(khach_hang);
        sqLiteDatabase.execSQL(sach);
        sqLiteDatabase.execSQL(hoa_don);
        sqLiteDatabase.execSQL(the_loai);
        sqLiteDatabase.execSQL(tai_khoan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
