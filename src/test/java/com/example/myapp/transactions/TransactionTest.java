package com.example.myapp.transactions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {

    Transaction transaction;

    @Before
    public void setUp() throws Exception {
        transaction = new Transaction(Transaction.Type.add);
    }

    @Test
    public void setComplete() throws Exception {
        transaction.setComplete(true);
        assertEquals(true, transaction.isComplete());
        assertEquals(transaction.isComplete(), true);
    }


}