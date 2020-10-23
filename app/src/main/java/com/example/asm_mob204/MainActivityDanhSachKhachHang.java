package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.asm_mob204.adapter.KhachHangAdapter;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.sqlite.KhachHangDAO;
import com.example.asm_mob204.sqlite.MySQLite;

import java.util.ArrayList;
import java.util.List;

public class MainActivityDanhSachKhachHang extends AppCompatActivity {
    Button btnLocDanhSachKhachHang;
    EditText txtLocSoDienThoaiKhachHang;
    public static ListView lvDanhSachKhachHang;
    List<KhachHang> khachHangList;
    KhachHangDAO khachHangDAO;
    private MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_sach_khach_hang);
        btnLocDanhSachKhachHang = findViewById(R.id.btnLocDanhSachKhachHang);
        txtLocSoDienThoaiKhachHang = findViewById(R.id.txtLocSoDienThoaiKhachHang);
        lvDanhSachKhachHang = findViewById(R.id.lvDanhSachKhachHang);
        mySQLite = new MySQLite(this);
        khachHangDAO = new KhachHangDAO(mySQLite);
        khachHangList = new ArrayList<>();
        khachHangList =khachHangDAO.getAllKhachHang();
        final KhachHangAdapter khachHangAdapter = new KhachHangAdapter(MainActivityDanhSachKhachHang.this,khachHangList);
        lvDanhSachKhachHang.setAdapter(khachHangAdapter);
        btnLocDanhSachKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SDT = txtLocSoDienThoaiKhachHang.getText().toString().trim();
                if(SDT.isEmpty()){
                    txtLocSoDienThoaiKhachHang.setError("Nhập dữ liệu trước");
                    return;
                }
                KhachHangDAO khachHangDAO = new KhachHangDAO(mySQLite);
                List<KhachHang> khachHangList = khachHangDAO.timKiemKhachHang(SDT);
                if(khachHangList.size()==0){
                    txtLocSoDienThoaiKhachHang.setError("Không tìm thấy !");
                }else{
                    KhachHangAdapter khachHangAdapter1 =new KhachHangAdapter(MainActivityDanhSachKhachHang.this,khachHangList);
                    lvDanhSachKhachHang.setAdapter(khachHangAdapter1);
                }
            }
        });
        txtLocSoDienThoaiKhachHang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    List<KhachHang> khachHangList =khachHangDAO.getAllKhachHang();
                    KhachHangAdapter khachHangAdapter1 = new KhachHangAdapter(MainActivityDanhSachKhachHang.this,khachHangList);
                    lvDanhSachKhachHang.setAdapter(khachHangAdapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}