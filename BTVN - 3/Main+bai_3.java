package com.example;


import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
          do {
              System.out.println("================MENU================");
              System.out.println("1.Tro choi doan so");
              System.out.println("2.Tinh lai suat ngan hang");
              System.out.println("3.Sap xep mang tang dan");
              System.out.println("4.Tach chu so va tinh tong");
              System.out.println("5.Thoat chuong trinh.");
              System.out.println("Vui long chon chuc nang : ");
              choice = sc.nextInt();

              switch (choice) {
                  case 1 :
                      randomNumber();
                      break;

                  case 2 :
                      System.out.println("Nhap so tien dinh gui : ");
                      double money = sc.nextDouble();
                      System.out.println("Nhap lai suat ben ngan hang (%): ");
                      double rate = sc.nextDouble();
                      rate = rate / 100;
                      System.out.println("Nhap so nam dinh gui tiet kiem : ");
                      int years = sc.nextInt();
                      System.out.println("So tien sau "+years + "nam "+ tinhLaiSuat(money,rate,years));
                      break;

                  case 3 :
                      System.out.println("Nhap so phan tu trong mang : ");
                      int n = sc.nextInt();
                      int[] arr = new int[n];
                       for(int i=0;i<n;i++){
                           System.out.println("a["+i+"] = ");
                           arr[i] = sc.nextInt();
                      }
                       sapXepMang(arr);
                      System.out.println("Mang sau khi sap xep : ");
                      for(int x : arr){
                          System.out.println(x + " ");
                      }
                      System.out.println();
                      break;

                  case 4 :
                      System.out.println("Nhap so x : ");
                      int x = sc.nextInt();
                      System.out.println("Tong cac chu so trong so la : "+tinhTong(x));
                      break;

                  case 5 :
                      System.out.println("Thoat chuong trinh.");
                      break;

                  default:
                      System.out.println("Vui long nhap lai lua chon.");
              }
          } while (choice !=5);
        }

        public static void randomNumber(){
            Random rd = new Random();
            Scanner sc = new Scanner(System.in);

            int sercet = rd.nextInt(100)+1;
            int guess , turns = 5;

            System.out.println("Ban co 5 luot de doan so tu 1 - 100.");

            while (turns > 0){
                System.out.println("Nhap so du doan : ");
                guess = sc.nextInt();
                turns--;

                if(guess == sercet){
                    System.out.println("Chuc mung ban da doan dung.");
                    return;
                } else if(guess > sercet) {
                    System.out.println("Hay nhap so nho hon.");
                } else {
                    System.out.println("Hay nhap so lon hon.");
                }
            }
            System.out.println("Het luot ban qua ga. So dung la "+sercet);
        }


        public static double tinhLaiSuat(double money,double rate,int years){
           for(int i = 1; i<=years;i++){
               money = money *(1 + rate);
           }
           return money;
        }

        public static void sapXepMang(int[] arr){
              for(int i =0 ; i < arr.length - 1 ;i++){
                  for(int j = 0;j< arr.length -1-i ;j++){
                      if(arr[j] > arr[j+1]){
                          int temp = arr[j];
                          arr[j] = arr[j+1];
                          arr[j+1] = temp;
                      }
                  }
              }
        }

        public static int tinhTong(int n){
           int sum = 0;
           while(n>0){
               sum += n % 10;
               n /= 10;
           }
           return sum;
        }

}


