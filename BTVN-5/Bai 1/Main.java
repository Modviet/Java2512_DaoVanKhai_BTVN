package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Nhap ho ten : ");
        String name = sc.nextLine();

        System.out.printf("Nhap so thu tu : ");
        int index = sc.nextInt();

        String nameAfter = DataCleaner.formatName(name);
        String id = DataCleaner.generateID(name,index);

        System.out.println("Ho va ten : "+nameAfter);
        System.out.println("Ma ID : "+id);

        sc.close();
    }

}