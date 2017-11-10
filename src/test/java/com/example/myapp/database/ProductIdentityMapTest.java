package com.example.myapp.database;

import com.example.myapp.cartCatalog.Cart;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.database.ProductMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ProductIdentityMapTest {

    ProductIdentityMap productIdentityMap;

    @Mock
    Product productMock;
    @Mock
    ProductMapper productMapperMock;
    @Mock
    private Map<Integer, Product> mapMock = new HashMap<>();

    @Before
    public void setUp() throws Exception {

        mapMock.put(1, productMock);
        mapMock.remove(1);
        productIdentityMap = new ProductIdentityMap();
    }

    @Test
    public void insertProductById() throws Exception {

        productIdentityMap.insertProductById(1, productMock);
        Mockito.verify(mapMock).put(1, productMock);

    }

    @Test
    public void updateProductById() throws Exception {

        productIdentityMap.updateProductById(0, productMock);
        Mockito.verify(productMapperMock).update(productMock);

    }

    @Test
    public void deleteProductById() throws Exception {

        productIdentityMap.deleteProductById(1);
        Mockito.verify(mapMock).remove(1);

    }

}