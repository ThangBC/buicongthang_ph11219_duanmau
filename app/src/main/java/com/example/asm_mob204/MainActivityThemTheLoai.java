package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.asm_mob204.model.TheLoai;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.TheLoaiDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivityThemTheLoai extends AppCompatActivity {
    TextInputLayout txtMaTheLoaiThemTheLoai, txtTenTheLoaiThemTheLoai, txtNgayThemThemTheLoai;
    Button btnThemTheloai;
    private MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_the_loai);
        txtMaTheLoaiThemTheLoai = findViewById(R.id.txtMaTheLoaiThemTheLoai);
        txtTenTheLoaiThemTheLoai = findViewById(R.id.txtTenTheLoaiThemTheLoai);
        txtNgayThemThemTheLoai = findViewById(R.id.txtNgayThemThemTheLoai);
        btnThemTheloai = findViewById(R.id.btnThemTheLoai);
        mySQLite = new MySQLite(this);
        txtNgayThemThemTheLoai.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int y = calendar.get(Calendar.YEAR);
                final int m = calendar.get(Calendar.MONTH);
                final int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityThemTheLoai.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtNgayThemThemTheLoai.getEditText().setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
        btnThemTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoai theLoai = new TheLoai();
                theLoai.setMaTheLoai(txtMaTheLoaiThemTheLoai.getEditText().getText().toString().trim());
                theLoai.setTenTheLoai(txtTenTheLoaiThemTheLoai.getEditText().getText().toString().trim());
                theLoai.setNgayThem(txtNgayThemThemTheLoai.getEditText().getText().toString().trim());
                boolean checkMaTheLoai = checkEmpty(theLoai.getMaTheLoai(), txtMaTheLoaiThemTheLoai);
                boolean checkTenTheLoai = checkEmpty(theLoai.getTenTheLoai(), txtTenTheLoaiThemTheLoai);
                boolean checkNgayThem = checkEmpty(theLoai.getNgayThem(), txtNgayThemThemTheLoai);
                if (checkMaTheLoai && checkTenTheLoai && checkNgayThem) {
                    TheLoaiDAO theLoaiDAO = new TheLoaiDAO(mySQLite);
                    boolean ketQua = theLoaiDAO.themTheLoai(theLoai);
                    if (ketQua) {
                        Toast.makeText(MainActivityThemTheLoai.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivityThemTheLoai.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivityThemTheLoai.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
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