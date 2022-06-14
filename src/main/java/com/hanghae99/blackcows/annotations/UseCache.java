package com.hanghae99.blackcows.annotations;

import com.hanghae99.blackcows.interfaces.ReturnDTO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCache {
    Class<? extends ReturnDTO> key();
}
