package com.example.myapp.transactions;

public class Transaction {

    private boolean isComplete;
    private int userId;
    private Type type;
    public enum Type{add,modify,delete}

    public Transaction(Type t)
    {
      type = t;
    }

    public Type getType(){return type;}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId(){return userId;}

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isComplete(){return isComplete;}
}
