package com.example.users.utils;

public class StringUtils {
    public static String capitalizeString(String str){
        if(str == null)
            return null;
        if(str.isEmpty())
            return "";

        char firstCharacter = str.charAt(0);
        char upperCaseFirstCharacter = Character.toUpperCase(firstCharacter);

        String stringWithoutFirstCharacter = str.substring(1);
        return upperCaseFirstCharacter + stringWithoutFirstCharacter;
    }

    public static String getGetterMethodNameByFieldName(String variableName){
        return "get" + capitalizeString(variableName);
    }

    public static String getSetterMethodNameByFieldName(String variableName){
        return "set" + capitalizeString(variableName);
    }
}
