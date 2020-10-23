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
import com.example.asm_mob204.adapter.NguoiDungAdapter;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.model.NguoiDung;
import com.example.asm_mob204.sqlite.KhachHangDAO;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.NguoiDungDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivityDanhSachNguoiDung extends AppCompatActivity {
    Button btnLocDanhSachNguoiDung;
    EditText txtLocTenNguoiDung;
    public static ListView lvDanhSachNguoiDung;
    List<NguoiDung> nguoiDungList;
    NguoiDungDAO nguoiDungDAO;
    private MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_sach_nguoi_dung);
        btnLocDanhSachNguoiDung = findViewById(R.id.btnLocDanhSachNguoiDung);
        txtLocTenNguoiDung = findViewById(R.id.txtLocTenNguoiDung);
        lvDanhSachNguoiDung = findViewById(R.id.lvDanhSachNguoiDung);
        mySQLite = new MySQLite(this);
        nguoiDungDAO = new NguoiDungDAO(mySQLite);
        nguoiDungList = new ArrayList<>();
        nguoiDungList =nguoiDungDAO.getAllNguoiDung();
        Collections.sort(nguoiDungList, new Comparator<NguoiDung>() {
            @Override
            public int compare(NguoiDung nguoiDung, NguoiDung t1) {
                return nguoiDung.getHoVaTen().compareTo(t1.getHoVaTen());
            }
        });
        final NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList,MainActivityDanhSachNguoiDung.this);
        lvDanhSachNguoiDung.setAdapter(nguoiDungAdapter);
        btnLocDanhSachNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String HovaTen = txtLocTenNguoiDung.getText().toString().trim();
                if(HovaTen.isEmpty()){
                    txtLocTenNguoiDung.setError("Nhập dữ liệu trước");
                    return;
                }
                NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(mySQLite);
                List<NguoiDung> nguoiDungList = nguoiDungDAO.timKiemNguoiDung(HovaTen);
                if(nguoiDungList.size()==0){
                    txtLocTenNguoiDung.setError("Không tìm thấy !");
                }else{
                    NguoiDungAdapter nguoiDungAdapter1 =new NguoiDungAdapter(nguoiDungList,MainActivityDanhSachNguoiDung.this);
                    lvDanhSachNguoiDung.setAdapter(nguoiDungAdapter1);
                }
            }
        });
        txtLocTenNguoiDung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    List<NguoiDung> nguoiDungList =nguoiDungDAO.getAllNguoiDung();
                    NguoiDungAdapter nguoiDungAdapter1 = new NguoiDungAdapter(nguoiDungList,MainActivityDanhSachNguoiDung.this);
                    lvDanhSachNguoiDung.setAdapter(nguoiDungAdapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}