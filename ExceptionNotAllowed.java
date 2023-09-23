package org.example;

import java.io.PrintWriter;

public class ExceptionNotAllowed extends Exception{
    public ExceptionNotAllowed(String message){
        System.out.println(message);;
    }
}
