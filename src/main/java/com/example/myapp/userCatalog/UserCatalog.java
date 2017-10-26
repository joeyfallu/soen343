package com.example.myapp.userCatalog;
import com.example.myapp.userCatalog.User;
import com.example.myapp.database.UserMapper;

import java.util.HashMap;
import java.util.Map;
public class UserCatalog {

    private Map<Integer, User> activeUsers;
    private Map<Integer, User> users;


    //Default constructor
    public UserCatalog(){
        users = new HashMap<Integer, User>();
        activeUsers = new HashMap<Integer, User>();
    }

    //supposed to return User
    public  User  getUserById(int id){
        return users.get(id);
    }

    public void registerUser(User user1,int id){
       //inserting the new User/ID pair into the user HashMap
       users.put(id,user1);
    }

    /*
    Login
    Returns the User object after a successful login, or throws an exception
    */
    public User login(String email, String password) throws Exception {
        //Checks if the provided email password pair matches a user entry inside the users HashMap
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if(entry.getValue().getEmail().equals(email)) {
                if (entry.getValue().getPassword().equals(password)) {
                    //Successful login
                    //Adds the user to the activeUsers hashMap
                    addActiveUser(entry.getValue());
                    return activeUsers.get(entry.getKey());
                } else {
                    //Wrong password
                    throw new Exception("Wrong password");
                }
            }
            //Continues to the next map entry
        }
        //Do something if the email was not found in the map
        throw new Exception("Email not found");
    }

    public void logOut(int id){

    }
    public Map<Integer, User> getUsers(){return users;}
    public Map<Integer, User> getActiveUsers(){return activeUsers;}

    public User getActiveUserById(int id){
        return activeUsers.get(id);
    }
    public void addActiveUser(User user){
        activeUsers.put(user.getId(),user);
    }
    public void removeActiveUserById(int id){
        activeUsers.remove(id);
    }
    public void setUsers(Map<Integer, User> users){
        this.users = users;
    }
    public void addUser(User user){
        users.put(user.getId(), user);
    }


}
