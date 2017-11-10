package com.example.myapp;

import com.example.myapp.cartCatalog.Cart;
import com.example.myapp.cartCatalog.CartCatalog;
import com.example.myapp.database.ProductMapper;
import com.example.myapp.database.UserMapper;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.purchases.Purchase;
import com.example.myapp.purchases.PurchaseHistory;
import com.example.myapp.purchases.PurchaseMapper;
import com.example.myapp.transactions.Transaction;
import com.example.myapp.userCatalog.UserCatalog;

import com.example.myapp.userCatalog.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PointOfSaleTest {

    PointOfSale pointOfSale;
    @Mock
    Store storeMock;
    @Mock
    PurchaseMapper purchaseMapperMock;
    @Mock
    CartCatalog cartCatalogMock;
    @Mock
    Product productMock;
    @Mock
    Cart cartMock;
    @Mock
    User userMock;
    @Mock
    UserMapper userMapperMock;
    @Mock
    ProductCatalog productCatalogMock;
    @Mock
    ProductMapper productMapperMock;
    @Mock
    private Map<Integer, Cart> CartMapMock = new HashMap<>();
    @Mock
    private Map<Integer, User> UserMapMock = new HashMap<>();
    @Mock
    private Map<Integer, Date> DateMapMock = new HashMap<>();
    @Mock
    private Map<Integer, Product> ProductMapMock = new HashMap<>();

    @Before
    public void setUp() throws Exception
    {


        cartCatalogMock = new CartCatalog(CartMapMock);
        cartMock = new Cart(1, 0, 0, DateMapMock);
        productCatalogMock = new ProductCatalog();
        productCatalogMock.addProduct(1, productMock);
        ProductMapMock.put(1, productMock);
        CartMapMock.put(1, cartMock);
        UserMapMock.put(1, userMock);
        storeMock = new Store(userMapperMock, productMapperMock);
        productMapperMock = new ProductMapper(productCatalogMock);
        pointOfSale = new PointOfSale(storeMock, cartCatalogMock, purchaseMapperMock);

    }

    @Test
    public void startPurchase() throws Exception
    {
        pointOfSale.startPurchase(1);
        //Note: the cart being added to the cart map is testing in the cart catalog class
    }

    @Test
    public void cancelPurchase() throws Exception
    {

        pointOfSale.cancelPurchase(1);

    }

    @Test
    public void addCartItem() throws Exception
    {

        pointOfSale.addCartItem(1,1);


    }

    @Test
    public void removeCartItem() throws Exception
    {

        pointOfSale.removeCartItem(1, 1);


    }

}