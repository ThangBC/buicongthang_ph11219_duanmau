package com.example.asm_mob204.model;

public class HoaDon {
    private String maHoaDon;
    private String ngayTao;
    private String tenNguoiMua;
    private String tenNguoiTao;
    private String tenSach;
    private String gia;
    private String soLuong;
    private String tongTien;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, String ngayTao, String tenNguoiMua, String tenNguoiTao, String tenSach, String gia, String soLuong, String tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tenNguoiMua = tenNguoiMua;
        this.tenNguoiTao = tenNguoiTao;
        this.tenSach = tenSach;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenNguoiMua() {
        return tenNguoiMua;
    }

    public void setTenNguoiMua(String tenNguoiMua) {
        this.tenNguoiMua = tenNguoiMua;
    }

    public String getTenNguoiTao() {
        return tenNguoiTao;
    }

    public void setTenNguoiTao(String tenNguoiTao) {
        this.tenNguoiTao = tenNguoiTao;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
