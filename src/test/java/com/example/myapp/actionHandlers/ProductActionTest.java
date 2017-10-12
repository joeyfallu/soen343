package com.example.myapp.actionHandlers;

import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class ProductActionTest {
    @Before
    public void setUp() throws Exception {
        ProductCatalog productCatalog = new ProductCatalog();
        ProductAction productAction = new ProductAction(productCatalog);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addNewProduct() throws Exception {
    }

    @Test
    public void deleteProduct() throws Exception {
    }

    @Test
    public void modifyProduct() throws Exception {
    }

}