package com.example.myapp.transactions;

public class Transaction {

    private boolean isComplete;
    private boolean userId;

    public void setUserId(boolean userId) {
        this.userId = userId;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
