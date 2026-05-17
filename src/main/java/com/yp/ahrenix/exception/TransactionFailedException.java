package com.yp.ahrenix.exception;

public class TransactionFailedException extends RuntimeException {

    public TransactionFailedException(String message) {
        super(message);
    }

}