package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,3,5,7,4};

        System.out.print("Mang ban dau : ");
        for(int x : arr){
            System.out.print(x+" ");
        }

        for(int i =0;i<arr.length/2;i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - i -1];
            arr[arr.length - i -1] = temp;
        }


        System.out.print("Mang sau khi dao nguoc : ");
        for(int x: arr){
            System.out.print(x + " ");
        }
    }

}



