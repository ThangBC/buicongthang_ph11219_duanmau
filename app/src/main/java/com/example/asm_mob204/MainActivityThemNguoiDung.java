package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob204.adapter.KhachHangAdapter;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.sqlite.KhachHangDAO;
import com.example.asm_mob204.sqlite.MySQLite;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivityThemNguoiDung extends AppCompatActivity {
    TextInputLayout txtMaNguoiDungThemNguoiDung, txtTenNguoiDungThemNguoiDung, txtSoDienThoaiThemNguoiDung;
    Button btnThemNguoiDung;
    private MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_nguoi_dung);
        txtMaNguoiDungThemNguoiDung = findViewById(R.id.txtMaNguoiDungThemNguoiDung);
        txtTenNguoiDungThemNguoiDung = findViewById(R.id.txtTenNguoiDungThemNguoiDung);
        txtSoDienThoaiThemNguoiDung = findViewById(R.id.txtSoDienThoaiThemNguoiDung);
        btnThemNguoiDung = findViewById(R.id.btnThemNguoiDung);
        mySQLite = new MySQLite(this);
        btnThemNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(txtMaNguoiDungThemNguoiDung.getEditText().getText().toString().trim());
                khachHang.setTenKhachHang(txtTenNguoiDungThemNguoiDung.getEditText().getText().toString().trim());
                khachHang.setSoDienThoaiKhachHang(txtSoDienThoaiThemNguoiDung.getEditText().getText().toString().trim());
                checkEmty(khachHang.getMaKhachHang(),txtMaNguoiDungThemNguoiDung);
                checkEmty(khachHang.getTenKhachHang(),txtTenNguoiDungThemNguoiDung);
                checkEmty(khachHang.getSoDienThoaiKhachHang(),txtSoDienThoaiThemNguoiDung);
                KhachHangDAO khachHangDAO =new KhachHangDAO(mySQLite);
                boolean ketQua = khachHangDAO.themKhachHang(khachHang);
                if (ketQua) {
                    Toast.makeText(MainActivityThemNguoiDung.this, "Thêm thành công !!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivityThemNguoiDung.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void checkEmty(String data, TextInputLayout txt){
        if(data.isEmpty()){
            txt.setError("Mời nhập đủ thông tin");
            return;
        }
    }
}