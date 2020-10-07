package com.example.asm_mob204.model;

public class TheLoai {
    private String maTheLoai;
    private String tenTheLoai;
    private String ngayThem;

    public TheLoai() {
    }

    public TheLoai(String maTheLoai, String tenTheLoai, String ngayThem) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.ngayThem = ngayThem;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(String ngayThem) {
        this.ngayThem = ngayThem;
    }
}
