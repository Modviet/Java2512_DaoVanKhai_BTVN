package com.java.shopweb.utils;

public class StringUtils {

    public static Integer getInteger(String str){
        try{
            return Integer.parseInt(str);
        } catch (Exception e){
            return -1;
        }
    }
}
