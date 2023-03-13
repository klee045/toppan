package com.example.libraryapi.common.pojos;

import java.util.List;

public class BorrowerArrayValue implements DynamicTypeValue {
    private List<String> borrowers;

    public BorrowerArrayValue(List<String> borrowers) {
        this.borrowers = borrowers;
    }

    public void addBorrower(String borrower) {
        this.borrowers.add(borrower);
    }

    public List<String> getBorrowers() {
        return this.borrowers;
    }
}
