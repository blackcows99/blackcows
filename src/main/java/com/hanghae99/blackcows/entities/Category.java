package com.hanghae99.blackcows.entities;

public enum Category {
    All, COMPUTER, PHONE, WEARABLE, HOME_APPLIANCES, ETC;
    public static String getName(int idx){
        return values()[idx].name();
    }
}
