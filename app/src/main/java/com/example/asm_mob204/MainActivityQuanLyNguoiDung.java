package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivityQuanLyNguoiDung extends AppCompatActivity {
    ImageView imgThemNguoiDung,imgDanhSachNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_nguoi_dung);
        imgThemNguoiDung = findViewById(R.id.imgThemNguoiDung);
        imgDanhSachNguoiDung = findViewById(R.id.imgDanhSachNguoiDung);
        imgThemNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyNguoiDung.this,MainActivityThemNguoiDung.class);
                startActivity(intent);
            }
        });
        imgDanhSachNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyNguoiDung.this,MainActivityDanhSachNguoiDung.class);
                startActivity(intent);
            }
        });
    }
}