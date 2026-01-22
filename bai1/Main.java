package com.example.bai9.bai1;

import javax.sound.midi.InvalidMidiDataException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Chuong trinh Quan Ly Nhan Su IT");
        System.out.println("Nhap vao so phong ban trong cong ty : ");
        int n = new Scanner(System.in).nextInt();
        EmployeeManager manager = new EmployeeManager(n);
        int choice = 0;

        do{
            System.out.println("=======MENU CHUC NANG========");
            System.out.println("1.Them nhan vien vao phong ban");
            System.out.println("2.Hien thi toan bo nhan vien cong ty");
            System.out.println("3.Tim kiem nhan vien theo luong hoac ky nang");
            System.out.println("0.Thoat chuong trinh");
            System.out.println("Vui long chon chuc nang");
            choice = new Scanner(System.in).nextInt();

            switch(choice){
                case 1:
                    manager.inputDepartmentData();
                    break;
                case 2:
                    manager.displayCompanyInfo();
                    break;
                case 3 :
                    manager.filterEmployee();
                    break;
                case 0 :
                    System.out.println("Thoat chuong trinh");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }while (choice !=4);
    }

}
