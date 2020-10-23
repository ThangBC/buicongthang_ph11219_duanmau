package com.example.asm_mob204;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivityCaiDat extends AppCompatActivity {
    ImageView imgThongTinTaiKhoan,imgDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cai_dat);
        imgThongTinTaiKhoan = findViewById(R.id.imgThongTinTaiKhoan);
        imgDangXuat = findViewById(R.id.imgDangXuat);
        imgThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityCaiDat.this,MainActivityThongTinTaiKhoan.class);
                startActivity(intent);
            }
        });
        imgDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityCaiDat.this);
                builder.setTitle("Bạn có chắc muốn đăng xuất ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        Intent intent = new Intent(MainActivityCaiDat.this,MainActivityDangNhap.class);
                        startActivity(intent);
                        Toast.makeText(MainActivityCaiDat.this, "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
}