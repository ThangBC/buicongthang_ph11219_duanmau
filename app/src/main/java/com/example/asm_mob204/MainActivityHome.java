package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivityHome extends AppCompatActivity {
    ImageView imgQuanLyNguoiDung, imgQuanLySach,imgQuanLyHoaDon,imgQuanLyTheLoai, imgThongKe,imgCaiDat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        imgQuanLyNguoiDung = findViewById(R.id.imgQuanLyNguoiDung);
        imgQuanLySach = findViewById(R.id.imgQuanLySach);
        imgQuanLyHoaDon = findViewById(R.id.imgQuanLyHoaDon);
        imgQuanLyTheLoai = findViewById(R.id.imgQuanLyTheLoai);
        imgThongKe = findViewById(R.id.imgThongKe);
        imgCaiDat = findViewById(R.id.imgSetting);
        imgQuanLyNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityQuanLyNguoiDung.class);
                startActivity(intent);
            }
        });
        imgQuanLySach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityQuanLySach.class);
                startActivity(intent);
            }
        });
        imgQuanLyHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityQuanLyHoaDon.class);
                startActivity(intent);
            }
        });
        imgQuanLyTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityQuanLyTheLoai.class);
                startActivity(intent);
            }
        });
        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityThongKe.class);
                startActivity(intent);
            }
        });
        imgCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityCaiDat.class);
                startActivity(intent);
            }
        });
    }
}