package com.example.bai10;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(){
        super("Khong tim thay tai khoan");
    }
}
