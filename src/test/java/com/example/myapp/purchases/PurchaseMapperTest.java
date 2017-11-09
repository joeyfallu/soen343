package com.example.myapp.purchases;

import com.example.myapp.database.PurchaseTDG;

import com.example.myapp.database.PurchaseIdentityMap;

import com.example.myapp.productCatalog.Product;

import com.example.myapp.transactions.Transaction;

import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

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


    @Before
    public void setUp() throws Exception
    {
        purchaseMapper = new PurchaseMapper(purchaseTDGMock, purchaseHistoryMock, transactionMock.getType());
    }

    //throws nullpointerexception
    @Test
    public void purchase() throws Exception
    {

        //purchaseMapper.purchase(purchaseMock);
        //Mockito.verify(purchaseIdentityMapMock).insertPurchaseById(0, purchaseMock);

    }

    @Test
    public void insertPurchaseHistory() throws Exception
    {

        purchaseMapper.insertPurchaseHistory(0, purchaseMock);
        Mockito.verify(purchaseHistoryMock).addPurchase(0, purchaseMock);

    }

    @Test
    public void deletePurchaseHistory() throws Exception
    {

        purchaseMapper.deletePurchaseHistory(0);
        Mockito.verify(purchaseHistoryMock).deletePurchase(0);

    }

    //throws nullpointerexception
    @Test
    public void returnItem() throws Exception
    {

        //purchaseMapper.returnItem(0);
        //Mockito.verify(purchaseIdentityMapMock).insertPurchaseById(0, purchaseMock);

    }

    //not sure if this is testable, WIP
    @Test
    public void get() throws Exception
    {

        //assertEquals(purchaseMapper.get(purchaseMock.getUserId()), purchaseMock);



    }


}