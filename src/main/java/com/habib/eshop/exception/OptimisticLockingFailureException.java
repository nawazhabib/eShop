package com.habib.eshop.exception;

public class OptimisticLockingFailureException extends RuntimeException{
    public OptimisticLockingFailureException(String msg){
        super(msg);
    }
}
