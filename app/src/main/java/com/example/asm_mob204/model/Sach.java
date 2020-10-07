package com.example.asm_mob204.model;

public class Sach {
    private String maSach;
    private String tenSach;
    private double giaSach;
    private String tacGia;
    private String nhaXuatBan;
    private String theLoai;
    private int soLuong;
    private String thoiGianNhap;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, double giaSach, String tacGia, String nhaXuatBan, String theLoai, int soLuong, String thoiGianNhap) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaSach = giaSach;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.thoiGianNhap = thoiGianNhap;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public double getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(double giaSach) {
        this.giaSach = giaSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getThoiGianNhap() {
        return thoiGianNhap;
    }

    public void setThoiGianNhap(String thoiGianNhap) {
        this.thoiGianNhap = thoiGianNhap;
    }
}
