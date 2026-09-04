package com.folder.user_service.util;

public final class NameUtils {

     private NameUtils(){
         throw new UnsupportedOperationException("Utility class");
     }

     public static String buildFullName(String firstName, String lastName) {

         firstName = firstName == null ? "" : firstName.trim();
         lastName = lastName == null ? "" : lastName.trim();

         return (firstName + " " + lastName).trim();
     }

    /**
     * Tach FullName ra thanh FirstName va Last Name
     */

    public static String[] splitFullName(String fullName) {

        if (fullName == null || fullName.isBlank()) {
            return new String[]{"", ""};
        }

        fullName = fullName.trim();

        int lastSpace = fullName.lastIndexOf(' ');

        if (lastSpace == -1) {
            return new String[]{fullName, ""};
        }

        String firstName = fullName.substring(0, lastSpace).trim();
        String lastName = fullName.substring(lastSpace + 1).trim();

        return new String[]{firstName, lastName};
    }
}
