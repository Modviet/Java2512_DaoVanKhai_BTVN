package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[30];
        int count = 0;
        int select;

        do {
            System.out.println("\n===========Menu=========");
            System.out.println("1. Nhap danh sach sinh vien");
            System.out.println("2. Hien thi bang diem");
            System.out.println("3.Tim sinh vien theo ID");
            System.out.println("0.Thoat");
            System.out.print("Chon chuc nang : ");

           select = Integer.parseInt(sc.nextLine());


            switch (select) {
                case 1 :
                    System.out.println("Nhap so luong sinh vien :  ");
                    int n = Integer.parseInt(sc.nextLine());

                    for(int i =0;i<n;i++){
                        System.out.println("Sinh vien thu "+(i+1));
                        System.out.println("ID : ");
                        String id = sc.nextLine();

                        System.out.println("Ho ten : ");
                        String name = sc.nextLine();

                        System.out.println("Diem chuyen can : ");
                        double point = Double.parseDouble(sc.nextLine());

                        System.out.println("Diem thi : ");
                        double exam = Double.parseDouble(sc.nextLine());

                        students[count++] = new Student(id,name,point,exam);
                    }
                    break;

                case 2 :
                    System.out.println("\nID\tTen\tDiem Cuoi\tXep Loai");
                    for(int i = 0;i<count;i++){
                        System.out.println(
                                students[i].getId()+" Ho ten : "
                                +students[i].getName() + " - Diem cuoi ki : "
                                +String.format("%.2f", students[i].calculateFinalScore()) + "\t"+" - Xep loai : "
                                +students[i].getGrade()
                        );
                    }
                    break;

                case 3 :
                    System.out.println("Nhap ID can tim : ");
                    String searchId = sc.nextLine();
                    boolean found = false;

                    for(int i = 0;i<count;i++){
                        if(students[i].getId().equals(searchId)){
                            System.out.println("Tim thay : "
                                    + students[i].getName()
                                    + " Diem : "
                                    + String.format("%.2f", students[i].calculateFinalScore()) + "\t"
                                    + " Xep loai : "
                                    + students[i].getGrade());
                            found = true;
                            break;
                        }
                    }

                    if(!found) {
                        System.out.println("Khong tim thay sinh vien.");
                    }
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;

                default :
                    System.out.println("Lua chon khong hop le.");
            }
        }
        while (select >= 3);
    }

}