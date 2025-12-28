package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
           Scanner sc  = new Scanner(System.in);

        System.out.println("Nhap chuoi : ");
        String str = sc.nextLine();

        int[] count = new int[256];

        for(int i =0;i<str.length();i++) {
            char c = str.charAt(i);
            count[c]++;
        }

        System.out.println("So lan xuat hien cua cac ki tu : ");
        for(int i = 0;i< 256;i++){
            if(count[i] > 0){
                System.out.println(" "+(char) i + ": "+count[i]);
            }
        }
    }

}



