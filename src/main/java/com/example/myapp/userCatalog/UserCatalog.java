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

    public UserCatalog(Map<Integer, User> users, Map<Integer, User> activeUsers,  Map<Integer, LocalDateTime> loginHistory){
        this.activeUsers = activeUsers;
        this.users =users;
        this.loginHistory = loginHistory;
    }

    public User login(String email, String password) throws Exception {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if(entry.getValue().getEmail().toLowerCase().equals(email.toLowerCase())) {
                if (entry.getValue().getPassword().equals(password)) {
                    if (activeUsers.get(entry.getValue().getId()) != null)
                        throw new Exception("User already logged in");

                    addActiveUser(entry.getValue());
                    return activeUsers.get(entry.getKey());
                } else {
                    throw new Exception("Wrong password");
                }
            }
        }
        throw new Exception("Email not found");
    }

    public Map<Integer, User> getUsers(){
        return users;
    }

    public User getUserById(int id) {
        return users.get(id);
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
}
