package com.example.myapp.database;

import com.example.myapp.transactions.UnitOfWork;
import com.example.myapp.userCatalog.User;
import com.example.myapp.userCatalog.UserCatalog;

public class UserMapper {

    private UserIdentityMap userIdentityMap;
    private UserTDG userTDG;
    private UserCatalog userCatalog;
    private UnitOfWork u;
    private int mapCount=0;



    public UserMapper(UserCatalog userCatalog)
    {
        this.userTDG = new UserTDG();
        this.userCatalog = userCatalog;
        this.userIdentityMap = new UserIdentityMap();
    }



    public void insert(User user){

        userIdentityMap.insertUserById(mapCount, user);
        mapCount++;
    }

    public User get(int id){
        return userCatalog.getUserById(id);
    }

    public void commit()
    {
        u = new UnitOfWork(this);
        for(int i=0; i<mapCount; i++)
        {
            User us = userIdentityMap.getUserById(i);
            u.registerAdd(us);
        }
        u.commitUsers();
    }

    public UserTDG getUserTDG() {
        return userTDG;
    }
}
