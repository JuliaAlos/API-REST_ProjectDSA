package edu.upc.dsa;


import edu.upc.dsa.models.Plane;
import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.User;

import java.util.List;


public interface GameManager {
    User addUser(User user);
    User addUser(String userName, String password, String fullName, String email);
    User loginUser(String userName,String password);
    void deleteUser(String userName);
    void logoutUser(String userName);
    User getUser(String userName);
    List<User> getAll();
    List<User> getAllActive();
    Boolean existUser(String userName);
    //public User updateUser(User user);

    List<Plane> getAllPlanes ();
    List <Insignia> getAllInsignias();
    void addPlane (Plane plane); //Adds a plane to the system.
    void addInsignia (Insignia insignia); //Adds an insignia to the system.
    Plane getPlaneByModel (String model);
    Insignia getInsigniaByName (String nameInsignia);
    void addPlaneToUser (String username, String planeModel); //Adds a plane to a user.
    void addInsigniaToUser (String username, Insignia insignia);
    List<Plane> getListPlanesUser (String username);
    List<Insignia> getListInsigniasUser (String username);



}
