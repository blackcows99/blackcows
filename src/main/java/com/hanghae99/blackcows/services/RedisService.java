package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.annotations.DeleteCache;
import com.hanghae99.blackcows.annotations.UseCache;
import com.hanghae99.blackcows.interfaces.ReturnDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class RedisService {
    private final RedisTemplate<Class, HashMap<Long,Object>> template;

    @AfterReturning("@annotation(com.hanghae99.blackcows.annotations.DeleteCache)")
    public void deleteCache(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DeleteCache annotation = methodSignature.getMethod().getAnnotation(DeleteCache.class);
        template.delete(Arrays.asList(annotation.key()));
    }

    @Around(value = "@annotation(com.hanghae99.blackcows.annotations.UseCache)")
    public Object useCache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        UseCache annotation = methodSignature.getMethod().getAnnotation(UseCache.class);
        long id = getScope();
        if(template.hasKey(annotation.key())){
            HashMap<Long,Object> map = template.opsForValue().get(annotation.key());
            if(id == 0L){
                log.info("Cache 조회");
                return new ArrayList<>(map.values());
            }else{
                if(map.containsKey(id)){
                    return map.get(id);
                }else{
                    Object object = joinPoint.proceed();
                    saveCache(annotation,object);
                    return object;
                }
            }
        }else{
            Object object = joinPoint.proceed();
            saveCache(annotation,object);
            return object;
        }
    }

    public void saveCache(UseCache annotation,Object returnValue){

        if(!template.hasKey(annotation.key())){
            HashMap<Long,Object> map = new HashMap<>();
            if(returnValue instanceof ArrayList){
                for(ReturnDTO r : (ArrayList<ReturnDTO>) returnValue){
                    map.put(r.getId(),r);
                }
            }else{
                map.put(((ReturnDTO) returnValue).getId(),returnValue);
            }
            template.opsForValue().set(annotation.key(),map);
        }
    }
    public long getScope(){
        try {
            HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String uri = request.getRequestURI();
            return Long.parseLong(uri.substring(uri.lastIndexOf("/") + 1));
        }catch (NumberFormatException e){
            return 0L;
        }
    }

    public <T> T getValue(Class<? extends ReturnDTO> key,Long id){
        if(template.hasKey(key)){
            return (T) template.opsForValue().get(key).getOrDefault(id,null);
        }
        return null;
    }
    public ArrayList getValue(Class<? extends ReturnDTO> key){
        if(template.hasKey(key)){
            return new ArrayList<>(template.opsForValue().get(key).values());
        }
        return null;
    }
}
