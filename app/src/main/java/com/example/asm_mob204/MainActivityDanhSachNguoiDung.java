package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.asm_mob204.adapter.KhachHangAdapter;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.sqlite.KhachHangDAO;
import com.example.asm_mob204.sqlite.MySQLite;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivityDanhSachNguoiDung extends AppCompatActivity {
    Button btnLocDanhSachNguoiDung;
    EditText txtLocSoDienThoaiNguoiDung;
    public static ListView lvDanhSachNguoiDung;
    List<KhachHang> khachHangList;
    KhachHangDAO khachHangDAO;
    private MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_sach_nguoi_dung);
        btnLocDanhSachNguoiDung = findViewById(R.id.btnLocDanhSachNguoiDung);
        txtLocSoDienThoaiNguoiDung = findViewById(R.id.txtLocSoDienThoaiNguoiDung);
        lvDanhSachNguoiDung = findViewById(R.id.lvDanhSachNguoiDung);
        mySQLite = new MySQLite(this);
        khachHangDAO = new KhachHangDAO(mySQLite);
        khachHangList = new ArrayList<>();
        khachHangList =khachHangDAO.getAllKhachHang();
        final KhachHangAdapter khachHangAdapter = new KhachHangAdapter(MainActivityDanhSachNguoiDung.this,khachHangList);
        lvDanhSachNguoiDung.setAdapter(khachHangAdapter);
        btnLocDanhSachNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SDT = txtLocSoDienThoaiNguoiDung.getText().toString().trim();
                if(SDT.isEmpty()){
                    txtLocSoDienThoaiNguoiDung.setError("Nhập dữ liệu trước");
                    return;
                }
                KhachHangDAO khachHangDAO = new KhachHangDAO(mySQLite);
                List<KhachHang> khachHangList1 = khachHangDAO.timKiemKhachHang(SDT);
                if(khachHangList1.size()==0){
                    txtLocSoDienThoaiNguoiDung.setError("Không tìm thấy !");
                }else{
                    KhachHangAdapter khachHangAdapter1 =new KhachHangAdapter(MainActivityDanhSachNguoiDung.this,khachHangList1);
                    lvDanhSachNguoiDung.setAdapter(khachHangAdapter1);
                }
            }
        });
        txtLocSoDienThoaiNguoiDung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    List<KhachHang> khachHangList =khachHangDAO.getAllKhachHang();
                    KhachHangAdapter khachHangAdapter1 = new KhachHangAdapter(MainActivityDanhSachNguoiDung.this,khachHangList);
                    lvDanhSachNguoiDung.setAdapter(khachHangAdapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}