package com.Zyuchen.common.utils;

public class TypeUtils {
    public static boolean StringIsNumber(String str){
        for(int i = 0; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }

        return true;
    }

}
