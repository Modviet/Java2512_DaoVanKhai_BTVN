package com.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sentence = "Học viện công nghệ thông tin T3H";
        int index = sentence.indexOf("T3H");
        System.out.println("Vi tri cua ki tu 'T3H' : "+index);

        String char1 = sentence.substring(index);
        System.out.println(char1);

        char char5 = sentence.charAt(4);
        System.out.println("Ki tu o vi tri index thu 5 la : "+ char5);

    }

}



