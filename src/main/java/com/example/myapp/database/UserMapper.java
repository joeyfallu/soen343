package com.example.myapp.database;


import com.example.myapp.transactions.Transaction;
import com.example.myapp.userCatalog.User;
import com.example.myapp.userCatalog.UserCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class UserMapper {

    private UserIdentityMap userIdentityMap;
    private UserTDG userTDG;
    private UserCatalog userCatalog;
    private Transaction.Type commitType= Transaction.Type.add;
    private int mapCount=0;


    public UserMapper()
    {
        this.userTDG = new UserTDG();
        this.userCatalog = new UserCatalog();
        this.userIdentityMap = new UserIdentityMap();
        getUserCatalog().setUsers(this.getAll());
    }

    public UserMapper(UserCatalog userCatalog)
    {
        this.userTDG = new UserTDG();
        this.userCatalog = userCatalog;
        this.userIdentityMap = new UserIdentityMap();
    }



    public void insert(User user){

        commitType = Transaction.Type.add;
        userIdentityMap.insertUserById(mapCount, user);
        mapCount++;
    }

    public void delete(User user)
    {
        commitType = Transaction.Type.delete;
        userIdentityMap.insertUserById(mapCount,user);
        mapCount++;
    }
    public void insertUserCatalog(User user)
    {
        userCatalog.addUser(user);
    }

    public User get(int id){
        return userCatalog.getUserById(id);
    }

    //Gets all the users from DB, stores them in userCatalog then returns the userCatalog
    public Map<Integer, User> getAll(){
        User currentUsers[];
        try{
            currentUsers = userTDG.dbGetAll();

            for(int i=0;i<currentUsers.length;i++){
                userCatalog.addUser(currentUsers[i]);
            }
        } catch (Exception e){
            //TODO
        }
        return userCatalog.getUsers();
    }

    public void deleteUserCatalog(int id)
    {
        userCatalog.removeActiveUserById(id);
        userCatalog.removeUser(id);
    }

    public void commit()
    {
        mapCount=0;
    }

    public UserTDG getUserTDG() {
        return userTDG;
    }
    public UserCatalog getUserCatalog(){
        return userCatalog;
    }

    public int getMapCount() {
        return mapCount;
    }

    public Transaction.Type getCommitType()
    {
        return commitType;
    }

    public UserIdentityMap getUserIdentityMap() {
        return userIdentityMap;
    }
}
