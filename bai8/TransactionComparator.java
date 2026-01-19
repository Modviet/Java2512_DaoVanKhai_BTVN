package com.example.bai8;
@FunctionalInterface
public interface TransactionComparator {
    int compare(Transaction t1,Transaction t2);
}
