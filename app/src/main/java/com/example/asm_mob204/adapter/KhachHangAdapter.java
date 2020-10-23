package com.example.asm_mob204.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob204.MainActivityDanhSachKhachHang;
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview_danh_sach_khach_hang,
                viewGroup, false);
        TextView tvMaKhachHangThemKhachHang = view.findViewById(R.id.tvMaKhachHangThemKhachHang);
        TextView tvTenKhachHangThemKhachHang = view.findViewById(R.id.tvTenKhachHangThemKhachHang);
        TextView tvSoDienThoaiThemKhachHang = view.findViewById(R.id.tvSoDienThoaiThemKhachHang);
        tvMaKhachHangThemKhachHang.setText("Mã khách hàng: "+khachHangList.get(i).getMaKhachHang());
        tvTenKhachHangThemKhachHang.setText("Tên khách hàng: "+khachHangList.get(i).getTenKhachHang());
        tvSoDienThoaiThemKhachHang.setText("Số điện thoại: "+khachHangList.get(i).getSoDienThoaiKhachHang());
        view.findViewById(R.id.imgDeleteKhachHang).setOnClickListener(new View.OnClickListener() {
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
        view.findViewById(R.id.imgEditKhachHang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 =LayoutInflater.from(context).inflate(R.layout.dialog_sua_khach_hang,null);
                builder.setCancelable(false);
                builder.setTitle("Sửa khách hàng");
                builder.setView(view1);
                Button btnCancelKhachHang = view1.findViewById(R.id.btnCancelKhachHang);
                Button btnSuaKhachHang = view1.findViewById(R.id.btnSuaKhachHang);
                final TextInputLayout txtTenKhachHangSuaKhachHang = view1.findViewById(R.id.txtTenKhachHangSuaKhachHang);
                final TextInputLayout txtSoDienThoaiSuaKhachHang = view1.findViewById(R.id.txtSoDienThoaiSuaKhachHang);
                final AlertDialog alertDialog = builder.show();
                btnSuaKhachHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        KhachHang khachHang = new KhachHang();
                        khachHang.setMaKhachHang(khachHangList.get(i).getMaKhachHang());
                        khachHang.setTenKhachHang(txtTenKhachHangSuaKhachHang.getEditText().getText().toString().trim());
                        khachHang.setSoDienThoaiKhachHang(txtSoDienThoaiSuaKhachHang.getEditText().getText().toString().trim());
                        KhachHangDAO khachHangDAO = new KhachHangDAO(new MySQLite(viewGroup.getContext()));
                        boolean ketQua = khachHangDAO.suaKhachHang(khachHang);
                        if (ketQua) {
                            Toast.makeText(viewGroup.getContext(), "Sửa thành công !!!", Toast.LENGTH_SHORT).show();
                            khachHangDAO = new KhachHangDAO(new MySQLite(viewGroup.getContext()));
                            khachHangList = new ArrayList<>();
                            khachHangList =khachHangDAO.getAllKhachHang();
                            final KhachHangAdapter khachHangAdapter = new KhachHangAdapter(context,khachHangList);
                            MainActivityDanhSachKhachHang.lvDanhSachKhachHang.setAdapter(khachHangAdapter);
                        }else{
                            Toast.makeText(viewGroup.getContext(), "Sửa không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancelKhachHang.setOnClickListener(new View.OnClickListener() {
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
