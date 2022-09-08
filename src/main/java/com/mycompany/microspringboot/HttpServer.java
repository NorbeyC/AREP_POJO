
package com.mycompany.microspringboot;

import java.net.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServer {
    public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        MicroSpringBoot.main(args);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        while (true) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            String path = "";
            int nLine = 0;
            while ((inputLine = in.readLine()) != null) {
                if(nLine==0){
                    nLine = 1;
                    path = inputLine.split(" ")[1];
                }
                System.out.println("Received: " + inputLine);
                
                if (!in.ready()) {
                    break;
                }
            }

            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    +getServiceOutput(path);

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();

        }
        //serverSocket.close();
    }
    
    public static String getServiceOutput(String path) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        System.out.println(path);
        String output = "";
        if(Data.services.containsKey(path)){
            Method met = Data.services.get(path);
            output = (String) met.invoke(null);
        }
        return output;
    }
    
    
     
}
