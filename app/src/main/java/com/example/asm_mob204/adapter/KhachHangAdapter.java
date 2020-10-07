package com.example.asm_mob204.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob204.MainActivityDanhSachNguoiDung;
import com.example.asm_mob204.MainActivityThemNguoiDung;
import com.example.asm_mob204.R;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.sqlite.KhachHangDAO;
import com.example.asm_mob204.sqlite.MySQLite;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class KhachHangAdapter extends BaseAdapter {
    private List<KhachHang> khachHangList;
    private Context context;
    public KhachHangAdapter(Context context,List<KhachHang> khachHangList) {
        this.context = context;
        this.khachHangList = khachHangList;
    }

    @Override
    public int getCount() {
        return khachHangList.size();
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview_danh_sach_nguoi_dung,
                viewGroup, false);
        TextView tvMaNguoiDung = view.findViewById(R.id.tvMaNguoiDung);
        TextView tvTenNguoiDungThemNguoiDung = view.findViewById(R.id.tvTenNguoiDungThemNguoiDung);
        TextView tvSoDienThoaiThemNguoiDung = view.findViewById(R.id.tvSoDienThoaiThemNguoiDung);
        tvMaNguoiDung.setText("Mã người dùng: "+khachHangList.get(i).getMaKhachHang());
        tvTenNguoiDungThemNguoiDung.setText("Tên người dùng: "+khachHangList.get(i).getTenKhachHang());
        tvSoDienThoaiThemNguoiDung.setText("Số điện thoại: "+khachHangList.get(i).getSoDienThoaiKhachHang());
        view.findViewById(R.id.imgDeleteNguoiDung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhachHangDAO khachHangDAO =new KhachHangDAO(new MySQLite(viewGroup.getContext()));
                String maKhachHang = khachHangList.get(i).getMaKhachHang();
                boolean ketQua = khachHangDAO.xoaKhachHang(maKhachHang);
                if(ketQua){
                    Toast.makeText(viewGroup.getContext(), "Xóa thành công !!!", Toast.LENGTH_SHORT).show();
                    khachHangList.remove(i);
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(viewGroup.getContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.imgEditNguoiDung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 =LayoutInflater.from(context).inflate(R.layout.dialog_sua_nguoi_dung,null);
                builder.setCancelable(false);
                builder.setTitle("Sửa người dùng");
                builder.setView(view1);
                Button btnCancelNguoiDung = view1.findViewById(R.id.btnCancelNguoiDung);
                Button btnSuaNguoiDung = view1.findViewById(R.id.btnSuaNguoiDung);
                final TextInputLayout txtTenNguoiDungSuaNguoiDung = view1.findViewById(R.id.txtTenNguoiDungSuaNguoiDung);
                final TextInputLayout txtSoDienThoaiSuaNguoiDung = view1.findViewById(R.id.txtSoDienThoaiSuaNguoiDung);
                final AlertDialog alertDialog = builder.show();
                btnSuaNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        KhachHang khachHang = new KhachHang();
                        khachHang.setMaKhachHang(khachHangList.get(i).getMaKhachHang());
                        khachHang.setTenKhachHang(txtTenNguoiDungSuaNguoiDung.getEditText().getText().toString().trim());
                        khachHang.setSoDienThoaiKhachHang(txtSoDienThoaiSuaNguoiDung.getEditText().getText().toString().trim());
                        KhachHangDAO khachHangDAO = new KhachHangDAO(new MySQLite(viewGroup.getContext()));
                        boolean ketQua = khachHangDAO.suaKhachHang(khachHang);
                        if (ketQua) {
                            Toast.makeText(viewGroup.getContext(), "Sửa thành công !!!", Toast.LENGTH_SHORT).show();
                            khachHangDAO = new KhachHangDAO(new MySQLite(viewGroup.getContext()));
                            khachHangList = new ArrayList<>();
                            khachHangList =khachHangDAO.getAllKhachHang();
                            final KhachHangAdapter khachHangAdapter = new KhachHangAdapter(context,khachHangList);
                            MainActivityDanhSachNguoiDung.lvDanhSachNguoiDung.setAdapter(khachHangAdapter);
                        }else{
                            Toast.makeText(viewGroup.getContext(), "Sửa không thành công", Toast.LENGTH_SHORT).show();
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
