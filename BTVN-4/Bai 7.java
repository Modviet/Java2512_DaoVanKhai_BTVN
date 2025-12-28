package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String s1 = "t3h";
        String s2 = new String("t3h");

        if(s1 == s2){
            System.out.println("Hai chuoi giong nhau : TRUE ");
        } else {
            System.out.println("Hai chuoi khac nhau : FASLE");
        }
         // Doi voi cai so sanh nay thi no se so sanh dia chi o nho  s1 nam o String Pool , s2 nam o HEAP --> khac nhau dia chi FASLE
        if(s1.equals(s2)){
            System.out.println("Hai chuoi giong nhau : TRUE ");
        } else {
            System.out.println("Hai chuoi khac nhau : FASLE");
        }

        // Doi voi so sanh nay chu yeu la so sanh noi dung --> giong nhau TRUE.


        String a ="JAVA";
        String b ="java";

        if(a.equalsIgnoreCase(b)){
            System.out.println("Hai chuoi bang nhau - Khong phan biet viet hoa hay thuong");
        } else {
            System.out.println("Hai chuoi khac nhau");
        }
    }

}



