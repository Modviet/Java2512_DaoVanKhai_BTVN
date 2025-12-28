package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = "radar";
        boolean isPalindrome = true;

        for(int i = 0;i<str.length() / 2 ;i++){
            if(str.charAt(i) != str.charAt(str.length()-1-i)){
                isPalindrome = false;
                break;
            }
        }

        if(isPalindrome) {
            System.out.println("Day la chuoi doi xung");
        } else {
            System.out.println("Khong phai la chuoi doi xung");
        }
    }

}



