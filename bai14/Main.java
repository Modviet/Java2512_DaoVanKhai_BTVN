package com.example.bai14;

import com.example.bai14.entity.SinhVien;
import com.example.bai14.service.SinhVienService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SinhVienService sinhVienService = new SinhVienService();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("=======QUAN LY SINH VIEN========");
            System.out.println("1.Hien thi thong tin sinh vien.");
            System.out.println("2.Them sinh vien moi.");
            System.out.println("3.Cap nhat sinh vien.");
            System.out.println("4.Xoa sinh vien.");
            System.out.println("5.Tim kiem sinh vien.");
            System.out.println("0.Thoat");

            System.out.println("Vui long chon chuc nang: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){

                case 1:
                    sinhVienService.showAllSinhVien();
                    break;

                case 2:
                    sinhVienService.themSinhVien();
                    break;

                case 3:
                    sinhVienService.updateSinhVien();
                    break;

                case 4:
                    sinhVienService.deleteSinhVien();
                    break;

                case 5:
                    sinhVienService.searchSinhVien();
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh");
                    return;

                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
