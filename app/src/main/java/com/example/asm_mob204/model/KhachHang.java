package com.example.asm_mob204.model;

public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private String soDienThoaiKhachHang;

    public KhachHang() {
    }

    public KhachHang(String maKhachHang, String tenKhachHang, String soDienThoaiKhachHang) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoaiKhachHang() {
        return soDienThoaiKhachHang;
    }

    public void setSoDienThoaiKhachHang(String soDienThoaiKhachHang) {
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
    }
}
