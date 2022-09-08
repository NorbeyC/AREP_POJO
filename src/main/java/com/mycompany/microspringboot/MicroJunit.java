
package com.mycompany.microspringboot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MicroJunit {
    
    public static void main(String... args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String className = args[0];
        Class c = Class.forName(className);
        Method[] declaredMethods = c.getDeclaredMethods();
        
        int passed = 0;
        int falled = 0;
        
        for(Method m : declaredMethods){
            if(m.isAnnotationPresent(Test.class)){
                try{
                    System.out.println("Invoking: " + m.getName() + "in class: " + c.getName());
                    m.invoke(null);
                    passed = passed +1;
                }catch(Exception e){
                    falled = falled +1;
                }
            }
        }
        System.out.println("Passed: " + passed + "Falled: " + falled);
    }
    
}
