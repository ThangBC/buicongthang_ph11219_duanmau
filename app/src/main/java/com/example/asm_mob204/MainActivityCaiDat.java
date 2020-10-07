package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivityCaiDat extends AppCompatActivity {
    ImageView imgThongTinTaiKhoan,imgDoiMatKhau,imgDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cai_dat);
        imgThongTinTaiKhoan = findViewById(R.id.imgThongTinTaiKhoan);
        imgDoiMatKhau = findViewById(R.id.imgDoiMatKhau);
        imgDangXuat = findViewById(R.id.imgDangXuat);
        imgThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityCaiDat.this,MainActivityThongTinTaiKhoan.class);
                startActivity(intent);
            }
        });
        imgDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityCaiDat.this,MainActivityDoiMatKhau.class);
                startActivity(intent);
            }
        });
        imgDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}