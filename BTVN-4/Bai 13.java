package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] arr = {3,4,5,6,7,8,2};

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for(int x : arr){
            if(x > max1){
                max2= max1;
                max1 = x;
            } else if(x > max2 && x < max1){
                max2 = x;
            }
        }

        if(max2 == Integer.MIN_VALUE){
            System.out.println("Khong ton tai gia tri lon thu hai trong mang");
        } else {
            System.out.println("Gia tri lon thu hai : "+max2);
        }
    }

}



