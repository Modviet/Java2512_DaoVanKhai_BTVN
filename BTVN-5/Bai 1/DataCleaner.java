package com.example;

public class DataCleaner {
    private String name;
    private String index;

    public DataCleaner(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public DataCleaner(){
    }

    public static String formatName(String name){
        if(name == null || name.trim().isEmpty()){
            return "";
        }

        String[] words = name.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for(String word : words){
            result.append(word.substring(0,1).toUpperCase())
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return result.toString().trim();
    }

    public static String generateID(String name,int index){
        String formattedName = formatName(name);
        if(formattedName.isEmpty()){
            return "";
        }

        String[] words = formattedName.split(" ");
        StringBuilder id = new StringBuilder();

        for(String word : words){
            id.append(word.charAt(0));
        }

        id.append(index);
        return id.toString();
    }
}
