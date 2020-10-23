package com.example.asm_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asm_mob204.adapter.SachBanChayAdapter;
import com.example.asm_mob204.model.HoaDon;
import com.example.asm_mob204.sqlite.HoaDonDAO;
import com.example.asm_mob204.sqlite.MySQLite;

import java.util.List;

public class MainActivitySachBanChay extends AppCompatActivity {
    public static ListView lvSachBanChay;
    EditText txtLocSachBanChay;
    Button btnLocSachBanChay;
    private MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sach_ban_chay);
        lvSachBanChay = findViewById(R.id.lvSachBanChay);
        txtLocSachBanChay = findViewById(R.id.txtLocSachBanChay);
        btnLocSachBanChay = findViewById(R.id.btnLocSachBanChay);
        mySQLite = new MySQLite(this);
        btnLocSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(txtLocSachBanChay.getText().toString().trim())>13 ||Integer.parseInt(txtLocSachBanChay.getText().toString().trim())<0){
                    Toast.makeText(MainActivitySachBanChay.this, "Không đúng định dạng tháng (1-12)", Toast.LENGTH_SHORT).show();
                }else {
                    HoaDonDAO hoaDonDAO = new HoaDonDAO(mySQLite);
                    List<HoaDon> hoaDonList = hoaDonDAO.sachBanChay(txtLocSachBanChay.getText().toString().trim());
                    SachBanChayAdapter sachBanChayAdapter = new SachBanChayAdapter(hoaDonList,MainActivitySachBanChay.this);
                    lvSachBanChay.setAdapter(sachBanChayAdapter);
                    if(hoaDonList.size()==0){
                        Toast.makeText(MainActivitySachBanChay.this, "Không có sách nào được mua trong tháng "+txtLocSachBanChay.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}