package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivityThongKe extends AppCompatActivity {
    ImageView imgThongKeDoanhThu,imgSachBanChay,imgTonKho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_ke);
        imgThongKeDoanhThu = findViewById(R.id.imgThongKeDoanhThu);
        imgSachBanChay = findViewById(R.id.imgSachBanChay);
        imgTonKho = findViewById(R.id.imgTonKho);
        imgThongKeDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityThongKe.this,MainActivityThongKeDoanhThu.class);
                startActivity(intent);
            }
        });
        imgSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityThongKe.this,MainActivitySachBanChay.class);
                startActivity(intent);
            }
        });
        imgTonKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityThongKe.this,MainActivitySachTonKho.class);
                startActivity(intent);
            }
        });
    }
}