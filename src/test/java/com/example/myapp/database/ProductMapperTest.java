package com.example.myapp.database;

import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {

    ProductMapper productMapper;
    @Mock
    ProductCatalog productCatalogMock;
    @Mock
    Product productMock;


    @Before
    public void setUp(){
        productMapper = new ProductMapper(productCatalogMock);
    }

    @Test
    public void insertProductCatalog() throws Exception {
        productMapper.insertProductCatalog("a1", productMock);
        Mockito.verify(productCatalogMock).addProduct("a1", productMock);
    }

    @Test
    public void modifyProductCatalog() throws Exception {
        productMapper.modifyProductCatalog("a1", productMock);
        Mockito.verify(productCatalogMock).modifyProduct("a1", productMock);
    }

    @Test
    public void deleteByIdProductCatalog() throws Exception {
        productMapper.deleteProductCatalog("a1");
        Mockito.verify(productCatalogMock).deleteProduct("a1");
    }

}