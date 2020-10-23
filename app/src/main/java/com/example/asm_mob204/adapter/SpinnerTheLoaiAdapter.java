package com.example.asm_mob204.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_mob204.R;
import com.example.asm_mob204.model.TheLoai;

import java.util.List;

public class SpinnerTheLoaiAdapter extends BaseAdapter {
    List<TheLoai> theLoaiList;
    Context context;

    public SpinnerTheLoaiAdapter(List<TheLoai> theLoaiList, Context context) {
        this.theLoaiList = theLoaiList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return theLoaiList.size();
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_spinner_the_loai,viewGroup,false);
        TextView tvTenTheLoaiSach = view.findViewById(R.id.tvTenTheLoaiSach);
        TheLoai theLoai = theLoaiList.get(i);
        tvTenTheLoaiSach.setText(theLoai.getTenTheLoai());
        return view;
    }
}
