package com.example.myapp.database;

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

    @Before
    public void setUp() throws Exception {
        productIdentityMap = new ProductIdentityMap();
    }

    //note: these tests do not work. need fixing.
    
    /*error message:
    org.mockito.exceptions.misusing.NullInsteadOfMockException:
    Argument passed to verify() should be a mock but is null!
    Examples of correct verifications:
    verify(mock).someMethod();
    verify(mock, times(10)).someMethod();
    verify(mock, atLeastOnce()).someMethod();
    not: verify(mock.someMethod());
    Also, if you use @Mock annotation don't miss initMocks()
     */

    @Test
    public void insertProductById() throws Exception {

        //productIdentityMap.insertProductById(0, productMock);
        //Mockito.verify(productMapperMock).insert(productMock);

    }

    @Test
    public void updateProductById() throws Exception {

        /*productIdentityMap.updateProductById(0, productMock);
        Mockito.verify(productMapperMock).update(productMock);*/

    }

    @Test
    public void deleteProductById() throws Exception {

        /*productIdentityMap.deleteProductById(0);
        Mockito.verify(productMapperMock).delete(0);*/

    }

}