package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivityQuanLySach extends AppCompatActivity {
    ImageView imgNhapSach,imgDanhSachSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_sach);
        imgNhapSach =findViewById(R.id.imgNhapSach);
        imgDanhSachSach =findViewById(R.id.imgDanhSachSach);
        imgNhapSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLySach.this,MainActivityNhapSach.class);
                startActivity(intent);
            }
        });
        imgDanhSachSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityQuanLySach.this,MainActivityDanhSachSach.class);
                startActivity(intent);
            }
        });
    }
}