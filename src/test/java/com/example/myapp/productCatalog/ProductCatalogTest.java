package com.example.myapp.productCatalog;

import com.example.myapp.database.ProductMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class ProductCatalogTest {


    ProductCatalog productCatalog;
    @Mock
    private Map<Integer, Product> mapMock = new HashMap<>();
    @Mock
    ProductMapper productMapperMock;
    @Mock
    Product productMock;

    @Before
    public void setUp() throws Exception {
        mapMock.put(1, productMock);
        mapMock.remove(1);
        productCatalog = new ProductCatalog();
    }

    @Test
    public void addProduct(){
        productCatalog.addProduct("a1", productMock);
        Mockito.verify(mapMock).put(1, productMock);
    }

    @Test
    public void deleteProduct(){
        productCatalog.deleteProduct("a1");
        Mockito.verify(mapMock).remove(1);
    }

    @Test
    public void modifyProduct(){
        productCatalog.modifyProduct("a1", productMock);
        Mockito.verify(mapMock).remove(1);
        Mockito.verify(mapMock).put(1, productMock);
    }


}