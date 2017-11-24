package com.example.myapp.cartCatalog;

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
public class CartCatalogTest {

    CartCatalog cartCatalog;

    Map<Integer, Cart> cartsMock;

    @Mock
    Cart cartMock1;
    @Mock
    Cart cartMock2;



    @Before
    public void setUp() throws Exception {
        cartsMock = new HashMap<>();
        cartCatalog = new CartCatalog(cartsMock);
        cartsMock.put(0, cartMock1);
        cartsMock.put(1, cartMock2);
    }

    @Test
    public void emptyCart() throws Exception {
        cartCatalog.emptyCart(0);
        assertEquals(0, cartsMock.get(0).getSize());
    }

    @Test
    public void addToCart() throws Exception {
        int first = cartsMock.size();
        cartCatalog.addCart(0);
        assertEquals(first, cartsMock.size());
    }

    @Test
    public void removeFromCart() throws Exception {
        int first = cartCatalog.getCart(1).getSize();
        cartCatalog.removeFromCart(1, "s");
        assertEquals(cartCatalog.getCart(1).getSize(), first);
    }

}