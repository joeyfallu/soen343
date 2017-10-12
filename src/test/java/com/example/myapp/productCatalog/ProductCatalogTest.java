package com.example.myapp.productCatalog;

import com.example.myapp.database.ProductMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class ProductCatalogTest {

    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductCatalog productCatalog;
    @Mock
    private Product product;
    private Map<Integer, Product> map = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        map.put(1,product);
        productCatalog = new ProductCatalog();
    }


}