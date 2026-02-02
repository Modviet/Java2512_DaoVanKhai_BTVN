package com.example.bai10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        int choice;

        do{
            System.out.println("========BANK ONLINE MENU=======");
            System.out.println("1.Mo tai khoan moi.");
            System.out.println("2.Nap /Rut tien.");
            System.out.println("3.Chuyen khoan noi bo.");
            System.out.println("4.Loc tai khoan VIP");
            System.out.println("5.Tinh lai suat");
            System.out.println("0.Thoat");
            System.out.println("Lua chon cua ban : ");

            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice){
                    case 1:
                        manager.registerAccount();
                        break;

                    case 2:
                        manager.depositWithdraw();
                        break;

                    case 3:
                        manager.transferMoney();
                        break;

                    case 4 :
                        manager.statistics();
                        break;

                    case 5 :
                        manager.endOfMonth();
                        break;

                    case 0:
                        System.out.println("Thoat chuong trinh");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le");
                }
            } catch(NumberFormatException e){
                System.out.println("Vui long nhap lai so hop le.");
                choice =-1;
            }

        } while (choice !=0);
        sc.close();
    }
}
