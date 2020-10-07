package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivityQuanLyTheLoai extends AppCompatActivity {
    ImageView imgThemTheLoai,imgDanhSachTheLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_the_loai);
        imgThemTheLoai = findViewById(R.id.imgThemTheLoai);
        imgDanhSachTheLoai = findViewById(R.id.imgDanhSachTheLoai);
        imgThemTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyTheLoai.this,MainActivityThemTheLoai.class);
                startActivity(intent);
            }
        });
        imgDanhSachTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLyTheLoai.this,MainActivityDanhSachTheLoai.class);
                startActivity(intent);
            }
        });
    }
}