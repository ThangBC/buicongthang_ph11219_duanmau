package com.example.asm_mob204.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_mob204.R;
import com.example.asm_mob204.model.HoaDon;

import java.util.List;

public class SachBanChayAdapter extends BaseAdapter {
    List<HoaDon> hoaDonList;
    private Context context;

    public SachBanChayAdapter(List<HoaDon> hoaDonList, Context context) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview_sach_ban_chay,
                viewGroup, false);

        TextView tvTenSachSachBanChay = view.findViewById(R.id.tvTenSachSachBanChay);
        TextView tvSoLuongSachBanChay = view.findViewById(R.id.tvSoLuongSachBanChay);

        tvTenSachSachBanChay.setText("Tên sách: "+hoaDonList.get(i).getTenSach());
        tvSoLuongSachBanChay.setText("Số lượng sách bán được: "+hoaDonList.get(i).getSoLuong());

        return view;
    }
}
