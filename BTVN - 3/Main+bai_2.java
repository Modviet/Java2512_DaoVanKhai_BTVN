package com.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
          do {
              System.out.println("================MENU================");
              System.out.println("1.Kiem tra so nguyen to.");
              System.out.println("2.Kiem tra tong cua cac uoc cua 1 so.");
              System.out.println("3.In ra day Fibonacci toi n.");
              System.out.println("4.Tim uoc chung lon nhat cua 2 so.");
              System.out.println("5.Thoat chuong trinh.");
              System.out.println("Vui long chon chuc nang : ");
              choice = sc.nextInt();

              switch (choice) {
                  case 1 :
                      System.out.println("Nhap so n : ");
                      int n = sc.nextInt();
                      if(isPrime(n)){
                          System.out.println(n + " la so nguyen to.");
                      } else {
                          System.out.println(n + " khong phai so nguyen to.");
                      }

                      break;

                  case 2 :
                      System.out.println("Nhap so n : ");
                      int n1 = sc.nextInt();
                      if(isPerfect(n1)){
                          System.out.println(n1 + " la so hoan hao.");
                      } else {
                          System.out.println(n1 + " khong phai so hoan hao");
                      }
                      break;

                  case 3 :
                      System.out.println("Nhap n : ");
                      int n2 = sc.nextInt();
                      printFibonacci(n2);
                      break;

                  case 4 :
                      System.out.println("Nhap a : ");
                      int a = sc.nextInt();
                      System.out.println("Nhap b : ");
                      int b = sc.nextInt();
                      System.out.println("Uoc chung lon nhat : "+UCLN(a,b));
                      break;

                  case 5 :
                      System.out.println("Thoat chuong trinh.");
                      break;

                  default:
                      System.out.println("Vui long nhap lai lua chon.");
              }
          } while (choice !=4);
        }

        public static boolean isPrime(int n){
              if( n <2)
                  return false;

              for(int i=2;i<=Math.sqrt(n);i++){
                  if(n % i == 0)
                      return false;
              }
              return true;
        }

        public static boolean isPerfect(int n){
              int sum = 0;
              for(int i = 1;i <= n/2 ;i++){
                  if( n % i ==0)
                      sum +=i;
              }
              return sum == n;
        }

        public static void printFibonacci(int n){
           int a = 0, b =1;
            System.out.println("Day Fibonacci : ");
            for(int i = 1;i<=n;i++){
                System.out.println(a+ " ");
                int temp = a + b;
                a = b;
                b=temp;
            }
            System.out.println();
        }

        public static int UCLN(int a , int b){
            while( b != 0){
                int r = a % b;
                a = b;
                b = r;
            }
            return a;
        }
}


