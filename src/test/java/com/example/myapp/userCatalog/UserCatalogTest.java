package com.example.myapp.userCatalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserCatalogTest {

    UserCatalog userCatalog;
    Map<Integer, User> activeUsersMock;
    Map<Integer, User> usersMock;
    Map<Integer, LocalDateTime> loginHistoryMock;

    @Mock
    User userMock1;
    @Mock
    User userMock2;
    @Mock
    User userMock3;

    @Before
    public void setUp() throws Exception {
        activeUsersMock = new HashMap<>();
        usersMock = new HashMap<>();
        loginHistoryMock = new HashMap<>();
        activeUsersMock.put(0,userMock1);
        usersMock.put(0, userMock1);
        usersMock.put(1, userMock2);
        userCatalog = new UserCatalog(usersMock ,activeUsersMock,loginHistoryMock);
    }

    @Test
    public void addActiveUser() throws Exception {
        int first = activeUsersMock.size();
        userCatalog.addActiveUser(userMock2);
        assertEquals(first ,activeUsersMock.size());
    }

    @Test
    public void removeActiveUserById() throws Exception {
        int first = activeUsersMock.size();
        userCatalog.removeActiveUserById(0);
        assertEquals(first - 1, activeUsersMock.size());
    }

    @Test
    public void removeUser() throws Exception {
        int first=usersMock.size();
        userCatalog.removeUser(0);
        assertEquals(first - 1, usersMock.size());
    }

    @Test
    public void addUser() throws Exception {
        int first=usersMock.size();
        userCatalog.addUser(userMock3);
        assertEquals(first, usersMock.size());
    }

}