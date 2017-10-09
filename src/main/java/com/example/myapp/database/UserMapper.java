package com.example.myapp.database;

import com.example.myapp.database.UserIdentityMap;
import com.example.myapp.userCatalog.User;
import com.example.myapp.userCatalog.UserCatalog;

public class UserMapper {

    private UserIdentityMap userIdentityMap;
    private UserTdg userTdg;
    private UserCatalog userCatalog;




    public UserMapper(UserCatalog userCatalog)
    {
        this.userTdg= new UserTdg();
        this.userCatalog = userCatalog;
        this.userIdentityMap = new UserIdentityMap();
    }



    public int insert(User user){
        int id = 0;
        try {
            id = userTdg.dbInsert(user);
        }
        catch(Exception e){
            //do nothing
        }
        userIdentityMap.insertUserById(id, user);
        return id;
    }

    public User get(int id){
        User user = userIdentityMap.getUserById(id);
        if(user == null){
            try {
                user = userTdg.dbGet(id);
                userIdentityMap.insertUserById(id, user);
            }
            catch (Exception e){

            }
        }

        return user;
    }

}
