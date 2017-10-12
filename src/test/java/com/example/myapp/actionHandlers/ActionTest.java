package com.example.myapp.actionHandlers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class ActionTest {
    @Before
    public void setUp() throws Exception {
    }

    @Mock
    boolean isComplete;

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isComplete() throws Exception {
    }

    @Test
    public void becomeComplete() throws Exception {
        boolean isComplete = true;
        assertEquals(isComplete, true);
    }

}