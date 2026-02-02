package com.example.bai10;

import java.util.*;
import java.util.stream.Collectors;

public class AccountManager {

    private Scanner sc = new Scanner(System.in);
    private Map<String,Account> accounts = new HashMap<>();
    private Set<String> citizenIds = new HashSet<>();


    public void registerAccount() {
        System.out.println("=== MO TAI KHOAN MOI ===");

        String citizenId;
        while (true) {
            try {
                System.out.print("Nhap Citizen ID: ");
                citizenId = sc.nextLine();
                if (citizenIds.contains(citizenId)) {
                    throw new DuplicateCustomerException(
                            "Loi: Khach hang co ID " + citizenId + " da ton tai!"
                    );
                }
                citizenIds.add(citizenId);
                break;
            } catch (DuplicateCustomerException e) {
                System.out.println(e.getMessage());
            }
        }

        Account account = createAccountByType();
        account.input();
        accounts.put(account.getNumberAccount(), account);

        System.out.println("Mo tai khoan thanh cong! So TK: " + account.getNumberAccount());
    }

    private Account createAccountByType() {
        System.out.println("Chon loai tai khoan:");
        System.out.println("1. Savings Account");
        System.out.println("2. Credit Account");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) return new SavingsAccount();
        return new CreditAccount();
    }


    public void depositWithdraw() {
        try {
            Account account = findAccount();

            System.out.println("1. Nap tien");
            System.out.println("2. Rut tien");
            int choice = Integer.parseInt(sc.nextLine());

            System.out.print("Nhap so tien: ");
            double amount = Double.parseDouble(sc.nextLine());

            if (choice == 1) {
                account.deposit(amount);
                System.out.println("Nap tien thanh cong!");
            } else {
                account.withdraw(amount);
                System.out.println("Rut tien thanh cong!");
            }

            System.out.println("So du hien tai: " + account.getBalance());

        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transferMoney() {
        try {
            System.out.print("Nhap TK nguon: ");
            Account from = findAccount(sc.nextLine());

            System.out.print("Nhap TK dich: ");
            Account to = findAccount(sc.nextLine());

            System.out.print("Nhap so tien chuyen: ");
            double amount = Double.parseDouble(sc.nextLine());

            from.transfer(to, amount);

            System.out.println("Chuyen khoan thanh cong!");

        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void statistics() {
        System.out.println("--------DANH SACH TAI KHOAN DANG NO TIN DUNG-------");
        accounts.values().stream()
                .filter(a->a instanceof CreditAccount && a.getBalance() <0)
                .forEach(a->{
                    System.out.println("[CRE]"+a.getOwnerName()
                    +"-So du : "+a.getBalance());
                });

        System.out.println("------------DANH SACH TAI KHOAN VIP (> 5000)------");

        List<Account> vipList = accounts.values().stream()
                .filter(a->a.getBalance() > 5000)
                .collect(Collectors.toList());

        int index = 1;
        for(Account acc : vipList){
            String type = (acc instanceof SavingsAccount) ? "SAV" : "CRE";

            System.out.println(index++ +".["+type+"]"
            +acc.getOwnerName().toUpperCase()
            +"-So du : "+acc.getBalance());
        }

        double total = accounts.values().stream()
                .mapToDouble(Account::getBalance)
                .sum();

        System.out.println("\nTong von ngan hang dang quan ly : "+total);
    }

    public void endOfMonth() {
        System.out.println("Dang quet danh sach tai khoan...");

        InterestRate savingInterest = balance -> balance * 0.05;
        InterestRate creditFee = balance -> Math.abs(balance) * 0.01;

        for (Account acc : accounts.values()) {
            if (acc instanceof SavingsAccount) {
                double interest = savingInterest.apply(acc.getBalance());
                acc.setBalance(acc.getBalance() + interest);
                System.out.println("- TK " + acc.getNumberAccount()
                        + ": +" + interest + " (Lai 5%)");
            } else if (acc instanceof CreditAccount) {
                double fee = creditFee.apply(acc.getBalance());
                acc.setBalance(acc.getBalance() - fee);
                System.out.println("- TK " + acc.getNumberAccount()
                        + ": -" + fee + " (Phi 1%)");
            }
        }

        System.out.println("Hoan tat quyet toan cuoi thang!");
    }

    private Account findAccount() {
        System.out.print("Nhap so tai khoan: ");
        String accNo = sc.nextLine();
        return findAccount(accNo);
    }

    private Account findAccount(String accNo) {
        if (!accounts.containsKey(accNo)) {
            throw new AccountNotFoundException(
                    "Loi: Khong tim thay tai khoan " + accNo
            );
        }
        return accounts.get(accNo);
    }

}
