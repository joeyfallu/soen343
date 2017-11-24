package com.example.myapp;

import com.example.myapp.cartCatalog.CartCatalog;
import com.example.myapp.purchases.PurchaseMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PointOfSaleTest {

    PointOfSale pointOfSale;
    @Mock
    Store storeMock;
    @Mock
    PurchaseMapper purchaseMapperMock;
    @Mock
    CartCatalog cartCatalogMock;


    @Before
    public void setUp() throws Exception
    {
        pointOfSale = new PointOfSale(storeMock, cartCatalogMock, purchaseMapperMock);
    }

    @Test
    public void startPurchase(){
        pointOfSale.startPurchase(0);
        Mockito.verify(cartCatalogMock).addCart(0);
    }
    @Test
    public void cancelPurchase(){
        pointOfSale.cancelPurchase(0);
        Mockito.verify(cartCatalogMock).emptyCart(0);
    }
    @Test
    public void addCartItem(){
        pointOfSale.addCartItem(0, "s");
        Mockito.verify(cartCatalogMock).addToCart(0, "s");
    }
    @Test
    public void removeCartItem(){
        pointOfSale.removeCartItem(0, "s");
        Mockito.verify(cartCatalogMock).removeFromCart(0, "s");
    }
    @Test
    public void processReturn(){
        pointOfSale.processReturn( "s");
        Mockito.verify(purchaseMapperMock).returnItem( "s");
    }
    @Test
    public void viewCart(){
        pointOfSale.viewCart( 0);
        Mockito.verify(cartCatalogMock).getCart( 0);
    }

}