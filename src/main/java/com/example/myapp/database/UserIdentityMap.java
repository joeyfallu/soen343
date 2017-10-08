package com.example.myapp.database;


import com.example.myapp.userCatalog.User;

import java.util.HashMap;
import java.util.Map;

public class UserIdentityMap {

    private Map<Integer, User> users = new HashMap<>();

    public User getUserById(int id){
        return users.getOrDefault(id, null);
    }

    public void insertUserById(int id, User user){
        users.put(id,user);
    }
}
