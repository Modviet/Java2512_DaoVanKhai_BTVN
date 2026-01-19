package com.example.bai8;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();

        manager.addTransaction(new DespositTransaction("D01",2000));
        manager.addTransaction(new DespositTransaction("D02",3000));
        manager.addTransaction(new DespositTransaction("D03",300));
        manager.addTransaction(new WithdrawTransaction("W01",2000));
        manager.addTransaction(new WithdrawTransaction("W02",800));

        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("------MENU-------");
            System.out.println("1.Hien thi tat ca giao dich.");
            System.out.println("2.Loc giao dich theo so tien yeu cau.");
            System.out.println("3.Tinh thue cho cac giao dich rut tien.");
            System.out.println("4.Sap xep cac giao dich tang dan.");
            System.out.println("0.Thoat chuong trinh.");
            System.out.println("Vui long chon chuc nang : ");
            choice = sc.nextInt();

            switch (choice){
                case 1 :
                    manager.showAll();
                    break;
                case 2 :
                    manager.filterByAmount();
                    break;
                case 3 :
                    manager.calculateTax();
                    break;
                case 4 :
                    manager.sort();
                    break;
                case 0 :
                    System.out.println("Thoat chuong trinh");
                    break;
                default:
                    System.out.println("Vui long chon lai chuc nang.");
            }
        } while (choice !=0);
        sc.close();
    }
}
