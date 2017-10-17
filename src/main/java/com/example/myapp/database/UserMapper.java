package com.example.myapp.database;

import com.example.myapp.userCatalog.User;
import com.example.myapp.userCatalog.UserCatalog;

public class UserMapper {

    private UserIdentityMap userIdentityMap;
    private UserTDG userTDG;
    private UserCatalog userCatalog;




    public UserMapper(UserCatalog userCatalog)
    {
        this.userTDG = new UserTDG();
        this.userCatalog = userCatalog;
        this.userIdentityMap = new UserIdentityMap();
    }



    public int insert(User user){
        int id = 0;
        try {
            id = userTDG.dbInsert(user);
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
                user = userTDG.dbGet(id);
                userIdentityMap.insertUserById(id, user);
            }
            catch (Exception e){

            }
        }

        return user;
    }

    public UserTDG getUserTDG() {
        return userTDG;
    }
}
