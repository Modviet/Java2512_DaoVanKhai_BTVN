package com.example.bai14.entity;


import java.sql.Date;

public class SinhVien {

    private int maSv;
    private String hoTen;
    private Date ngaySinh;
    private String email;
    private float hocPhi;
    private int maLop;

    private LopHoc lopHoc;

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    public SinhVien(int maSv, String hoTen, Date ngaySinh, String email, float hocPhi, int maLop) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.hocPhi = hocPhi;
        this.maLop = maLop;
    }

    public SinhVien() {
    }

    public int getMaSv() {
        return maSv;
    }

    public void setMaSv(int maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    @Override
    public String toString() {
        return "ID : " + maSv +
                ", Ho ten : '" + hoTen + '\'' +
                ", Ngay sinh :" + ngaySinh +
                ", email : '" + email + '\'' +
                ", Hoc phi : " + hocPhi +
                ", Ma lop : " + maLop +
                ". Ten lop : "+lopHoc.getTenLop()+
                '}';
    }
}
