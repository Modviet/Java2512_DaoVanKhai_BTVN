package com.example.BTL1;

public class DataCleaner {
    // chuan hoa ten cua san pham //

    public static String formatName(String name){
        if(name== null){
            return "";
        }

        name = name.trim();
        if(name.isEmpty())
            return "";

        String[] words = name.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for(String word : words){
            if(word.length() > 0){
                word = word.toLowerCase();

                sb.append(Character.toUpperCase(word.charAt(0)));
                if(word.length() > 1){
                    sb.append(word.substring(1));
                }
                sb.append("");
            }
        }
        return sb.toString().trim();
    }

}
