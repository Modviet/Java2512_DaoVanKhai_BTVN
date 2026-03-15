package com.example.bai14.service;

import com.example.bai14.dao.SinhVienDao;
import com.example.bai14.entity.LopHoc;
import com.example.bai14.entity.SinhVien;

import javax.swing.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class SinhVienService {
    SinhVienDao sinhVienDao = new SinhVienDao();

    //Them sinh vien moi//
    public void themSinhVien(){
        LopHocService lopHocService = new LopHocService();
        System.out.println("Nhap vao ten lop hoc ma sinh vien muon them : ");
        lopHocService.showAllLopHoc();

        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ma lop : ");
        int maLop = sc.nextInt();
        sc.nextLine();

        System.out.println("Nhap vao ho ten sinh vien : ");
        String hoTen = sc.nextLine();
        System.out.println("Nhap vao ngay sinh sinh vien (yyyy-MM-dd): ");
        String ngaySinhStr = sc.nextLine();
        Date ngaySinh = Date.valueOf(ngaySinhStr);
        System.out.println("Nhap vao email sinh vien: ");
        String email = sc.nextLine();
        System.out.println("Nhap vao hoc phi cua sinh vien: ");
        float hocPhi = sc.nextFloat();

        SinhVien sinhVien = new SinhVien();
        sinhVien.setHoTen(hoTen);
        sinhVien.setNgaySinh(ngaySinh);
        sinhVien.setEmail(email);
        sinhVien.setHocPhi(hocPhi);
        sinhVien.setMaLop(maLop);
        sinhVienDao.themSinhVien(sinhVien);
    }

    // Hien thi thong tin sinh vien//
    public void showAllSinhVien(){
        List<SinhVien> sinhViens = sinhVienDao.getAllSinhVienWithLopHoc();
        for(SinhVien sv : sinhViens){
            System.out.println(sv);
        }
    }

    // Tim kiem sinh vien qua ten hoac ID.
    public void searchSinhVien(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten hoac ID sinh vien can tim : ");
        String keyword = sc.nextLine();

       List<SinhVien> sinhViens = sinhVienDao.searchSinhVien(keyword);

       if(sinhViens.isEmpty()){
           System.out.println("Khong tim thay sinh vien!");
           return;
       }
       for(SinhVien sv: sinhViens){
           System.out.println(sv);
       }

    }

    //cap nhat thong tin sinh vien//
    public void updateSinhVien(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Nhap ten hoac ma sinh vien can cap nhat : ");
        String keyword = sc.nextLine();
        List<SinhVien> sinhViens = sinhVienDao.searchSinhVien(keyword);

        if(sinhViens.isEmpty()){
            System.out.println("Khong tim thay sinh vien!");
            return;
        }

        for(SinhVien sv : sinhViens){
            LopHoc lopHoc = new LopHoc();
            System.out.println(
                    "MaSV : "+sv.getMaSv()+
                    "| Ten : "+sv.getHoTen()+
                    "| Lop : "+sv.getLopHoc().getTenLop());
        }
        System.out.println("Nhap ma sinh vien can cap nhat : ");
        int maSv = sc.nextInt();
        sc.nextLine();

        System.out.println("Nhap ten moi : ");
        String hoTen = sc.nextLine();
        System.out.println("Nhap vao ngay sinh sinh vien (yyyy-MM-dd): ");
        String ngaySinhStr = sc.nextLine();
        Date ngaySinh = Date.valueOf(ngaySinhStr);
        System.out.println("Nhap email moi : ");
        String email = sc.nextLine();
        System.out.println("Nhap hoc phi moi : ");
        float hocPhi = sc.nextFloat();
        System.out.println("Nhap ma lop moi : ");
        int maLop = sc.nextInt();

        SinhVien sv = new SinhVien();
        sv.setMaSv(maSv);
        sv.setHoTen(hoTen);
        sv.setEmail(email);
        sv.setHocPhi(hocPhi);
        sv.setMaLop(maLop);
        sv.setNgaySinh(ngaySinh);
        sinhVienDao.updateSinhVien(sv);
        System.out.println("Cap nhat thanh cong!");
    }

    // Xoa sinh vien : bang cach kiem tra qua ma hoac ten truoc khi xac nhan xoa thong tin.
    public void deleteSinhVien(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten hoac ma sinh vien can xoa : ");
        String keyword = sc.nextLine();
        List<SinhVien> sinhViens = sinhVienDao.searchSinhVien(keyword);
        if(sinhViens.isEmpty()){
            System.out.println("Khong tim thay sinh vien!");
            return;
        }
        for(SinhVien sinhVien : sinhViens){
            System.out.println(
                    "MaSV: " + sinhVien.getMaSv() +
                            " | Ten: " + sinhVien.getHoTen() +
                            " | Lop: " + sinhVien.getLopHoc().getTenLop()
            );
        }

        System.out.println("Ban co chan chan muon xoa (Yes/No) : ");
        String keybar = sc.nextLine();
        if(keybar.equalsIgnoreCase("Yes")){
           for(SinhVien sinhVien : sinhViens){
               sinhVienDao.deleteSinhVien(sinhVien.getMaSv());
           }
            System.out.println("Da xoa thanh cong!");
        } else {
            System.out.println("Yeu cau da bi huy bo");
        }
    }

}

