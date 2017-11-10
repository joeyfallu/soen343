package com.example.myapp.database;

import com.example.myapp.productCatalog.Product;
import com.example.myapp.userCatalog.User;
import com.example.myapp.userCatalog.UserCatalog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    UserMapper userMapper;
    @Mock
    UserCatalog userCatalogMock;
    @Mock
    User userMock;
    @Mock
    UserIdentityMap userIdentityMapMock;
    @Mock
    Map<Integer, User> mapMock = new HashMap<>();
    @Mock
    Product productMock;

    @Before
    public void setUp()
    {
        mapMock.put(1, userMock);
        userMapper = new UserMapper(userCatalogMock);
    }

    @Test
    public void insert() throws Exception
    {
        userMapper.insert(userMock);
        Mockito.verify(mapMock).put(1, userMock);
    }

    @Test
    public void insertUserCatalog()
    {
        userCatalogMock.addUser(userMock);
    }

}