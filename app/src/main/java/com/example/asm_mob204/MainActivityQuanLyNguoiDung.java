package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivityQuanLyNguoiDung extends AppCompatActivity {
    ImageView imgThemKhachHang,imgDanhSachKhachHang,imgDanhSachNguoiDung;
    LinearLayout linerDanhSachNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_nguoi_dung);
        imgThemKhachHang = findViewById(R.id.imgThemKhachHang);
        imgDanhSachKhachHang = findViewById(R.id.imgDanhSachKhachHang);
        imgDanhSachNguoiDung = findViewById(R.id.imgDanhSachNguoiDung);
        linerDanhSachNguoiDung = findViewById(R.id.linerDanhSachNguoiDung);
        if(MainActivityDangNhap.txtUserName.getEditText().getText().toString().trim().equalsIgnoreCase("admin")&&
                MainActivityDangNhap.txtPassWord.getEditText().getText().toString().trim().equalsIgnoreCase("admin")){
            linerDanhSachNguoiDung.setVisibility(View.VISIBLE);
        }
        imgThemKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyNguoiDung.this, MainActivityThemKhachHang.class);
                startActivity(intent);
            }
        });
        imgDanhSachKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyNguoiDung.this, MainActivityDanhSachKhachHang.class);
                startActivity(intent);
            }
        });
        imgDanhSachNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyNguoiDung.this, MainActivityDanhSachNguoiDung.class);
                startActivity(intent);
            }
        });

    }
}