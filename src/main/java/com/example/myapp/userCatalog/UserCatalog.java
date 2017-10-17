package com.example.myapp.userCatalog;
import com.example.myapp.userCatalog.User;
import com.example.myapp.database.UserMapper;

import java.util.HashMap;
import java.util.Map;
public class UserCatalog {

    private Map<Integer, User> activeUser;
    private Map<Integer, User> user;


    //Default constructor
    public UserCatalog(){
        user = new HashMap<Integer, User>();
    }

    //supposed to return User
    public  User  getUserById(int id){
        return user.get(id);
    }

    public void registerUser(User user1,int id){
       //inserting the new User/ID pair into the user HashMap
       user.put(id,user1);
    }

    public void login(String email, String password){

    }

    public void logOut(int id){

    }


}
