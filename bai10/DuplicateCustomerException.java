package com.example.bai10;

public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException(String message) {
        super(message);
    }

    public DuplicateCustomerException(){
        super("Khach hang da ton tai");
    }
}
