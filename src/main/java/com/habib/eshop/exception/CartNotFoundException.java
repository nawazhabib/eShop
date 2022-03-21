package com.habib.eshop.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String msg){
        super(msg);
    }
}
