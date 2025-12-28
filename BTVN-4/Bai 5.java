package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String data = "Java,Python,C++,PHP,JavaScript";
        String[] database = data.split(",");
        System.out.println("Mang con duoc in ra : ");
        for(String dataname : database){
            System.out.println(dataname);
        }

        boolean check = data.startsWith("Java");
        int index = data.indexOf('P');

        if(index != -1){
            System.out.println("Ki tu 'P' xuat hien dau tien tai : "+index);
        } else {
            System.out.println("Khong co ki tu 'P' trong chuoi.");
        }

    }

}



