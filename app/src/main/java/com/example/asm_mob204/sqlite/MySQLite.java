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
        String khach_hang = "CREATE TABLE khachhang (makhachhang char primary key not null," +
                "tenkhachhang nvarchar," +
                "sodienthoaikhachhang nvarchar " +
                ")";
        String sach = "CREATE TABLE sach (masach nvarchar(5) primary key not null," +
                "tensach char," +
                "giasach char ," +
                "tacgia char," +
                "nhaxuatban char," +
                "idtheloai char," +
                "soluong char ," +
                "thoigiannhap char," +
                "issale " +
                ")";
        String hoa_don = "CREATE TABLE hoadon (mahoadon nvarchar(5) primary key not null," +
                "ngaytao date not null," +
                "tennguoimua char," +
                "tennguoitao char," +
                "tensach char ," +
                "gia char ," +
                "soluong char ," +
                "tongtien char," +
                "FOREIGN KEY(tennguoimua) REFERENCES khachhang(makhachang)," +
                "FOREIGN KEY(tennguoitao) REFERENCES nguoidung(tentaikhoan)," +
                "FOREIGN KEY(tensach) REFERENCES sach (masach)" +
                ")";
        String the_loai = "CREATE TABLE theloai (matheloai nvarchar(5) primary key not null," +
                "tentheloai char," +
                "ngaythem date " +
                ")";
        String nguoi_dung = "CREATE TABLE nguoidung (tentaikhoan nvarchar(50) primary key not null," +
                "hovaten char," +
                "matkhau char," +
                "email char," +
                "sodienthoai char(10) ," +
                "ngaysinh date ," +
                "gioitinh char," +
                "diachi char" +
                ")";
        sqLiteDatabase.execSQL(khach_hang);
        sqLiteDatabase.execSQL(sach);
        sqLiteDatabase.execSQL(hoa_don);
        sqLiteDatabase.execSQL(the_loai);
        sqLiteDatabase.execSQL(nguoi_dung);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"khachhang");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"sach");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"hoadon");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"theloai");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"nguoidung");
    }
}
