package com.example.myapp.database;

import com.example.myapp.userCatalog.User;
import com.example.myapp.userCatalog.UserCatalog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    UserMapper userMapper;
    @Mock
    UserCatalog userCatalogMock;
    @Mock
    User userMock;

    @Before
    public void setUp(){
        userMapper = new UserMapper(userCatalogMock);
    }

    @Test
    public void insert() throws Exception {
        userMapper.insert(userMock);
        Mockito.verify(userMapper).insert(userMock);
    }

    @Test
    public void insertUserCatalog() {
        userCatalogMock.addUser(userMock);
    }

}