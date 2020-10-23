package com.example.asm_mob204.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob204.MainActivityDanhSachKhachHang;
import com.example.asm_mob204.MainActivityDanhSachNguoiDung;
import com.example.asm_mob204.MainActivityDanhSachSach;
import com.example.asm_mob204.R;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.model.NguoiDung;
import com.example.asm_mob204.model.Sach;
import com.example.asm_mob204.model.TheLoai;
import com.example.asm_mob204.sqlite.KhachHangDAO;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.NguoiDungDAO;
import com.example.asm_mob204.sqlite.SachDAO;
import com.example.asm_mob204.sqlite.TheLoaiDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> nguoiDungList;
    Context context;

    public NguoiDungAdapter(List<NguoiDung> nguoiDungList, Context context) {
        this.nguoiDungList = nguoiDungList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nguoiDungList.size();
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview_danh_sach_nguoi_dung,viewGroup,false);
        TextView tvTenTaiKhoanThemNguoiDung = view.findViewById(R.id.tvTenTaiKhoanThemNguoiDung);
        TextView tvHoVaTenThemNguoiDung = view.findViewById(R.id.tvHoVaTenThemNguoiDung);
        TextView tvMatKhauThemNguoiDung = view.findViewById(R.id.tvMatKhauThemNguoiDung);
        final TextView tvEmailThemNguoiDung = view.findViewById(R.id.tvEmailThemNguoiDung);
        TextView tvSoDienThoaiThemNguoiDung = view.findViewById(R.id.tvSoDienThoaiThemNguoiDung);
        TextView tvNgaySinhThemNguoiDung = view.findViewById(R.id.tvNgaySinhThemNguoiDung);
        TextView tvGioiTinhThemNguoiDung = view.findViewById(R.id.tvGioiTinhThemNguoiDung);
        TextView tvDiaChiThemNguoiDung = view.findViewById(R.id.tvDiaChiThemNguoiDung);
        tvTenTaiKhoanThemNguoiDung.setText("Tên tài khoản: "+nguoiDungList.get(i).getTenTaiKhoan());
        tvHoVaTenThemNguoiDung.setText("Họ và tên: "+nguoiDungList.get(i).getHoVaTen());
        tvMatKhauThemNguoiDung.setText("Mật khẩu: "+nguoiDungList.get(i).getMatKhau());
        tvEmailThemNguoiDung.setText("Email: "+nguoiDungList.get(i).getEmai());
        tvSoDienThoaiThemNguoiDung.setText("Số điện thoại: "+nguoiDungList.get(i).getSoDienThoai());
        tvNgaySinhThemNguoiDung.setText("Ngày sinh: "+nguoiDungList.get(i).getNgaySinh());
        tvGioiTinhThemNguoiDung.setText("Giới tính: "+nguoiDungList.get(i).getGioiTinh());
        tvDiaChiThemNguoiDung.setText("Địa chỉ: "+nguoiDungList.get(i).getDiaChi());
        view.findViewById(R.id.imgDeleteNguoiDung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(new MySQLite(viewGroup.getContext()));
                String tenTaiKhoan = nguoiDungList.get(i).getTenTaiKhoan();
                boolean ketQua = nguoiDungDAO.xoaNguoiDung(tenTaiKhoan);
                if(ketQua){
                    Toast.makeText(viewGroup.getContext(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    nguoiDungList.remove(i);
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(viewGroup.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.imgEditNguoiDung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View view1 =LayoutInflater.from(context).inflate(R.layout.dialog_sua_nguoi_dung,null);
                builder.setCancelable(false);
                builder.setTitle("Sửa người dùng");
                builder.setView(view1);
                Button btnCancelNguoiDung = view1.findViewById(R.id.btnCancelNguoiDung);
                Button btnSuaNguoiDung = view1.findViewById(R.id.btnSuaNguoiDung);
                final RadioGroup rdogroupSuaNguoiDung = view1.findViewById(R.id.rdogroupSuaNguoiDung);
                final TextInputLayout txtHoVaTenSuaNguoiDung = view1.findViewById(R.id.txtHoVaTenSuaNguoiDung);
                final TextInputLayout txtMatKhauSuaNguoiDung = view1.findViewById(R.id.txtMatKhauSuaNguoiDung);
                final TextInputLayout txtEmailSuaNguoiDung = view1.findViewById(R.id.txtEmailSuaNguoiDung);
                final TextInputLayout txtSoDienThoaiSuaNguoiDung = view1.findViewById(R.id.txtSoDienThoaiSuaNguoiDung);
                final TextInputLayout txtNgaySinhSuaNguoiDung = view1.findViewById(R.id.txtNgaySinhSuaNguoiDung);
                final TextInputLayout txtDiaChiSuaNguoiDung = view1.findViewById(R.id.txtDiaChiSuaNguoiDung);
                final AlertDialog alertDialog = builder.show();
                txtNgaySinhSuaNguoiDung.getEditText().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        final int y =calendar.get(Calendar.YEAR);
                        final int m = calendar.get(Calendar.MONTH);
                        final int d = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(viewGroup.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtNgaySinhSuaNguoiDung.getEditText().setText(day+"/"+(month+1)+"/"+year);
                            }
                        },y,m,d);
                        datePickerDialog.show();
                    }
                });
                btnSuaNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioButton rdobtnSuaNguoiDung;
                        int radioId = rdogroupSuaNguoiDung.getCheckedRadioButtonId();
                        rdobtnSuaNguoiDung = view1.findViewById(radioId);
                        NguoiDung nguoiDung = new NguoiDung();
                        nguoiDung.setTenTaiKhoan(nguoiDungList.get(i).getTenTaiKhoan());
                        nguoiDung.setHoVaTen(txtHoVaTenSuaNguoiDung.getEditText().getText().toString().trim());
                        nguoiDung.setMatKhau(txtMatKhauSuaNguoiDung.getEditText().getText().toString().trim());
                        nguoiDung.setEmai(txtEmailSuaNguoiDung.getEditText().getText().toString().trim());
                        nguoiDung.setSoDienThoai(txtSoDienThoaiSuaNguoiDung.getEditText().getText().toString().trim());
                        nguoiDung.setNgaySinh(txtNgaySinhSuaNguoiDung.getEditText().getText().toString().trim());
                        nguoiDung.setGioiTinh(rdobtnSuaNguoiDung.getText().toString().trim());
                        nguoiDung.setDiaChi(txtDiaChiSuaNguoiDung.getEditText().getText().toString().trim());
                        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(new MySQLite(viewGroup.getContext()));
                        boolean ketQua = nguoiDungDAO.suaNguoiDung(nguoiDung);
                        boolean checkEmail =  (!TextUtils.isEmpty(txtEmailSuaNguoiDung.getEditText().getText().toString().trim()) && Patterns.EMAIL_ADDRESS.matcher(txtEmailSuaNguoiDung.getEditText().getText().toString().trim()).matches());
                        if(checkEmail){
                            if (ketQua) {
                                Toast.makeText(viewGroup.getContext(), "Sửa thành công !!!", Toast.LENGTH_SHORT).show();
                                nguoiDungDAO = new NguoiDungDAO(new MySQLite(viewGroup.getContext()));
                                nguoiDungList = new ArrayList<>();
                                nguoiDungList =nguoiDungDAO.getAllNguoiDung();
                                final NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList,context);
                                MainActivityDanhSachNguoiDung.lvDanhSachNguoiDung.setAdapter(nguoiDungAdapter);
                            }else{
                                Toast.makeText(viewGroup.getContext(), "Sửa không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(viewGroup.getContext(), "Không đúng định dạng email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancelNguoiDung.setOnClickListener(new View.OnClickListener() {
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
