package com.example.myapp.database;

import com.example.myapp.database.UserIdentityMap;
import com.example.myapp.userCatalog.User;

public class UserMapper {

    private UserIdentityMap userIdentityMap = new UserIdentityMap();

    public void insert(User user){

    }

    public User get(int id){
        User user = userIdentityMap.getUserById(id);
        if(user == null){
            //TDG shtuff
            return null;
        }
        else
            return user;
    }

}
