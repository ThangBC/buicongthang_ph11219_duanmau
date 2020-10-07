package com.example.asm_mob204.model;

public class TaiKhoan {
    private String tenTaiKhoan;
    private String hoVaTen;
    private String matKhau;
    private String emai;
    private String soDienThoai;
    private String ngaySinh;
    private String gioiTinh;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String hoVaTen, String matKhau, String emai, String soDienThoai, String ngaySinh, String gioiTinh) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.hoVaTen = hoVaTen;
        this.matKhau = matKhau;
        this.emai = emai;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
