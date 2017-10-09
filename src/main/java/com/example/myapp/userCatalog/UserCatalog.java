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

    public void registerUser(String[] values){
      //Creating new user object with a placeholder ID of (0)
       u1 = new User(0, values[0], values[1], values[2], values[3], values[4], values[5],Integer.parseint(values[6]));
       //determining the auto-generated ID of the new user
       int id = UserMapper.insert(u1);
       //inserting the new User/ID pair into the user HashMap
       user.put(id,u1);
    }

    public void login(String email, String password){

    }

    public void logOut(int id){

    }


}
