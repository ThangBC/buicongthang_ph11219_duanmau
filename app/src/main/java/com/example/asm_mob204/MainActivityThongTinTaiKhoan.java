package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob204.adapter.NguoiDungAdapter;
import com.example.asm_mob204.model.NguoiDung;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.NguoiDungDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivityThongTinTaiKhoan extends AppCompatActivity {
    TextView tvTenTaiKhoanThongTinTaiKhoan, tvHoVaTenThongTinTaiKhoan, tvMatKhauThongTinTaiKhoan, tvEmailThongTinTaiKhoan,
            tvSoDienThoaiThongTinTaiKhoan, tvNgaySinhThongTinTaiKhoan, tvGioiTinhThongTinTaiKhoan;
    Button btnSuaThongTinTaiKhoan;
    MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin_tai_khoan);
        tvTenTaiKhoanThongTinTaiKhoan = findViewById(R.id.tvTenTaiKhoanThongTinTaiKhoan);
        tvHoVaTenThongTinTaiKhoan = findViewById(R.id.tvHoVaTenThongTinTaiKhoan);
        tvMatKhauThongTinTaiKhoan = findViewById(R.id.tvPassWordThongTinTaiKhoan);
        tvEmailThongTinTaiKhoan = findViewById(R.id.tvEmailThongTinTaiKhoan);
        tvSoDienThoaiThongTinTaiKhoan = findViewById(R.id.tvSoDienThoaiThongTinTaiKhoan);
        tvNgaySinhThongTinTaiKhoan = findViewById(R.id.tvNgaySinhThongTinTaiKhoan);
        tvGioiTinhThongTinTaiKhoan = findViewById(R.id.tvGioiTinhThongTinTaiKhoan);
        btnSuaThongTinTaiKhoan = findViewById(R.id.btnSuaThongTinTaiKhoan);

        mySQLite = new MySQLite(this);
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(mySQLite);
        NguoiDung nguoiDung = nguoiDungDAO.gettOneNguoiDung(MainActivityDangNhap.txtUserName.getEditText().getText().toString().trim(),
                MainActivityDangNhap.txtPassWord.getEditText().getText().toString().trim());
        tvTenTaiKhoanThongTinTaiKhoan.setText("Tên tài khoản: " + nguoiDung.getTenTaiKhoan());
        tvHoVaTenThongTinTaiKhoan.setText("Họ và tên: " + nguoiDung.getHoVaTen());
        tvMatKhauThongTinTaiKhoan.setText("Mật khẩu: " + nguoiDung.getMatKhau());
        tvEmailThongTinTaiKhoan.setText("Email: " + nguoiDung.getEmai());
        tvSoDienThoaiThongTinTaiKhoan.setText("Số điện thoại: " + nguoiDung.getSoDienThoai());
        tvNgaySinhThongTinTaiKhoan.setText("Ngày sinh: " + nguoiDung.getNgaySinh());
        tvGioiTinhThongTinTaiKhoan.setText("Giới tính: " + nguoiDung.getGioiTinh());

        if(MainActivityDangNhap.txtUserName.getEditText().getText().toString().trim().equalsIgnoreCase("admin") &&
        MainActivityDangNhap.txtPassWord.getEditText().getText().toString().trim().equalsIgnoreCase("admin")){
            tvTenTaiKhoanThongTinTaiKhoan.setText("Tên tài khoản: admin");
            tvHoVaTenThongTinTaiKhoan.setText("Họ và tên: BUI CONG THANG");
            tvMatKhauThongTinTaiKhoan.setText("Mật khẩu: admin");
            tvEmailThongTinTaiKhoan.setText("Email: thangbcph11219@fpt.edu.vn");
            tvSoDienThoaiThongTinTaiKhoan.setText("Số điện thoại: 0981239019");
            tvNgaySinhThongTinTaiKhoan.setText("Ngày sinh: 04/08/2001");
            tvGioiTinhThongTinTaiKhoan.setText("Giới tính: Nam");
            btnSuaThongTinTaiKhoan.setVisibility(View.INVISIBLE);
        }

        btnSuaThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityThongTinTaiKhoan.this);
                view = LayoutInflater.from(MainActivityThongTinTaiKhoan.this).inflate(R.layout.dialog_sua_thong_tin_tai_khoan,null);
                builder.setCancelable(false);
                builder.setTitle("Sửa thông tin tài khoản");
                builder.setView(view);

                Button btnCancelThongTinTaiKhoan = view.findViewById(R.id.btnCancelThongTinTaiKhoan);
                Button btnSuaSuaThongTinTaiKhoan = view.findViewById(R.id.btnSuaSuaThongTinTaiKhoan);
                final RadioGroup rdogroupSuaThongTinTaiKhoan = view.findViewById(R.id.rdogroupSuaThongTinTaiKhoan);
                final TextInputLayout txtHoVaTenSuaThongTinTaiKhoan = view.findViewById(R.id.txtHoVaTenSuaThongTinTaiKhoan);
                final TextInputLayout txtMatKhauSuaThongTinTaiKhoan = view.findViewById(R.id.txtMatKhauSuaThongTinTaiKhoan);
                final TextInputLayout txtEmailSuaThongTinTaiKhoan = view.findViewById(R.id.txtEmailSuaThongTinTaiKhoan);
                final TextInputLayout txtSoDienThoaiSuaThongTinTaiKhoan = view.findViewById(R.id.txtSoDienThoaiSuaThongTinTaiKhoan);
                final TextInputLayout txtNgaySinhSuaThongTinTaiKhoan = view.findViewById(R.id.txtNgaySinhSuaThongTinTaiKhoan);
                final AlertDialog alertDialog = builder.show();

                txtNgaySinhSuaThongTinTaiKhoan.getEditText().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        final int y =calendar.get(Calendar.YEAR);
                        final int m = calendar.get(Calendar.MONTH);
                        final int d = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityThongTinTaiKhoan.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtNgaySinhSuaThongTinTaiKhoan.getEditText().setText(day+"/"+(month+1)+"/"+year);
                            }
                        },y,m,d);
                        datePickerDialog.show();
                    }
                });

                final View finalView = view;

                btnSuaSuaThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        final RadioButton rdobtnSuaThongTinTaiKhoan;
                        int radioId = rdogroupSuaThongTinTaiKhoan.getCheckedRadioButtonId();
                        rdobtnSuaThongTinTaiKhoan = finalView.findViewById(radioId);

                        NguoiDung nguoiDung1 = new NguoiDung();
                        nguoiDung1.setTenTaiKhoan(tvTenTaiKhoanThongTinTaiKhoan.getText().toString().substring(15));
                        nguoiDung1.setHoVaTen(txtHoVaTenSuaThongTinTaiKhoan.getEditText().getText().toString().trim());
                        nguoiDung1.setMatKhau(txtMatKhauSuaThongTinTaiKhoan.getEditText().getText().toString().trim());
                        nguoiDung1.setEmai(txtEmailSuaThongTinTaiKhoan.getEditText().getText().toString().trim());
                        nguoiDung1.setSoDienThoai(txtSoDienThoaiSuaThongTinTaiKhoan.getEditText().getText().toString().trim());
                        nguoiDung1.setNgaySinh(txtNgaySinhSuaThongTinTaiKhoan.getEditText().getText().toString().trim());
                        nguoiDung1.setGioiTinh(rdobtnSuaThongTinTaiKhoan.getText().toString().trim());
                        NguoiDungDAO nguoiDungDAO1 = new NguoiDungDAO(new MySQLite(MainActivityThongTinTaiKhoan.this));
                        boolean ketQua = nguoiDungDAO1.suaNguoiDung(nguoiDung1);
                        if (ketQua) {
                            Toast.makeText(MainActivityThongTinTaiKhoan.this, "Sửa thành công !!!", Toast.LENGTH_SHORT).show();
                            nguoiDungDAO1 = new NguoiDungDAO(new MySQLite(MainActivityThongTinTaiKhoan.this));
                            NguoiDung nguoiDung2 = nguoiDungDAO1.gettOneNguoiDung(nguoiDung1.getTenTaiKhoan(),nguoiDung1.getMatKhau());
                            tvTenTaiKhoanThongTinTaiKhoan.setText("Tên tài khoản: " + nguoiDung2.getTenTaiKhoan());
                            tvHoVaTenThongTinTaiKhoan.setText("Họ và tên: " + nguoiDung2.getHoVaTen());
                            tvMatKhauThongTinTaiKhoan.setText("Mật khẩu: " + nguoiDung2.getMatKhau());
                            tvEmailThongTinTaiKhoan.setText("Email: " + nguoiDung2.getEmai());
                            tvSoDienThoaiThongTinTaiKhoan.setText("Số điện thoại: " + nguoiDung2.getSoDienThoai());
                            tvNgaySinhThongTinTaiKhoan.setText("Ngày sinh: " + nguoiDung2.getNgaySinh());
                            tvGioiTinhThongTinTaiKhoan.setText("Giới tính: " + nguoiDung2.getGioiTinh());
                        }else{
                            Toast.makeText(MainActivityThongTinTaiKhoan.this, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancelThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
}