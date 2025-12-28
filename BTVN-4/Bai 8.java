package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int arr[][] = new int[3][3];

        Scanner sc = new Scanner(System.in);

        int sum = 0;

        System.out.println("Nhap cac phan tu trong mang hai chieu : ");
        for (int i = 0 ; i<3;i++){
            for(int j = 0;j<3;j++){
                System.out.println("arr["+i+"]["+j+"] = ");
                arr[i][j] = sc.nextInt();
                sum += arr[i][j];
            }
        }

        System.out.println("Mang vua nhap : ");
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("Tong cac phan tu trong mang : "+sum);
        sc.close();

    }

}



