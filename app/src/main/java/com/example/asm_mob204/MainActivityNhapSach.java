package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asm_mob204.adapter.SpinnerTheLoaiAdapter;
import com.example.asm_mob204.model.Sach;
import com.example.asm_mob204.model.TheLoai;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.SachDAO;
import com.example.asm_mob204.sqlite.TheLoaiDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivityNhapSach extends AppCompatActivity {
    TextInputLayout txtMaSachNhapSach, txtTenSachNhapSach, txtGiaSachNhapSach, txtTacGiaNhapSach,
            txtNhaXuatBanNhapSach, txtSoLuongNhapSach, txtThoiGianNhapNhapSach;
    Spinner spnTheLoaiNhapSach,spnIsSaleNhapSach;
    Button btnNhapSach;
    String tenTheLoai, isSale;
    private MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nhap_sach);
        txtMaSachNhapSach = findViewById(R.id.txtMaSachNhapSach);
        txtTenSachNhapSach = findViewById(R.id.txtTenSachNhapSach);
        txtGiaSachNhapSach = findViewById(R.id.txtGiaSachNhapSach);
        txtTacGiaNhapSach = findViewById(R.id.txtTacGiaNhapSach);
        txtNhaXuatBanNhapSach = findViewById(R.id.txtNhaXuatBanNhapSach);
        txtSoLuongNhapSach = findViewById(R.id.txtSoLuongNhapSach);
        txtThoiGianNhapNhapSach = findViewById(R.id.txtThoiGianNhapNhapSach);
        spnTheLoaiNhapSach = findViewById(R.id.spnTheLoaiNhapSach);
        spnIsSaleNhapSach = findViewById(R.id.spnIsSaleNhapSach);
        btnNhapSach = findViewById(R.id.btnNhapSach);
        mySQLite = new MySQLite(this);
        final List<TheLoai> theLoaiList = new TheLoaiDAO(mySQLite).getAllTheLoai();
        TheLoai vuilongchon = new TheLoai("-1", "--Vui lòng chọn thể loại--", "-1");
        theLoaiList.add(0, vuilongchon);
        SpinnerTheLoaiAdapter spinnerAdapter = new SpinnerTheLoaiAdapter(theLoaiList, this);
        spnTheLoaiNhapSach.setAdapter(spinnerAdapter);
        spnTheLoaiNhapSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    tenTheLoai = theLoaiList.get(i).getTenTheLoai();
                } else {
                    tenTheLoai = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final String[] sale = {"Vui lòng chọn kiểu giảm giá","Sale","No sale"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivityNhapSach.this,android.R.layout.simple_spinner_item,sale);
        spnIsSaleNhapSach.setAdapter(adapter);
        spnIsSaleNhapSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    isSale = sale[i];
                }else {
                    isSale = null;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        txtThoiGianNhapNhapSach.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int y = calendar.get(Calendar.YEAR);
                final int m = calendar.get(Calendar.MONTH);
                final int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityNhapSach.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtThoiGianNhapNhapSach.getEditText().setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
        btnNhapSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tenTheLoai == null) {
                    Toast.makeText(MainActivityNhapSach.this, "Bạn chưa chọn thể loại sách", Toast.LENGTH_SHORT).show();
                    return;
                } else if(isSale ==null){
                    Toast.makeText(MainActivityNhapSach.this, "Bạn chưa chọn kiểu giảm giá", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (txtTenSachNhapSach.getEditText().getText().toString().trim().length() < 10 || txtTenSachNhapSach.getEditText().getText().toString().trim().length() > 20) {
                    Toast.makeText(MainActivityNhapSach.this, "Tên sách phải lớn hơn 10 và bé hơn 20", Toast.LENGTH_SHORT).show();
                    return;
                } else if(!Pattern.matches("[A-Z]",txtTenSachNhapSach.getEditText().getText().toString().trim().substring(0,1))){
                    Toast.makeText(MainActivityNhapSach.this, "Bạn phải nhập chữ cái đầu viết hoa", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Sach sach = new Sach();
                    sach.setMaSach(txtMaSachNhapSach.getEditText().getText().toString().trim());
                    sach.setTenSach(txtTenSachNhapSach.getEditText().getText().toString().trim());
                    sach.setGiaSach(txtGiaSachNhapSach.getEditText().getText().toString().trim());
                    sach.setTacGia(txtTacGiaNhapSach.getEditText().getText().toString().trim());
                    sach.setNhaXuatBan(txtNhaXuatBanNhapSach.getEditText().getText().toString().trim());
                    sach.setTheLoai(tenTheLoai);
                    sach.setSoLuong(txtSoLuongNhapSach.getEditText().getText().toString().trim());
                    sach.setThoiGianNhap(txtThoiGianNhapNhapSach.getEditText().getText().toString().trim());
                    sach.setIsSale(isSale);
                    boolean checkMaSach = checkEmpty(sach.getMaSach(), txtMaSachNhapSach);
                    boolean checkTenSach = checkEmpty(sach.getTenSach(), txtTenSachNhapSach);
                    boolean checkGiaSach = checkEmpty(String.valueOf(sach.getGiaSach()), txtGiaSachNhapSach);
                    boolean checkTacGia = checkEmpty(sach.getTacGia(), txtTacGiaNhapSach);
                    boolean checkNhaXuatBan = checkEmpty(sach.getNhaXuatBan(), txtNhaXuatBanNhapSach);
                    boolean checkSoLuong = checkEmpty(String.valueOf(sach.getSoLuong()), txtSoLuongNhapSach);
                    boolean checkThoiGianNhap = checkEmpty(sach.getThoiGianNhap(), txtThoiGianNhapNhapSach);
                    if (checkMaSach && checkTenSach && checkGiaSach && checkTacGia && checkNhaXuatBan && checkSoLuong && checkThoiGianNhap) {
                        SachDAO sachDAO = new SachDAO(mySQLite);
                        boolean ketQua = sachDAO.themSach(sach);
                        if (ketQua) {
                            Toast.makeText(MainActivityNhapSach.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivityNhapSach.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivityNhapSach.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    }
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