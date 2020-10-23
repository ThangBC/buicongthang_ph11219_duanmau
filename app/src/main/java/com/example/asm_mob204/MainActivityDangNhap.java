package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.NguoiDungDAO;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivityDangNhap extends AppCompatActivity {
    public static TextInputLayout txtUserName, txtPassWord;
    Button btnDangNhap, btnDangKy;
    CheckBox ckbNhoMatKhau;
    TextView tvQuenMatKhau;
    MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQuenMatKhau = findViewById(R.id.tvQuenMatKhau);
        txtUserName = findViewById(R.id.txtUserNameDangNhap);
        txtPassWord = findViewById(R.id.txtPassWordDangNhap);
        ckbNhoMatKhau = findViewById(R.id.ckbNhoMatKhau);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        mySQLite = new MySQLite(this);
        final SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true")){
            Intent intent = new Intent(MainActivityDangNhap.this,MainActivityHome.class);
            startActivity(intent);
        }
        ckbNhoMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences1 = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(MainActivityDangNhap.this, "Đã nhớ", Toast.LENGTH_SHORT).show();
                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences2 = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = preferences2.edit();
                    editor1.putString("remember","false");
                    editor1.apply();
                    Toast.makeText(MainActivityDangNhap.this, "Đã bỏ nhớ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDangNhap.this, MainActivityDangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(mySQLite);
                boolean kq = nguoiDungDAO.dangNhap(txtUserName.getEditText().getText().toString().trim(),
                        txtPassWord.getEditText().getText().toString().trim());
                if (kq) {
                    Toast.makeText(MainActivityDangNhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityDangNhap.this, MainActivityHome.class);
                    startActivity(intent);
                } else if (txtUserName.getEditText().getText().toString().trim().equals("admin") &&
                        txtPassWord.getEditText().getText().toString().trim().equals("admin")) {
                    Toast.makeText(MainActivityDangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityDangNhap.this, MainActivityHome.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivityDangNhap.this, "Sai username hoặc password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDangNhap.this, MainActivityQuenMatKhau.class);
                startActivity(intent);
            }
        });
    }
}