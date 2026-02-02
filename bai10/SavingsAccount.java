package com.example.bai10;

public class SavingsAccount extends Account{

    @Override
    public void deposit(double amount) {
        super.setBalance(super.getBalance()+ amount);
    }

    @Override
    public void withdraw(double amount) {
        if(amount > super.getBalance()){
            System.out.println("So du khong du de rut !");
            return;
        }
        super.setBalance(super.getBalance() - amount);
    }

    @Override
    public void transfer(Account toAccount, double amount) {
          if(amount > super.getBalance()){
              System.out.println("So du khong du de chuyen tien.");
              return;
          }
          this.withdraw(amount);
          toAccount.deposit(amount);
        System.out.println("Chuyen tien thanh cong.");
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }
}
