package com.example.asm_mob204.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob204.MainActivityDanhSachSach;
import com.example.asm_mob204.MainActivityNhapSach;
import com.example.asm_mob204.R;
import com.example.asm_mob204.model.Sach;
import com.example.asm_mob204.model.TheLoai;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.SachDAO;
import com.example.asm_mob204.sqlite.TheLoaiDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SachAdapter extends BaseAdapter {
    List<Sach> sachList;
    Context context;
    String tenTheLoai, isSale;

    public SachAdapter(List<Sach> sachList, Context context) {
        this.sachList = sachList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sachList.size();
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview_danh_sach_sach, viewGroup, false);
        TextView tvMaSachNhapSach = view.findViewById(R.id.tvMaSachNhapSach);
        TextView tvTenSachNhapSach = view.findViewById(R.id.tvTenSachNhapSach);
        TextView tvGiaSachNhapSach = view.findViewById(R.id.tvGiaSachNhapSach);
        TextView tvTacGiaNhapSach = view.findViewById(R.id.tvTacGiaNhapSach);
        TextView tvNhaXuatBanNhapSach = view.findViewById(R.id.tvNhaXuatBanNhapSach);
        TextView tvTheLoaiNhapSach = view.findViewById(R.id.tvTheLoaiNhapSach);
        TextView tvSoLuongNhapSach = view.findViewById(R.id.tvSoLuongNhapSach);
        TextView tvNgayNhapNhapSach = view.findViewById(R.id.tvNgayNhapNhapSach);
        TextView tvIsSaleNhapSach = view.findViewById(R.id.tvIsSaleNhapSach);
        tvMaSachNhapSach.setText("Mã sách: " + sachList.get(i).getMaSach());
        tvTenSachNhapSach.setText("Tên sách: " + sachList.get(i).getTenSach());
        tvGiaSachNhapSach.setText("Giá sách: " + String.valueOf(sachList.get(i).getGiaSach()));
        tvTacGiaNhapSach.setText("Tác giả: " + sachList.get(i).getTacGia());
        tvNhaXuatBanNhapSach.setText("Nhà xuất bản: " + sachList.get(i).getNhaXuatBan());
        tvTheLoaiNhapSach.setText("Thể loại: " + sachList.get(i).getTheLoai());
        tvSoLuongNhapSach.setText("Số lượng: " + sachList.get(i).getSoLuong());
        tvNgayNhapNhapSach.setText("Ngày nhập: " + sachList.get(i).getThoiGianNhap());
        tvIsSaleNhapSach.setText("Giảm giá ?:" + sachList.get(i).getIsSale());
        view.findViewById(R.id.imgDeleteSach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SachDAO sachDAO = new SachDAO(new MySQLite(viewGroup.getContext()));
                String maSach = sachList.get(i).getMaSach();
                boolean ketQua = sachDAO.xoaSach(maSach);
                if (ketQua) {
                    Toast.makeText(viewGroup.getContext(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    sachList.remove(i);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(viewGroup.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.imgEditSach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_sua_sach, null);
                builder.setCancelable(false);
                builder.setTitle("Sửa sách");
                builder.setView(view1);
                Button btnCancelSach = view1.findViewById(R.id.btnCancelSach);
                Button btnSuaSach = view1.findViewById(R.id.btnSuaSach);
                final TextInputLayout txtTenSachSuaSach = view1.findViewById(R.id.txtTenSachSuaSach);
                final TextInputLayout txtGiaSachSuaSach = view1.findViewById(R.id.txtGiaSachSuaSach);
                final TextInputLayout txtTacGiaSuaSach = view1.findViewById(R.id.txtTacGiaSuaSach);
                final TextInputLayout txtNhaXuatBanSuaSach = view1.findViewById(R.id.txtNhaXuatBanSuaSach);
                final Spinner spnTheLoaiSuaSach = view1.findViewById(R.id.spnTheLoaiSuaSach);
                final TextInputLayout txtSoLuongSuaSach = view1.findViewById(R.id.txtSoLuongSuaSach);
                final TextInputLayout txtThoiGianNhapSuaSach = view1.findViewById(R.id.txtThoiGianNhapSuaSach);
                final Spinner spnIsSaleSuaSach = view1.findViewById(R.id.spnIsSaleSuaSach);
                final AlertDialog alertDialog = builder.show();
                final MySQLite mySQLite = new MySQLite(viewGroup.getContext());
                final TheLoaiDAO theLoaiDAO = new TheLoaiDAO(mySQLite);
                final List<TheLoai> theLoaiList = theLoaiDAO.getAllTheLoai();
                TheLoai vuilongchon = new TheLoai("-1", "--Vui lòng chọn thể loại--", "-1");
                theLoaiList.add(0, vuilongchon);
                SpinnerTheLoaiAdapter spinnerAdapter = new SpinnerTheLoaiAdapter(theLoaiList, viewGroup.getContext());
                spnTheLoaiSuaSach.setAdapter(spinnerAdapter);
                spnTheLoaiSuaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                final String[] sale = {"Vui lòng chọn kiểu giảm giá","Sale", "No sale"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(viewGroup.getContext(), android.R.layout.simple_spinner_item, sale);
                spnIsSaleSuaSach.setAdapter(adapter);
                spnIsSaleSuaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i > 0) {
                            isSale = sale[i];
                        } else {
                            isSale = null;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                txtThoiGianNhapSuaSach.getEditText().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        final int y = calendar.get(Calendar.YEAR);
                        final int m = calendar.get(Calendar.MONTH);
                        final int d = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(viewGroup.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtThoiGianNhapSuaSach.getEditText().setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, y, m, d);
                        datePickerDialog.show();
                    }
                });
                btnSuaSach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Sach sach = new Sach();
                        sach.setMaSach(sachList.get(i).getMaSach());
                        sach.setTenSach(txtTenSachSuaSach.getEditText().getText().toString().trim());
                        sach.setGiaSach(txtGiaSachSuaSach.getEditText().getText().toString().trim());
                        sach.setTacGia(txtTacGiaSuaSach.getEditText().getText().toString().trim());
                        sach.setNhaXuatBan(txtNhaXuatBanSuaSach.getEditText().getText().toString().trim());
                        sach.setTheLoai(tenTheLoai);
                        sach.setSoLuong(txtSoLuongSuaSach.getEditText().getText().toString().trim());
                        sach.setThoiGianNhap(txtThoiGianNhapSuaSach.getEditText().getText().toString().trim());
                        sach.setIsSale(isSale);
                        SachDAO sachDAO = new SachDAO(new MySQLite(viewGroup.getContext()));
                        boolean ketQua = sachDAO.suaSach(sach);
                        if (ketQua) {
                            Toast.makeText(viewGroup.getContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                            sachDAO = new SachDAO(new MySQLite(viewGroup.getContext()));
                            sachList = new ArrayList<>();
                            sachList = sachDAO.getAllSach();
                            final SachAdapter sachAdapter = new SachAdapter(sachList, context);
                            MainActivityDanhSachSach.lvDanhSachSach.setAdapter(sachAdapter);
                        } else {
                            Toast.makeText(viewGroup.getContext(), "Sửa không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancelSach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

            }
        });
        return view;
    }
}
