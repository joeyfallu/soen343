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
    ProductMapper productMapperMock;
    @Mock
    Product productMock;

    @Before
    public void setUp() throws Exception {
        productCatalog = new ProductCatalog();
    }

    @Test
    public void addProduct(){
        productCatalog.addProduct(0, productMock);
        Mockito.verify(productMapperMock).insert(productMock);
    }

    @Test
    public void deleteProduct(){
        productCatalog.deleteProduct(0);
        Mockito.verify(productMapperMock).delete(0);
    }

    @Test
    public void modifyProduct(){
        productCatalog.modifyProduct(0, productMock);
        Mockito.verify(productMapperMock).update(productMock);
    }


}