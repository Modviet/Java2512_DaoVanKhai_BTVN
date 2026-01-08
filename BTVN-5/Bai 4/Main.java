package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductManager manager = new ProductManager();
        int choice;

        do {
            System.out.println("\n-----------Menu------------");
            System.out.println("1.Them san pham.");
            System.out.println("2.Hien thi % thong ke kho hang.");
            System.out.println("3.Tim san pham co gia re nhat.");
            System.out.println("4.Cap nhat thong tin san pham.");
            System.out.println("5.Xoa san pham.");
            System.out.println("6.Sap xep kho hang.");
            System.out.println("0.Thoat");
            System.out.println("Vui long chon chuc nang");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println(" Nhap ID : ");
                    String id = sc.nextLine();

                    System.out.println("Nhap ten san pham : ");
                    String name = DataCleaner.formatName(sc.nextLine());

                    System.out.println("Nhap gia san pham : ");
                    double price = Double.parseDouble(sc.nextLine());

                    System.out.println("Nhap so luong : ");
                    int qty = Integer.parseInt(sc.nextLine());

                    boolean ok = manager.themSanPham(
                            new Product(id, name, price, qty)
                    );
                    System.out.println(ok ? "Them thanh cong" : "Khong the them san pham");
                }

                case 2 -> {
                    manager.showAll();
                }

                case 3 ->{
                    Product product = manager.sanPhamGiaRe();
                    System.out.println(product != null ? product :"Kho trong");
                }


                case 4 -> {
                    System.out.println("Nhap ID hoac ten san pham : ");
                    String key = sc.nextLine();

                    System.out.println("Ten san pham moi: ");
                    String name = DataCleaner.formatName(sc.nextLine());

                    System.out.println("Gia moi : ");
                    double price = Double.parseDouble(sc.nextLine());

                    System.out.println("So luong moi : ");
                    int qty = Integer.parseInt(sc.nextLine());

                    boolean ok = manager.capNhatSanPham(key,
                            new Product(key, name, price, qty));
                    System.out.println(ok ? "Cap nhat thanh cong " : "Cap nhat that bai");
                }

                case 5 -> {
                    System.out.println("Nhap ID san pham can xoa : ");
                    String id = sc.nextLine();
                    System.out.println(
                            manager.xoaSanPham(id) ? "Da xoa san pham" : "Khong tim thay"
                    );
                }

                case 6 ->{
                    manager.sapXepSanPham();
                    manager.showAll();
                }

                case 0 ->{
                    System.out.println("Thoat chuong trinh");
                }

                default->
                    System.out.println("Lua chon khong hop le !");
            }

        } while (choice < 7);
    }

}