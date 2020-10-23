package com.example.asm_mob204.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob204.MainActivityDanhSachTheLoai;
import com.example.asm_mob204.MainActivityThemTheLoai;
import com.example.asm_mob204.R;
import com.example.asm_mob204.model.TheLoai;
import com.example.asm_mob204.sqlite.MySQLite;
import com.example.asm_mob204.sqlite.TheLoaiDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    List<TheLoai> theLoaiList;
    Context context;

    public TheLoaiAdapter(List<TheLoai> theLoaiList, Context context) {
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
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview_danh_sach_the_loai,viewGroup,false);
        TextView tvMaTheLoaiThemTheLoai = view.findViewById(R.id.tvMaTheLoaiThemTheLoai);
        TextView tvTheLoaiThemTheLoai = view.findViewById(R.id.tvTheLoaiThemTheLoai);
        TextView tvNgayThemThemTheLoai = view.findViewById(R.id.tvNgayThemThemTheLoai);
        tvMaTheLoaiThemTheLoai.setText("Mã thể loại: "+theLoaiList.get(i).getMaTheLoai());
        tvTheLoaiThemTheLoai.setText("Tên thể loại: "+theLoaiList.get(i).getTenTheLoai());
        tvNgayThemThemTheLoai.setText("Ngày thêm: "+theLoaiList.get(i).getNgayThem());
        view.findViewById(R.id.imgDeleteTheLoai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoaiDAO theLoaiDAO = new TheLoaiDAO(new MySQLite(viewGroup.getContext()));
                String maTheLoai = theLoaiList.get(i).getMaTheLoai();
                boolean ketQua = theLoaiDAO.xoaTheLoai(maTheLoai);
                if(ketQua){
                    Toast.makeText(viewGroup.getContext(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    theLoaiList.remove(i);
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(viewGroup.getContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.imgEditTheLoai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_sua_the_loai,null);
                builder.setCancelable(false);
                builder.setTitle("Sửa thể loại");
                builder.setView(view1);
                Button btnCancelTheLoai = view1.findViewById(R.id.btnCancelTheLoai);
                Button btnSuaTheLoai = view1.findViewById(R.id.btnSuaTheLoai);
                final TextInputLayout txtTenTheLoaiSuaTheLoai = view1.findViewById(R.id.txtTenTheLoaiSuaTheLoai);
                final TextInputLayout txtNgayThemSuaTheLoai = view1.findViewById(R.id.txtNgayThemSuaTheLoai);
                final AlertDialog alertDialog = builder.show();
                txtNgayThemSuaTheLoai.getEditText().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        final int y =calendar.get(Calendar.YEAR);
                        final int m = calendar.get(Calendar.MONTH);
                        final int d = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(viewGroup.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtNgayThemSuaTheLoai.getEditText().setText(day+"/"+(month+1)+"/"+year);
                            }
                        },y,m,d);
                        datePickerDialog.show();
                    }
                });
                btnSuaTheLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TheLoai theLoai = new TheLoai();
                        theLoai.setMaTheLoai(theLoaiList.get(i).getMaTheLoai());
                        theLoai.setTenTheLoai(txtTenTheLoaiSuaTheLoai.getEditText().getText().toString().trim());
                        theLoai.setNgayThem(txtNgayThemSuaTheLoai.getEditText().getText().toString().trim());
                        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(new MySQLite(viewGroup.getContext()));
                        boolean ketQua = theLoaiDAO.suaTheLoai(theLoai);
                        if(ketQua){
                            Toast.makeText(viewGroup.getContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                            theLoaiDAO = new TheLoaiDAO(new MySQLite(viewGroup.getContext()));
                            theLoaiList = new ArrayList<>();
                            theLoaiList = theLoaiDAO.getAllTheLoai();
                            final TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(theLoaiList,context);
                            MainActivityDanhSachTheLoai.lvDanhSachTheLoai.setAdapter(theLoaiAdapter);
                        }else {
                            Toast.makeText(viewGroup.getContext(), "Sửa không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancelTheLoai.setOnClickListener(new View.OnClickListener() {
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
