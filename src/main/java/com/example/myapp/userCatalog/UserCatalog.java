package com.example.myapp.userCatalog;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserCatalog {

    private Map<Integer, User> activeUsers;
    private Map<Integer, User> users;
    private Map<Integer, LocalDateTime> loginHistory;

    public UserCatalog() {
        users = new HashMap<>();
        activeUsers = new HashMap<>();
        loginHistory = new HashMap<>();
    }


    /*
    Login
    Returns the User object after a successful login, or throws an exception
    */
    public User login(String email, String password, int isAdmin) throws Exception {
        //Checks if the provided email password pair matches a user entry inside the users HashMap
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if(entry.getValue().getIsAdmin() == isAdmin) { //condition that verify if the user is an admin or client
                if (entry.getValue().getEmail().equals(email)) {
                    if (entry.getValue().getPassword().equals(password)) {
                        if (activeUsers.get(entry.getValue().getId()) != null)
                            throw new Exception("User already logged in");

                        //Successful login
                        //Adds the user to the activeUsers hashMap
                        addActiveUser(entry.getValue());
                        return activeUsers.get(entry.getKey());
                    } else {
                        throw new Exception("Wrong password");
                    }
                }
            }//Continues to the next map entry
        }
        //Do something if the email was not found in the map
        throw new Exception("Email not found");
    }

    public Map<Integer, User> getUsers(){
        return users;
    }

    public Map<Integer, User> getActiveUsers(){
        return activeUsers;
    }

    public void test(){}

    public User getUserById(int id) {
        return users.get(id);
    }

    public User getActiveUserById(int id) {
        return activeUsers.get(id);
    }

    public void addActiveUser(User user) {
        activeUsers.put(user.getId(),user);
        loginHistory.put(user.getId(), LocalDateTime.now());
    }

    public void removeActiveUserById(int id) {
        activeUsers.remove(id);
        loginHistory.remove(id);
    }

    public void removeUser(int id)
    {
        users.remove(id);
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public Map<Integer, LocalDateTime> getLoginHistory() {
        return loginHistory;
    }
}
