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
        //Checks if the provided email password pair matches a user entry inside the users HashMap
        for (Map.Entry<Integer, User> entry : user.entrySet()) {
            if(entry.getValue().getEmail() == email){
                if(entry.getValue().getPassword() == password){
                    //Successful login

                } else {
                    //Wrong password

                }
            }
            //Continues to the next map entry
        }
        //Do something if the email was not found in the map
    }

    public void logOut(int id){

    }


}
