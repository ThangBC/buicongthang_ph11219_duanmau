package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.example.asm_mob204.adapter.SachAdapter;
import com.example.asm_mob204.adapter.TheLoaiAdapter;
import com.example.asm_mob204.model.Sach;
import com.example.asm_mob204.model.TheLoai;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.SachDAO;
import com.example.asm_mob204.sqlite.TheLoaiDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivityDanhSachSach extends AppCompatActivity {
    public static ListView lvDanhSachSach;
    EditText txtLocNgayNhapSach;
    Button btnLocDanhSachSach;
    Switch switchSale;
    List<Sach> sachList;
    SachDAO sachDAO;
    private MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_sach_sach);
        lvDanhSachSach =findViewById(R.id.lvDanhSachSach);
        txtLocNgayNhapSach = findViewById(R.id.txtLocNgayNhapSach);
        btnLocDanhSachSach =findViewById(R.id.btnLocDanhSachSach);
        switchSale = findViewById(R.id.switchSale);
        mySQLite = new MySQLite(this);
        sachDAO = new SachDAO(mySQLite);
        sachList = new ArrayList<>();
        sachList = sachDAO.getAllSach();
        final SachAdapter sachAdapter = new SachAdapter(sachList,this);
        lvDanhSachSach.setAdapter(sachAdapter);
        txtLocNgayNhapSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int y =calendar.get(Calendar.YEAR);
                final int m = calendar.get(Calendar.MONTH);
                final int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityDanhSachSach.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtLocNgayNhapSach.setText(day+"/"+(month+1)+"/"+year);
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
        btnLocDanhSachSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngayNhap = txtLocNgayNhapSach.getText().toString().trim();
                if(ngayNhap.isEmpty()){
                    txtLocNgayNhapSach.setError("Nhập ngày nhập trước");
                    return;
                }
                SachDAO sachDAO = new SachDAO(mySQLite);
                List<Sach> sachList = sachDAO.timKiemSach(ngayNhap);
                if(sachList.size()==0){
                    txtLocNgayNhapSach.setError("Không tìm thấy !");
                }else{
                    SachAdapter sachAdapter1 =new SachAdapter(sachList,MainActivityDanhSachSach.this);
                    lvDanhSachSach.setAdapter(sachAdapter1);
                }
            }
        });
        switchSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchSale.isChecked()){
                    String sale = "Sale";
                    sachDAO = new SachDAO(mySQLite);
                    sachList = new ArrayList<>();
                    sachList = sachDAO.checkSale(sale);
                    SachAdapter sachAdapter1 = new SachAdapter(sachList,MainActivityDanhSachSach.this);
                    lvDanhSachSach.setAdapter(sachAdapter1);
                }else{
                    sachDAO = new SachDAO(mySQLite);
                    sachList = new ArrayList<>();
                    sachList = sachDAO.getAllSach();
                    SachAdapter sachAdapter1 = new SachAdapter(sachList,MainActivityDanhSachSach.this);
                    lvDanhSachSach.setAdapter(sachAdapter1);
                }
            }
        });
        txtLocNgayNhapSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    List<Sach> sachList =sachDAO.getAllSach();
                    SachAdapter sachAdapter1 = new SachAdapter(sachList,MainActivityDanhSachSach.this);
                    lvDanhSachSach.setAdapter(sachAdapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}