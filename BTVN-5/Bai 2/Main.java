package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = new Product[100];
        int count = 0;

        System.out.println("Nhap so luong san pham : ");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0;i<n;i++){
            System.out.println("San pham thu "+(i+1));
            System.out.println("ID : ");
            String id = sc.nextLine();

            System.out.println("Ten san pham : ");
            String name = DataCleaner.formatName(sc.nextLine());

            System.out.println("Gia san pham : ");
            double price = sc.nextDouble();

            System.out.println("So luong : ");
            int quantity = sc.nextInt();
            sc.nextLine();

            products[count++] = new Product(id,name,price,quantity);
        }

        System.out.println("\nDanh sach san pham : ");
        for(int i = 0;i<count;i++){
            System.out.println(
                    products[i].getId() + " -  "
                    +products[i].getName() + " - Gia :  "
                    +products[i].getPrice() + " - So luong :  "
                    +products[i].getQuantity()
            );
        }

        // San pham re nhat trong kho hang
        Product cheapest = findCheapest(products,count);
        System.out.println("\nSan pham re nhat : "
                          +cheapest.getName()
                          + " ( "+cheapest.getPrice()+")" );

        // Tong gia tri kho hang
        System.out.println("Tong gia tri cua kho hang : "+ calculateTotalValue(products,count));
    }

    public static Product findCheapest(Product[] list,int size){
        Product cheapest = list[0];
        for(int i=1; i< size;i++){
            if(list[i].getPrice() < cheapest.getPrice()){
                cheapest = list[i];
            }
        }
        return cheapest;
    }

    public static double calculateTotalValue(Product[] list,int size){
        double total = 0;
        for(int i = 0;i<size;i++){
            total += list[i].getPrice() * list[i].getQuantity();
        }
        return total;
    }

}