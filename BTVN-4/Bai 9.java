package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(checkName("T3H"));
        System.out.println(checkName("t3h"));
        System.out.println(checkName(null));
    }

    public static boolean checkName(String name){
        if("T3H".equals(name)){
            return true;
        } else {
            return false;
        }
    }

    // CACH GOI : name.equals("T3H") KHONG AN TOAN BOI NEU name == null thi se gap loi.

}



