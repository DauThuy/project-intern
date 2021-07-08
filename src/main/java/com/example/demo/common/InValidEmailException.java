package com.example.demo.common;

public class InValidEmailException extends RuntimeException{
    public InValidEmailException() {
        super("Invalid email!");
    }

}
