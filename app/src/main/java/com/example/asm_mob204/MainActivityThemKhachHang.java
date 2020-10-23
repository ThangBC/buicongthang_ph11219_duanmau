package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.sqlite.KhachHangDAO;
import com.example.asm_mob204.sqlite.MySQLite;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivityThemKhachHang extends AppCompatActivity {
    TextInputLayout txtMaKhachHangThemKhachHang, txtTenKhachHangThemKhachHang, txtSoDienThoaiThemKhachHang;
    Button btnThemKhachHang;
    private MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_khach_hang);
        txtMaKhachHangThemKhachHang = findViewById(R.id.txtMaKhachHangThemKhachHang);
        txtTenKhachHangThemKhachHang = findViewById(R.id.txtTenKhachHangThemKhachHang);
        txtSoDienThoaiThemKhachHang = findViewById(R.id.txtSoDienThoaiThemKhachHang);
        btnThemKhachHang = findViewById(R.id.btnThemKhachHang);
        mySQLite = new MySQLite(this);
        btnThemKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(txtMaKhachHangThemKhachHang.getEditText().getText().toString().trim());
                khachHang.setTenKhachHang(txtTenKhachHangThemKhachHang.getEditText().getText().toString().trim());
                khachHang.setSoDienThoaiKhachHang(txtSoDienThoaiThemKhachHang.getEditText().getText().toString().trim());
                boolean checkMaKhachHang = checkEmpty(khachHang.getMaKhachHang(), txtMaKhachHangThemKhachHang);
                boolean checkTenKhachHang = checkEmpty(khachHang.getTenKhachHang(), txtTenKhachHangThemKhachHang);
                boolean checkSoDienThoaiKhachHang = checkEmpty(khachHang.getSoDienThoaiKhachHang(), txtSoDienThoaiThemKhachHang);
                if ( checkMaKhachHang && checkTenKhachHang && checkSoDienThoaiKhachHang) {
                    KhachHangDAO khachHangDAO = new KhachHangDAO(mySQLite);
                    boolean ketQua = khachHangDAO.themKhachHang(khachHang);
                    if (ketQua) {
                        Toast.makeText(MainActivityThemKhachHang.this, "Thêm thành công !!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivityThemKhachHang.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivityThemKhachHang.this, "Bạn nhập chưa đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkEmpty(String data, TextInputLayout txt) {
        if (data.isEmpty()) {
            txt.setError("Mời nhập đủ thông tin");
            return false;
        } else {
            return true;
        }
    }
}