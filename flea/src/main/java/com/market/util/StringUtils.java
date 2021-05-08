package com.market.util;

import java.util.UUID;

public class StringUtils {
    public static String getUUID(){
        String uuid= UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
