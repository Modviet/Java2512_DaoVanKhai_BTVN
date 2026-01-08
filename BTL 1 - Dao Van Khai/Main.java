package com.example;


import com.example.BTL1.Product;
import com.example.BTL1.ProductManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

          Scanner sc= new Scanner(System.in);
          ProductManager manager = new ProductManager();
          int choice;

          do {
              System.out.println("\n------ MENU -----");
              System.out.println("1. Them san pham moi");
              System.out.println("2. Tim san pham theo ten");
              System.out.println("3. Xoa san pham theo ID");
              System.out.println("4. Sap xep theo gia giam dan");
              System.out.println("5. Thong ke kho hang");
              System.out.println("0. Thoat chuong trinh");
              System.out.print("Vui long chon chuc nang : ");

              choice = Integer.parseInt(sc.nextLine());

              switch (choice) {
                  case 1 :
                      System.out.println("Nhap Id cua danh muc(0-4)");
                      int cateId = Integer.parseInt(sc.nextLine());

                      System.out.println("Nhap Id cua san pham : ");
                      int id = Integer.parseInt(sc.nextLine());

                      System.out.println("Nhap ten cua san pham : ");
                      String name = sc.nextLine();

                      System.out.println("Nhap gia cua san pham : ");
                      double price = Double.parseDouble(sc.nextLine());

                      System.out.println("Nhap so luong : ");
                      int quantity = Integer.parseInt(sc.nextLine());

                      Product product = new Product(id,name,price,quantity);
                      System.out.println(manager.addProduct(cateId,product));
                      break;

                  case 2 :
                      System.out.println("Nhap tu khoa : ");
                      manager.searchByName(sc.nextLine());
                      break;

                  case 3 :
                      System.out.println("Nhap ID san pham can xoa : ");
                      manager.deleteById(Integer.parseInt(sc.nextLine()));
                      break;

                  case 4 :
                      manager.sortByPriceDesc();
                      break;

                  case 5 :
                      manager.staticic();
                      break;

                  case 0 :
                      System.out.println("Thoat chuong trinh");
                      break;
                  default:
                      System.out.println("Lua chon khong hop le.");

              }

          } while (choice != 0);
    }

}