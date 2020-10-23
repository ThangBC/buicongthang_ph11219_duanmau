package com.example.asm_mob204.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_mob204.R;
import com.example.asm_mob204.model.KhachHang;
import com.example.asm_mob204.model.Sach;

import java.util.List;

public class SpinnerKhachHangAdapter extends BaseAdapter {
    List<KhachHang> khachHangList;
    Context context;

    public SpinnerKhachHangAdapter(List<KhachHang> khachHangList, Context context) {
        this.khachHangList = khachHangList;
        this.context = context;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_spinner_ten_khach_hang,viewGroup,false);
        TextView tvTenKhachHang = view.findViewById(R.id.tvTenKhachHang);
        KhachHang khachHang = khachHangList.get(i);
        tvTenKhachHang.setText(khachHang.getTenKhachHang());
        return view;
    }
}
