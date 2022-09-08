package com.mycompany.microspringboot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MicroSpringBoot {

    public static void main(String... args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String className = args[0];
        Class c = Class.forName(className);
        Method[] declaredMethods = c.getDeclaredMethods();
        
        int numServices = 0;
        
        for(Method m : declaredMethods){
            if(m.isAnnotationPresent(RequestMapping.class)){
                String path = m.getAnnotation(RequestMapping.class).value();
                Data.services.put(path, m);
                numServices += 1;  
            }
        }
        System.out.println("services: " + numServices);
    }
}
