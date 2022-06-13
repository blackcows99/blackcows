package com.hanghae99.blackcows.exceptions;

public class AccessDeniedException extends Exception{
    public AccessDeniedException(){
        super("로그인이 필요한 서비스 입니다.");
    }
}
