package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);

        System.out.println("Nhap so phan tu trong mang : ");
        int n = sc.nextInt();

        int[][] arr = new int[n][n];

        System.out.println("Nhap ma tran : ");
        for(int i = 0; i<n;i++){
            for(int j = 0; j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int sumChinh = 0;
        int sumPhu = 0;

        for(int i = 0;i<n;i++){
            sumChinh += arr[i][i];
            sumPhu += arr[i][n-1-i];
        }

        if(n%2 == 1){
            sumPhu -= arr[n/2][n/2];
        }

        System.out.println("Tong duong cheo chinh : "+sumChinh);
        System.out.println("Tong duong cheo phu : "+sumPhu);
        System.out.println("Tong hai duong cheo : "+(sumChinh + sumPhu));

        sc.close();
    }

}



