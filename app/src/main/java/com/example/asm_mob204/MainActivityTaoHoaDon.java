package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asm_mob204.adapter.SachAdapter;
import com.example.asm_mob204.adapter.SpinnerKhachHangAdapter;
import com.example.asm_mob204.adapter.SpinnerNguoiDungAdapter;
import com.example.asm_mob204.adapter.SpinnerSachAdapter;
import com.example.asm_mob204.adapter.SpinnerTheLoaiAdapter;
import com.example.asm_mob204.model.HoaDon;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.model.NguoiDung;
import com.example.asm_mob204.model.Sach;
import com.example.asm_mob204.model.TheLoai;
import com.example.asm_mob204.sqlite.HoaDonDAO;
import com.example.asm_mob204.sqlite.KhachHangDAO;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.NguoiDungDAO;
import com.example.asm_mob204.sqlite.SachDAO;
import com.example.asm_mob204.sqlite.TheLoaiDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class MainActivityTaoHoaDon extends AppCompatActivity {
    TextInputLayout txtMaHoaDonTaoHoaDon, txtNgayTaoTaoHoaDon, txtSoLuongTaoHoaDon;
    Spinner spnTenNguoiMuaTaoHoaDon, spnTenNguoiTaoTaoHoaDon, spnTenSachTaoHoaDon;
    Button btnTaoHoaDon;
    private MySQLite mySQLite;
    String tenNguoimua, tenNguoiTao, tenSach, giaSach, soLuongSach, maSach, tacGia, nhaXuatBan, ngayNhap, theLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tao_hoa_don);
        txtMaHoaDonTaoHoaDon = findViewById(R.id.txtMaHoaDonTaoHoaDon);
        txtNgayTaoTaoHoaDon = findViewById(R.id.txtNgayTaoTaoHoaDon);
        txtSoLuongTaoHoaDon = findViewById(R.id.txtSoLuongTaoHoaDon);
        spnTenNguoiMuaTaoHoaDon = findViewById(R.id.spnTenNguoiMuaTaoHoaDon);
        spnTenNguoiTaoTaoHoaDon = findViewById(R.id.spnTenNguoiTaoTaoHoaDon);
        spnTenSachTaoHoaDon = findViewById(R.id.spnTenSachTaoHoaDon);
        btnTaoHoaDon = findViewById(R.id.btnTaoHoaDon);
        mySQLite = new MySQLite(this);

        final List<KhachHang> khachHangList = new KhachHangDAO(mySQLite).getAllKhachHang();
        KhachHang vuilongchonKhachHang = new KhachHang("-1", "--Vui lòng chọn người mua--", "-1");
        khachHangList.add(0, vuilongchonKhachHang);
        SpinnerKhachHangAdapter spinnerKhachHangAdapter = new SpinnerKhachHangAdapter(khachHangList, this);
        spnTenNguoiMuaTaoHoaDon.setAdapter(spinnerKhachHangAdapter);

        final List<NguoiDung> nguoiDungList = new NguoiDungDAO(mySQLite).getAllNguoiDung();
        NguoiDung vuilongchonNguoiDung = new NguoiDung("-1", "--Vui lòng chọn người tạo--", "-1", "-1", "-1", "-1", "-1", "-1");
        nguoiDungList.add(0, vuilongchonNguoiDung);
        SpinnerNguoiDungAdapter spinnerNguoiDungAdapter = new SpinnerNguoiDungAdapter(nguoiDungList, this);
        spnTenNguoiTaoTaoHoaDon.setAdapter(spinnerNguoiDungAdapter);

        final List<Sach> sachList = new SachDAO(mySQLite).getAllSach();
        Sach vuilongchonSach = new Sach("-1", "--Vui lòng chọn tên sách--", "-1", "-1", "-1", "-1", "-1", "-1", "-1");
        sachList.add(0, vuilongchonSach);
        SpinnerSachAdapter spinnerSachAdapter = new SpinnerSachAdapter(sachList, this);
        spnTenSachTaoHoaDon.setAdapter(spinnerSachAdapter);

        spnTenNguoiMuaTaoHoaDon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    tenNguoimua = khachHangList.get(i).getTenKhachHang();
                } else {
                    tenNguoimua = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnTenNguoiTaoTaoHoaDon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    tenNguoiTao = nguoiDungList.get(i).getHoVaTen();
                } else {
                    tenNguoiTao = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnTenSachTaoHoaDon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    tenSach = sachList.get(i).getTenSach();
                    giaSach = sachList.get(i).getGiaSach();
                    soLuongSach = sachList.get(i).getSoLuong();
                    maSach = sachList.get(i).getMaSach();
                    tacGia = sachList.get(i).getTacGia();
                    nhaXuatBan = sachList.get(i).getNhaXuatBan();
                    ngayNhap = sachList.get(i).getThoiGianNhap();
                    theLoai = sachList.get(i).getTheLoai();
                } else {
                    tenSach = null;
                    giaSach = null;
                    soLuongSach = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        txtNgayTaoTaoHoaDon.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int y = calendar.get(Calendar.YEAR);
                final int m = calendar.get(Calendar.MONTH);
                final int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityTaoHoaDon.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtNgayTaoTaoHoaDon.getEditText().setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
        btnTaoHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(txtSoLuongTaoHoaDon.getEditText().getText().toString().trim()) == 0) {
                    Toast.makeText(MainActivityTaoHoaDon.this, "Không nhập số lượng là 0", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Integer.parseInt(soLuongSach) == 0) {
                    Toast.makeText(MainActivityTaoHoaDon.this, "Sách " + tenSach + " hết hàng", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Integer.parseInt(txtSoLuongTaoHoaDon.getEditText().getText().toString().trim()) > Integer.parseInt(soLuongSach)) {
                    Toast.makeText(MainActivityTaoHoaDon.this, "Số lượng sách còn " + soLuongSach + ", không mua lớn hơn !", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tenNguoimua == null) {
                    Toast.makeText(MainActivityTaoHoaDon.this, "Bạn chưa chọn tên người mua", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tenNguoiTao == null) {
                    Toast.makeText(MainActivityTaoHoaDon.this, "Bạn chưa chọn tên người tạo", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tenSach == null) {
                    Toast.makeText(MainActivityTaoHoaDon.this, "Bạn chưa chọn tên sách", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    int tongTien = Integer.parseInt(giaSach) * Integer.parseInt(txtSoLuongTaoHoaDon.getEditText().getText().toString().trim());
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setMaHoaDon(txtMaHoaDonTaoHoaDon.getEditText().getText().toString().trim());
                    hoaDon.setNgayTao(txtNgayTaoTaoHoaDon.getEditText().getText().toString().trim());
                    hoaDon.setTenNguoiMua(tenNguoimua);
                    hoaDon.setTenNguoiTao(tenNguoiTao);
                    hoaDon.setTenSach(tenSach);
                    hoaDon.setGia(giaSach);
                    hoaDon.setSoLuong(txtSoLuongTaoHoaDon.getEditText().getText().toString().trim());
                    hoaDon.setTongTien(String.valueOf(tongTien));
                    boolean checkMaHoaDon = checkEmpty(hoaDon.getMaHoaDon(), txtMaHoaDonTaoHoaDon);
                    boolean checkNgayTao = checkEmpty(hoaDon.getNgayTao(), txtNgayTaoTaoHoaDon);
                    boolean checkSoLuong = checkEmpty(hoaDon.getSoLuong(), txtSoLuongTaoHoaDon);
                    if (checkMaHoaDon && checkNgayTao && checkSoLuong) {
                        HoaDonDAO hoaDonDAO = new HoaDonDAO(mySQLite);
                        boolean ketQua = hoaDonDAO.themHoaDon(hoaDon);
                        if (ketQua) {
                            Toast.makeText(MainActivityTaoHoaDon.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                            Sach sach = new Sach();
                            sach.setMaSach(maSach);
                            sach.setTenSach(tenSach);
                            sach.setGiaSach(giaSach);
                            sach.setTacGia(tacGia);
                            sach.setNhaXuatBan(nhaXuatBan);
                            sach.setTheLoai(theLoai);
                            sach.setSoLuong(String.valueOf(Integer.parseInt(soLuongSach) - Integer.parseInt(txtSoLuongTaoHoaDon.getEditText().getText().toString().trim())));
                            SachDAO sachDAO = new SachDAO(mySQLite);
                            sachDAO.suaSach(sach);
                            List<Sach> sachList1 = sachDAO.getAllSach();
                            SachAdapter sachAdapter = new SachAdapter(sachList1, getBaseContext());
                            MainActivityDanhSachSach.lvDanhSachSach.setAdapter(sachAdapter);
                        } else {
                            Toast.makeText(MainActivityTaoHoaDon.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivityTaoHoaDon.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
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