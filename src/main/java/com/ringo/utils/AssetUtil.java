package com.ringo.utils;

import com.mysql.cj.exceptions.SSLParamsException;
import com.ringo.exceptions.ParamsException;

import java.lang.reflect.Parameter;

public class AssetUtil {
    public static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamsException(msg);
        }
    }
}
