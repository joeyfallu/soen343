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

    @Mock
    private ProductMapper productMapperMock;
    @Mock
    private ProductCatalog productCatalogMock;
    @Mock
    private Product productMock;
    private Map<Integer, Product> map = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        map.put(1,productMock);
        productCatalogMock = new ProductCatalog();
    }

    @Test
    public void addProduct(){
        ProductCatalog.addProduct(0, productMock);
        Mockito.verify(productMapperMock).insert(productMock);
    }

    @Test
    public void deleteProduct(){
        ProductCatalog.deleteProduct(0);
        Mockito.verify(productMapperMock).delete(0);
    }

    @Test
    public void modifyProduct(){
        ProductCatalog.modifyProduct(0, productMock);
        Mockito.verify(productMapperMock).update(productMock);
    }


}