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
        productMapper.insertProductCatalog(0, productMock);
        Mockito.verify(productCatalogMock).addProduct(0, productMock);
    }

    @Test
    public void modifyProductCatalog() throws Exception {
        productMapper.modifyProductCatalog(0, productMock);
        Mockito.verify(productCatalogMock).modifyProduct(0, productMock);
    }

    @Test
    public void deleteByIdProductCatalog() throws Exception {
        productMapper.deleteByIdProductCatalog(0);
        Mockito.verify(productCatalogMock).deleteProduct(0);
    }

}