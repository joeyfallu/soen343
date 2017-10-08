package com.example.myapp.actionHandlers;

public class Action {

    private boolean isComplete;

    Action(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void becomeComplete() {
        isComplete = true;
    }
}




