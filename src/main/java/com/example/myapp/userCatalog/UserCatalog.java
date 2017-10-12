package com.example.myapp.userCatalog;
import com.example.myapp.userCatalog.User;

import java.util.Map;
public class UserCatalog {

    private Map<User, Integer > activeUser;
    private Map<User, Integer> user;
    private UserMapper userMapper;

    //Default constructor
    public UserCatalog(){
      this.userMapper = new UserMapper(this);
      this.user = new HashMap<>();
      this.activeUser = new HashMap<>();
    }

    //supposed to return User
    public  void  getUsers(){

    }

    public void registerUser(User user){
       //determining the auto-generated ID of the new user
       int id = UserMapper.insert(user);
       //inserting the new User/ID pair into the user HashMap
       user.put(id,user);
    }

    public void login(String email, String password){

    }

    public void logOut(int id){

    }


}
