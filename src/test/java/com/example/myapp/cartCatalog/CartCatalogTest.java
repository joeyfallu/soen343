package com.example.myapp.cartCatalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CartCatalogTest {

    CartCatalog cartCatalog;

    @Mock
    Map<Integer, Cart> cartsMock;


    @Before
    public void setUp() throws Exception {
        cartCatalog = new CartCatalog(cartsMock);
    }


    @Test
    public void emptyCart() throws Exception {

    }

    @Test
    public void addToCart() throws Exception {
    }

    @Test
    public void removeFromCart() throws Exception {
    }

    @Test
    public void getCarts() throws Exception {
    }

    @Test
    public void purchaseCart() throws Exception {
     //   assertEquals(cartCatalog.purchaseCart(0), Mockito.verify(cartsMock).get(0).getCartProducts());
    }

    @Test
    public void getCart() throws Exception {
    }

}