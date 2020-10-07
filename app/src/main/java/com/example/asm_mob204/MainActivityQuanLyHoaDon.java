package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivityQuanLyHoaDon extends AppCompatActivity {
    ImageView imgTaoHoaDon,imgDanhSachHoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_hoa_don);
        imgTaoHoaDon = findViewById(R.id.imgTaoHoaDon);
        imgDanhSachHoaDon = findViewById(R.id.imgDanhSachHoaDon);
        imgTaoHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyHoaDon.this,MainActivityTaoHoaDon.class);
                startActivity(intent);
            }
        });
        imgDanhSachHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyHoaDon.this,MainActivityDanhSachHoaDon.class);
                startActivity(intent);
            }
        });
    }
}