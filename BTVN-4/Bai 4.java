package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);
       int[] arr = new int[9];

       for(int i = 0;i<arr.length;i++){
           System.out.printf("Phan tu thu "+(i+1)+ ": " );
           arr[i] = sc.nextInt();
       }

       int max = arr[0];
       int min = arr[0];

       for(int x : arr){
           max = Math.max(max,x);
           min = Math.min(min,x);
       }

        System.out.println("Phan tu lon nhat trong mang : "+max);
        System.out.println("Phan tu nho nhat trong mang : "+min);

        Arrays.sort(arr);
        System.out.println("Mang sau khi sap xep : ");
        for(int number : arr){
            System.out.println(number);
        }
    }

}



