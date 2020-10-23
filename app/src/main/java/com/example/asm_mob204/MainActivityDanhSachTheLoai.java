package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.example.asm_mob204.adapter.KhachHangAdapter;
import com.example.asm_mob204.adapter.TheLoaiAdapter;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.model.TheLoai;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.TheLoaiDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivityDanhSachTheLoai extends AppCompatActivity {
    public static ListView lvDanhSachTheLoai;
    EditText txtLocNgayThemTheLoai;
    Button btnLocDanhSachTheLoai;
    List<TheLoai> theLoaiList;
    TheLoaiDAO theLoaiDAO;
    private MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_sach_the_loai);
        lvDanhSachTheLoai = findViewById(R.id.lvDanhSachTheLoai);
        txtLocNgayThemTheLoai = findViewById(R.id.txtLocNgayThemTheLoai);
        btnLocDanhSachTheLoai = findViewById(R.id.btnLocDanhSachTheLoai);
        mySQLite = new MySQLite(this);
        theLoaiDAO = new TheLoaiDAO(mySQLite);
        theLoaiList = new ArrayList<>();
        theLoaiList = theLoaiDAO.getAllTheLoai();
        final TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(theLoaiList,this);
        lvDanhSachTheLoai.setAdapter(theLoaiAdapter);
        txtLocNgayThemTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int y =calendar.get(Calendar.YEAR);
                final int m = calendar.get(Calendar.MONTH);
                final int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityDanhSachTheLoai.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtLocNgayThemTheLoai.setText(day+"/"+(month+1)+"/"+year);
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
        btnLocDanhSachTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngayThem = txtLocNgayThemTheLoai.getText().toString().trim();
                if(ngayThem.isEmpty()){
                    txtLocNgayThemTheLoai.setError("Nhập ngày thêm trước");
                    return;
                }
                TheLoaiDAO theLoaiDAO = new TheLoaiDAO(mySQLite);
                List<TheLoai> theLoaiList = theLoaiDAO.timKiemTheLoai(ngayThem);
                if(theLoaiList.size()==0){
                    txtLocNgayThemTheLoai.setError("Không tìm thấy !");
                }else{
                    TheLoaiAdapter khachHangAdapter1 =new TheLoaiAdapter(theLoaiList,MainActivityDanhSachTheLoai.this);
                    lvDanhSachTheLoai.setAdapter(khachHangAdapter1);
                }
            }
        });
        txtLocNgayThemTheLoai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    List<TheLoai> theLoaiList =theLoaiDAO.getAllTheLoai();
                    TheLoaiAdapter theLoaiAdapter1 = new TheLoaiAdapter(theLoaiList,MainActivityDanhSachTheLoai.this);
                    lvDanhSachTheLoai.setAdapter(theLoaiAdapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}