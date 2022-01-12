package edu.upc.dsa;


import edu.upc.dsa.models.Plane;
import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.User;

import java.util.List;


public interface GameManager {
    User addUser(User user);
    User updateUser(User user, String oldUsername);
    User loginUser(String userName,String password);
    void deleteUser(String userName);
    void logoutUser(String userName);
    User getUser(String userName);
    List<User> getAll();
    List<User> getAllActive();
    Boolean existUser(String userName);

    List<User> sortByDistance();

    List<User> sortByTime();

    List<User> sortByMoney();

    List<User> sortByRol();

    //public User updateUser(User user);



}
