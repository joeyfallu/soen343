package com.example.myapp.userCatalog;
import com.example.myapp.userCatalog.User;
import com.example.myapp.database.UserMapper;

import java.util.HashMap;
import java.util.Map;
public class UserCatalog {

    private Map<User, Integer > activeUser;
    private Map<User, Integer> user;


    //Default constructor
    public UserCatalog(){
        user = new HashMap<User, Integer>();
    }

    //supposed to return User
    public  void  getUsers(){

    }

    public void registerUser(User user1,int id){
       //inserting the new User/ID pair into the user HashMap
       user.put(user1,id);
    }

    public void login(String email, String password){

    }

    public void logOut(int id){

    }


}
