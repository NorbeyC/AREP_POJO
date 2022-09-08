
package com.mycompany.microspringboot;


public class WebServices {
    
    @RequestMapping("/hello")
    public static String helloworld(){
        return "Hello world";
    }
   
    
    @RequestMapping("/status")
    public static String serverStatus(){
        return "Running";
    }
    
    @RequestMapping("/index.html")
    public static String serverIndex(){
        return "Está funcionando";
    }
}
