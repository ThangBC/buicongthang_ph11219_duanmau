package com.example.asm_mob204.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob204.MainActivityDanhSachHoaDon;
import com.example.asm_mob204.MainActivityDanhSachSach;
import com.example.asm_mob204.MainActivityTaoHoaDon;
import com.example.asm_mob204.R;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    List<HoaDon> hoaDonList;
    Context context;
    String tenNguoimua, tenNguoiTao, tenSach, giaSach, soLuongSach, maSach, tacGia, nhaXuatBan, ngayNhap, theLoai;

    public HoaDonAdapter(List<HoaDon> hoaDonList, Context context) {
        this.hoaDonList = hoaDonList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hoaDonList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview_danh_sach_hoa_don, viewGroup, false);
        TextView tvMaHoaDonTaoHoaDon = view.findViewById(R.id.tvMaHoaDonTaoHoaDon);
        TextView tvNgayTaoTaoHoaDon = view.findViewById(R.id.tvNgayTaoTaoHoaDon);
        TextView tvNguoiMuaTaoHoaDon = view.findViewById(R.id.tvNguoiMuaTaoHoaDon);
        TextView tvNguoiTaoTaoHoaDon = view.findViewById(R.id.tvNguoiTaoTaoHoaDon);
        TextView tvTenSachTaoHoaDon = view.findViewById(R.id.tvTenSachTaoHoaDon);
        TextView tvGiaSachTaoHoaDon = view.findViewById(R.id.tvGiaSachTaoHoaDon);
        TextView tvSoLuongTaoHoaDon = view.findViewById(R.id.tvSoLuongTaoHoaDon);
        TextView tvTongTienTaoHoaDon = view.findViewById(R.id.tvTongTienTaoHoaDon);
        tvMaHoaDonTaoHoaDon.setText("Mã : " + hoaDonList.get(i).getMaHoaDon());
        tvNgayTaoTaoHoaDon.setText("Ngày tạo: " + hoaDonList.get(i).getNgayTao());
        tvNguoiMuaTaoHoaDon.setText("Người mua: " + hoaDonList.get(i).getTenNguoiMua());
        tvNguoiTaoTaoHoaDon.setText("Người tạo: " + hoaDonList.get(i).getTenNguoiTao());
        tvTenSachTaoHoaDon.setText("Tên sách: " + hoaDonList.get(i).getTenSach());
        tvGiaSachTaoHoaDon.setText("Giá: " + hoaDonList.get(i).getGia());
        tvSoLuongTaoHoaDon.setText("Số lượng: " + hoaDonList.get(i).getSoLuong());
        tvTongTienTaoHoaDon.setText("Tổng tiền: " + hoaDonList.get(i).getTongTien());
        view.findViewById(R.id.imgDeleteHoaDon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(new MySQLite(viewGroup.getContext()));
                String maHoaDon = hoaDonList.get(i).getMaHoaDon();
                boolean ketQua = hoaDonDAO.xoaHoaDon(maHoaDon);
                if (ketQua) {
                    Toast.makeText(viewGroup.getContext(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    hoaDonList.remove(i);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(viewGroup.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.imgEditHoaDon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_sua_hoa_don, null);
                builder.setCancelable(false);
                builder.setTitle("Sửa hóa đơn");
                builder.setView(view1);
                Button btnCancelHoaDon = view1.findViewById(R.id.btnCancelHoaDon);
                Button btnSuaHoaDon = view1.findViewById(R.id.btnSuaHoaDon);
                final TextInputLayout txtNgayTaoSuaHoaDon = view1.findViewById(R.id.txtNgayTaoSuaHoaDon);
                final TextInputLayout txtSoLuongSuaHoaDon = view1.findViewById(R.id.txtSoLuongSuaHoaDon);
                final Spinner spnTenNguoiMuaSuaHoaDon = view1.findViewById(R.id.spnTenNguoiMuaSuaHoaDon);
                final Spinner spnTenNguoiTaoSuaHoaDon = view1.findViewById(R.id.spnTenNguoiTaoSuaHoaDon);
                final Spinner spnTenSachSuaHoaDon = view1.findViewById(R.id.spnTenSachSuaHoaDon);
                final AlertDialog alertDialog = builder.show();
                final MySQLite mySQLite = new MySQLite(viewGroup.getContext());
                final KhachHangDAO khachHangDAO = new KhachHangDAO(mySQLite);
                final List<KhachHang> khachHangList = khachHangDAO.getAllKhachHang();
                KhachHang vuilongchonKhachHang = new KhachHang("-1", "--Vui lòng chọn người mua--", "-1");
                khachHangList.add(0, vuilongchonKhachHang);
                SpinnerKhachHangAdapter spinnerKhachHangAdapter = new SpinnerKhachHangAdapter(khachHangList, viewGroup.getContext());
                spnTenNguoiMuaSuaHoaDon.setAdapter(spinnerKhachHangAdapter);

                final NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(mySQLite);
                final List<NguoiDung> nguoiDungList = nguoiDungDAO.getAllNguoiDung();
                NguoiDung vuilongchonNguoiDung = new NguoiDung("-1", "--Vui lòng chọn người tạo--", "-1", "-1", "-1", "-1", "-1","-1");
                nguoiDungList.add(0, vuilongchonNguoiDung);
                SpinnerNguoiDungAdapter spinnerNguoiDungAdapter = new SpinnerNguoiDungAdapter(nguoiDungList, viewGroup.getContext());
                spnTenNguoiTaoSuaHoaDon.setAdapter(spinnerNguoiDungAdapter);

                final SachDAO sachDAO = new SachDAO(mySQLite);
                final List<Sach> sachList = sachDAO.getAllSach();
                Sach vuilongchonSach = new Sach("-1", "--Vui lòng chọn tên sách--", "-1", "-1", "-1", "-1", "-1", "-1","-1");
                sachList.add(0, vuilongchonSach);
                SpinnerSachAdapter spinnerSachAdapter = new SpinnerSachAdapter(sachList, viewGroup.getContext());
                spnTenSachSuaHoaDon.setAdapter(spinnerSachAdapter);

                spnTenNguoiMuaSuaHoaDon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                spnTenNguoiTaoSuaHoaDon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                spnTenSachSuaHoaDon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            maSach = null;
                            tacGia = null;
                            nhaXuatBan = null;
                            ngayNhap = null;
                            theLoai = null;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                txtNgayTaoSuaHoaDon.getEditText().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        final int y = calendar.get(Calendar.YEAR);
                        final int m = calendar.get(Calendar.MONTH);
                        final int d = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(viewGroup.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtNgayTaoSuaHoaDon.getEditText().setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, y, m, d);
                        datePickerDialog.show();
                    }
                });
                btnSuaHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(txtSoLuongSuaHoaDon.getEditText().getText().toString().trim()) == 0) {
                            Toast.makeText(viewGroup.getContext(), "Không nhập số lượng là 0", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Integer.parseInt(soLuongSach) == 0) {
                            Toast.makeText(viewGroup.getContext(), "Sách " + tenSach + " hết hàng", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Integer.parseInt(txtSoLuongSuaHoaDon.getEditText().getText().toString().trim()) > Integer.parseInt(soLuongSach)) {
                            Toast.makeText(viewGroup.getContext(), "Số lượng sách còn " + soLuongSach + ", không mua lớn hơn !", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (tenNguoimua == null) {
                            Toast.makeText(viewGroup.getContext(), "Bạn chưa chọn tên người mua", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (tenNguoiTao == null) {
                            Toast.makeText(viewGroup.getContext(), "Bạn chưa chọn tên người tạo", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (tenSach == null) {
                            Toast.makeText(viewGroup.getContext(), "Bạn chưa chọn tên sách", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            int tongTien = Integer.parseInt(giaSach) * Integer.parseInt(txtSoLuongSuaHoaDon.getEditText().getText().toString().trim());
                            HoaDon hoaDon = new HoaDon();
                            hoaDon.setMaHoaDon(hoaDonList.get(i).getMaHoaDon());
                            hoaDon.setNgayTao(txtNgayTaoSuaHoaDon.getEditText().getText().toString().trim());
                            hoaDon.setTenNguoiMua(tenNguoimua);
                            hoaDon.setTenNguoiTao(tenNguoiTao);
                            hoaDon.setTenSach(tenSach);
                            hoaDon.setGia(giaSach);
                            hoaDon.setSoLuong(txtSoLuongSuaHoaDon.getEditText().getText().toString().trim());
                            hoaDon.setTongTien(String.valueOf(tongTien));
                            boolean checkNgayTao = checkEmpty(hoaDon.getNgayTao(), txtNgayTaoSuaHoaDon);
                            boolean checkSoLuong = checkEmpty(hoaDon.getSoLuong(), txtSoLuongSuaHoaDon);
                            if (checkNgayTao && checkSoLuong) {
                                HoaDonDAO hoaDonDAO = new HoaDonDAO(mySQLite);
                                boolean ketQua = hoaDonDAO.suaHoaDon(hoaDon);
                                if (ketQua) {
                                    Toast.makeText(viewGroup.getContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                    Sach sach = new Sach();
                                    sach.setMaSach(maSach);
                                    sach.setTenSach(tenSach);
                                    sach.setGiaSach(giaSach);
                                    sach.setTacGia(tacGia);
                                    sach.setNhaXuatBan(nhaXuatBan);
                                    sach.setTheLoai(theLoai);
                                    sach.setSoLuong(String.valueOf(Integer.parseInt(soLuongSach) - Integer.parseInt(txtSoLuongSuaHoaDon.getEditText().getText().toString().trim())));
                                    SachDAO sachDAO = new SachDAO(mySQLite);
                                    sachDAO.suaSach(sach);
                                    List<Sach> sachList1 = sachDAO.getAllSach();
                                    SachAdapter sachAdapter = new SachAdapter(sachList1, viewGroup.getContext());
                                    MainActivityDanhSachSach.lvDanhSachSach.setAdapter(sachAdapter);
                                    hoaDonDAO = new HoaDonDAO(new MySQLite(viewGroup.getContext()));
                                    hoaDonList = new ArrayList<>();
                                    hoaDonList = hoaDonDAO.getAllHoaDon();
                                    final HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(hoaDonList,viewGroup.getContext());
                                    MainActivityDanhSachHoaDon.lvDanhSachHoaDon.setAdapter(hoaDonAdapter);
                                } else {
                                    Toast.makeText(viewGroup.getContext(), "Sửa không thành công", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(viewGroup.getContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
                btnCancelHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }

        });
        return view;
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
