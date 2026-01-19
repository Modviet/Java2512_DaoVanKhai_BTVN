package com.example.bai8;

import com.example.bai7.Utils;

import java.util.Arrays;
import java.util.function.Predicate;

public class TransactionManager {
   private Transaction[] transactions;
   private int count;
   private static final int MAX_TRANSACTIONS = 100;

    public TransactionManager() {
        this.transactions = new Transaction[MAX_TRANSACTIONS];
        this.count = 0;
    }

    public void addTransaction(Transaction transaction){
        if(transactions == null){
            transactions = new Transaction[MAX_TRANSACTIONS];
            count = 0;
        }

        if(count < MAX_TRANSACTIONS){
            transactions[count++] = transaction;
        } else {
            System.out.println("Da toi toi da muc giao dich hien tai.");
        }
    }

    public void filterByAmount(){
        System.out.println("Nhap so tien de loc giao dich");
        int amount = Utils.scanNumber();
        Transaction[] filterTransactions = filter(t-> t.getAmount()>amount);
        System.out.println("Danh sach giao dich lon hon : "+amount);
        for(Transaction t : filterTransactions){
            System.out.println(t);
        }
    }

    public Transaction[] filter(Predicate<Transaction> condition){
        Transaction[] result = new Transaction[count];
        int index = 0;
        for(int i=0;i<count;i++){
            if(condition.test(transactions[i])){
                result[index++] = transactions[i];
            }
        }
        Transaction[] filteredTransactions = new Transaction[index];
        for(int i = 0;i<index;i++){
            filteredTransactions[i] = result[i];
        }
        return filteredTransactions;
    }

    public void calculateTax(){
        Taxable taxable = (amount) -> amount*0.1;
        double[] taxes = calculateWithdrawTaxes(taxable);
        System.out.println("Thue cho cac giao dich rut tien : ");
        for(double tax : taxes){
            System.out.println(tax);
        }
    }

    public double[] calculateWithdrawTaxes(Taxable taxable) {
        int withdrawCount = 0;
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof WithdrawTransaction) {
                withdrawCount++;
            }
        }
        double[] taxes = new double[withdrawCount];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof WithdrawTransaction) {
                taxes[index++] = taxable.calculateTax(((Transaction) transactions[i]).getAmount());
            }
        }
        return taxes;
    }

    public void sort() {
        TransactionComparator compare = (t1,t2)->Double.compare(t1.getAmount(), t2.getAmount());
        sortMath(compare);
        showAll();
    }

    public void sortMath(TransactionComparator compare){
        for(int i=0;i<count-1;i++){
            for(int j =i+1;j<count;j++){
                Transaction temp = transactions[i];
                transactions[i] = transactions[j];
                transactions[j] = temp;
            }
        }
    }

    public void showAll(){
        System.out.println("Danh sach giao dich : ");
        for(int i=0;i<count;i++){
            System.out.println(transactions[i]);
        }
    }
}
