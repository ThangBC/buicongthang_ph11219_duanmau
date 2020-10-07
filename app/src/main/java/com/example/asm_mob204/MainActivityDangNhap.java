package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;


public class MainActivityDangNhap extends AppCompatActivity {
    TextInputLayout txtUserName,txtPassWord;
    Button btnDangNhap,btnDangKy;
    CheckBox ckbNhoMatKhau;
    TextView tvQuenMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQuenMatKhau = findViewById(R.id.tvQuenMatKhau);
        txtUserName = findViewById(R.id.txtUserNameDangNhap);
        txtPassWord =findViewById(R.id.txtPassWordDangNhap);
        ckbNhoMatKhau = findViewById(R.id.ckbNhoMatKhau);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDangNhap.this,MainActivityDangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDangNhap.this,MainActivityHome.class);
                startActivity(intent);
            }
        });
        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDangNhap.this,MainActivityQuenMatKhau.class);
                startActivity(intent);
            }
        });
    }
}