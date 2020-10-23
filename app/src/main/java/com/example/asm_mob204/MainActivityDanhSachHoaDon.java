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

import com.example.asm_mob204.adapter.HoaDonAdapter;
import com.example.asm_mob204.adapter.SachAdapter;
import com.example.asm_mob204.model.HoaDon;
import com.example.asm_mob204.model.Sach;
import com.example.asm_mob204.sqlite.HoaDonDAO;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.SachDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivityDanhSachHoaDon extends AppCompatActivity {
    public static ListView lvDanhSachHoaDon;
    EditText txtLocNgayTaoHoaDon;
    Button btnLocDanhSachHoaDon;
    List<HoaDon> hoaDonList;
    HoaDonDAO hoaDonDAO;
    private MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_sach_hoa_don);
        lvDanhSachHoaDon = findViewById(R.id.lvDanhSachHoaDon);
        txtLocNgayTaoHoaDon = findViewById(R.id.txtLocNgayTaoHoaDon);
        btnLocDanhSachHoaDon = findViewById(R.id.btnLocDanhSachHoaDon);
        mySQLite = new MySQLite(this);
        hoaDonDAO = new HoaDonDAO(mySQLite);
        hoaDonList = new ArrayList<>();
        hoaDonList = hoaDonDAO.getAllHoaDon();
        final HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(hoaDonList,this);
        lvDanhSachHoaDon.setAdapter(hoaDonAdapter);
        txtLocNgayTaoHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int y =calendar.get(Calendar.YEAR);
                final int m = calendar.get(Calendar.MONTH);
                final int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityDanhSachHoaDon.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtLocNgayTaoHoaDon.setText(day+"/"+(month+1)+"/"+year);
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
        btnLocDanhSachHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngayTao = txtLocNgayTaoHoaDon.getText().toString().trim();
                if(ngayTao.isEmpty()){
                    txtLocNgayTaoHoaDon.setError("Nhập ngày tạo trước");
                    return;
                }
                HoaDonDAO hoaDonDAO = new HoaDonDAO(mySQLite);
                List<HoaDon> hoaDonList = hoaDonDAO.timKiemHoaDon(ngayTao);
                if(hoaDonList.size()==0){
                    txtLocNgayTaoHoaDon.setError("Không tìm thấy !");
                }else{
                    HoaDonAdapter hoaDonAdapter1 =new HoaDonAdapter(hoaDonList,MainActivityDanhSachHoaDon.this);
                    lvDanhSachHoaDon.setAdapter(hoaDonAdapter1);
                }
            }
        });
        txtLocNgayTaoHoaDon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    List<HoaDon> hoaDonList =hoaDonDAO.getAllHoaDon();
                    HoaDonAdapter hoaDonAdapter1 = new HoaDonAdapter(hoaDonList,MainActivityDanhSachHoaDon.this);
                    lvDanhSachHoaDon.setAdapter(hoaDonAdapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}