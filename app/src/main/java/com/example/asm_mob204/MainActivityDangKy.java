package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.asm_mob204.model.NguoiDung;
import com.example.asm_mob204.model.Sach;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.NguoiDungDAO;
import com.example.asm_mob204.sqlite.SachDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivityDangKy extends AppCompatActivity {
    TextInputLayout txtTenTaiKhoanDangKy, txtHoVaTenDangKy, txtMatKhauDangKy, txtEmailDangKy, txtSoDienThoaiDangKy, txtNgaySinhDangKy,txtDiaChiDangKy;
    RadioGroup rdogroupDangKy;
    RadioButton rdobtn;
    CheckBox ckbDieuKhoan;
    Button btnDangKyDangKy;
    MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);
        txtTenTaiKhoanDangKy = findViewById(R.id.txtUserNameDangKy);
        txtHoVaTenDangKy = findViewById(R.id.txtHovaTenDangKy);
        txtMatKhauDangKy = findViewById(R.id.txtPassWordDangKy);
        txtEmailDangKy = findViewById(R.id.txtEmailDangKy);
        txtSoDienThoaiDangKy = findViewById(R.id.txtSoDienThoaiDangKy);
        txtNgaySinhDangKy = findViewById(R.id.txtNgaySinhDangKy);
        txtDiaChiDangKy = findViewById(R.id.txtDiaChiDangKy);
        rdogroupDangKy = findViewById(R.id.rdogroupDangKy);
        ckbDieuKhoan = findViewById(R.id.ckbDieuKhoan);
        btnDangKyDangKy = findViewById(R.id.btnDangKyDangKy);
        mySQLite = new MySQLite(this);

        txtNgaySinhDangKy.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int y = calendar.get(Calendar.YEAR);
                final int m = calendar.get(Calendar.MONTH);
                final int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityDangKy.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtNgaySinhDangKy.getEditText().setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
        btnDangKyDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = rdogroupDangKy.getCheckedRadioButtonId();
                rdobtn = findViewById(radioId);
                if (!ckbDieuKhoan.isChecked()) {
                    Toast.makeText(MainActivityDangKy.this, "Bạn chưa đồng ý với điều khoản!", Toast.LENGTH_SHORT).show();
                    return;
                } else if(!checkEmail(txtEmailDangKy.getEditText().getText().toString().trim())){
                    Toast.makeText(MainActivityDangKy.this, "Sai định dạng email", Toast.LENGTH_SHORT).show();
                }
                else {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setTenTaiKhoan(txtTenTaiKhoanDangKy.getEditText().getText().toString().trim());
                    nguoiDung.setHoVaTen(txtHoVaTenDangKy.getEditText().getText().toString().trim());
                    nguoiDung.setMatKhau(txtMatKhauDangKy.getEditText().getText().toString().trim());
                    nguoiDung.setSoDienThoai(txtSoDienThoaiDangKy.getEditText().getText().toString().trim());
                    nguoiDung.setEmai(txtEmailDangKy.getEditText().getText().toString().trim());
                    nguoiDung.setNgaySinh(txtNgaySinhDangKy.getEditText().getText().toString().trim());
                    nguoiDung.setGioiTinh(rdobtn.getText().toString().trim());
                    nguoiDung.setDiaChi(txtDiaChiDangKy.getEditText().getText().toString().trim());
                    boolean checkTenTaiKhoan = checkEmpty(nguoiDung.getTenTaiKhoan(), txtTenTaiKhoanDangKy);
                    boolean checkHoVaTen = checkEmpty(nguoiDung.getHoVaTen(), txtHoVaTenDangKy);
                    boolean checkMatKhau = checkEmpty(nguoiDung.getMatKhau(), txtMatKhauDangKy);
                    boolean checkEmail = checkEmpty(nguoiDung.getEmai(), txtSoDienThoaiDangKy);
                    boolean checkSoDienThoai = checkEmpty(nguoiDung.getSoDienThoai(), txtEmailDangKy);
                    boolean checkNgaySinh = checkEmpty(nguoiDung.getNgaySinh(), txtNgaySinhDangKy);
                    boolean checkDiaChi = checkEmpty(nguoiDung.getDiaChi(), txtDiaChiDangKy);
                    if (checkTenTaiKhoan && checkHoVaTen && checkMatKhau && checkEmail && checkSoDienThoai && checkNgaySinh && checkDiaChi) {
                        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(mySQLite);
                        boolean ketQua = nguoiDungDAO.themNguoiDung(nguoiDung);
                        if (ketQua) {
                            Toast.makeText(MainActivityDangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivityDangKy.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivityDangKy.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
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
    public boolean checkEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}