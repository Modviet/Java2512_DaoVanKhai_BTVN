package com.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap chuoi ki tu : ");

        String str = sc.nextLine();

        System.out.println("Do dai cua chuoi ki tu vua nhap : "+str.length());

        System.out.println("Chuoi ki tu o dang in hoa : "+str.toUpperCase());

        System.out.println("Chuoi ki tu o dang viet thuong : "+str.toLowerCase());

        String strTrim = str.trim();

        System.out.println("Chuoi sau khi trim: \"" + strTrim + "\"");
        System.out.printf("Do dai chuoi sau khi dung trim : "+strTrim.length());

        sc.close();

    }

}



