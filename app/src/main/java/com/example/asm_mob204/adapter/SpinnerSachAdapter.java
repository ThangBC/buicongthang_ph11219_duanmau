package com.example.asm_mob204.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_mob204.R;
import com.example.asm_mob204.model.Sach;
import com.example.asm_mob204.model.TheLoai;

import java.util.List;

public class SpinnerSachAdapter extends BaseAdapter {
    List<Sach> sachList;
    Context context;

    public SpinnerSachAdapter(List<Sach> sachList, Context context) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_spinner_ten_sach,viewGroup,false);
        TextView tvTenSach = view.findViewById(R.id.tvTenSach);
        Sach sach = sachList.get(i);
        tvTenSach.setText(sach.getTenSach()+" - "+sach.getGiaSach());
        return view;
    }
}
