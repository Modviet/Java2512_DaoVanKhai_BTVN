package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // C1 : voi String thuong :
       String str = "Gura say : ";

       for(int i=0;i< 10000;i++){
           str = str +"A";
       }

        System.out.println("Do dai cua chuoi : "+str.length());


       //C2 : su dung StringBuilder :
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<10000;i++){
            sb.append("A");
        }

        String ketqua = sb.toString();
        System.out.println("Do dai cua chuoi : "+ketqua.length());

        // StringBuilder ung dung cho ung dung don luong : nhanh hon , khong dong bo.
        // StringBuffer ung dung cho ung dung da luong : dong bo , an toan trong da luong.
    }

}



