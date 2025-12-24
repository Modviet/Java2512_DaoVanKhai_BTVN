package com.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
          do {
              System.out.println("================MENU================");
              System.out.println("1.Tinh tong cac so tu 1 den n : ");
              System.out.println("2.Hien thi bang cuu chuong tu 2 den 9");
              System.out.println("3.Dem tong chu so trong 1 so : ");
              System.out.println("4.Thoat chuong trinh.");
              System.out.println("Vui long chon chuc nang : ");
              choice = sc.nextInt();

              switch (choice) {
                  case 1 :
                      System.out.println("Nhap so n : ");
                      int n = sc.nextInt();
                      System.out.println("Tong cac so tu 1 den "+n+": "+tinhTong(n));
                      break;

                  case 2 :
                      inBangCuuChuong();
                      break;

                  case 3 :
                      System.out.println("Nhap so can kiem tra N : ");
                      int x = sc.nextInt();
                      System.out.println("So "+ x +" co "+demSo(x)+ " chu so.");
                      break;

                  case 4 :
                      System.out.println("Thoat chuong trinh.");
                      break;

                  default:
                      System.out.println("Vui long nhap lai lua chon.");
              }
          } while (choice !=4);

        }

        public static int tinhTong(int n) {
              int sum = 0;
              for(int i =1;i<=n;i++){
                 sum += i;
              }
              return sum;
        }

        public static void inBangCuuChuong(){
           for(int i=2;i<=9;i++){
               System.out.println("Bang cuu chuong "+i+": ");
                for(int j = 1;j<=10;j++){
                    System.out.println(i+ " x "+j+" = "+(i * j));
               }
               System.out.println();
           }
        }

        public static int demSo(int n){
          int count = 0;
          while(n>0){
              count++;
              n /=10;
          }
          return count;
        }
}


