package com.example.myapp.purchases;

import com.example.myapp.database.PurchaseTDG;
import com.example.myapp.purchases.Purchase;
import com.example.myapp.database.PurchaseIdentityMap;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.transactions.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseMapperTest
    {
        PurchaseMapper purchaseMapper;

    @Mock
    Purchase purchaseMock;
    @Mock
    PurchaseTDG purchaseTDGMock;
    @Mock
    PurchaseIdentityMap purchaseIdentityMapMock;
    @Mock
    Product productMock;
    @Mock
    Transaction transactionMock;
    @Mock
    PurchaseHistory purchaseHistoryMock;
    @Mock
    private Map<Integer, Purchase> mapMock = new HashMap<>();

    @Before
    public void setUp() throws Exception
    {
        mapMock.put(1, purchaseMock);
        purchaseIdentityMapMock = new PurchaseIdentityMap();
        purchaseMapper = new PurchaseMapper(purchaseTDGMock, purchaseHistoryMock, transactionMock.getType());
    }


    @Test
    public void insertPurchaseHistory() throws Exception
    {

        purchaseMapper.insertPurchaseHistory("a1", purchaseMock);
        Mockito.verify(purchaseHistoryMock).addPurchase("a1", purchaseMock);

    }

    @Test
    public void deletePurchaseHistory() throws Exception
    {

        purchaseMapper.deletePurchaseHistory("a1");
        Mockito.verify(purchaseHistoryMock).deletePurchase("a1");

    }


}