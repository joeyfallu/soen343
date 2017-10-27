package com.example.myapp.actionHandlers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionTest {


    @Test
    public void isComplete() throws Exception {
    }

    @Test
    public void becomeComplete() throws Exception {
        boolean isComplete = true;
        assertEquals(isComplete, true);
    }

}