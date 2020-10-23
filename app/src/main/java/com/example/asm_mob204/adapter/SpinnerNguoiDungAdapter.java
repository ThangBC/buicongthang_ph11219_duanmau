package com.example.asm_mob204.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_mob204.R;
import com.example.asm_mob204.model.NguoiDung;
import com.example.asm_mob204.model.Sach;

import java.util.List;

public class SpinnerNguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> nguoiDungList;
    Context context;

    public SpinnerNguoiDungAdapter(List<NguoiDung> nguoiDungList, Context context) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_spinner_ten_nguoi_dung,viewGroup,false);
        TextView tvTenNguoiDung = view.findViewById(R.id.tvTenNguoiDung);
        NguoiDung nguoiDung = nguoiDungList.get(i);
        tvTenNguoiDung.setText(nguoiDung.getHoVaTen());
        return view;
    }
}
