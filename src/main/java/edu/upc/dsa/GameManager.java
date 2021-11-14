package edu.upc.dsa;


import edu.upc.dsa.models.User;

import java.util.List;


public interface GameManager {
    public User addUser(User user);
    public User addUser(String userName, String password, String fullName, String email);
    public User loginUser(String userName,String password);
    public void deleteUser(String userName);
    public void logoutUser(String userName);
    public User getUser(String userName);
    public List<User> getAll();
    public List<User> getAllActive();
    public Boolean existUser(String userName);
    //public User updateUser(User user);

}
