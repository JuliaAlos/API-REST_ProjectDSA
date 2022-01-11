package edu.upc.dsa;

import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.Plane;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class GameManagerImpl implements GameManager{

    private static GameManagerImpl manager;
    private HashMap<String, User> gameUsers;
    private HashMap<String, Plane> gamePlanes; //List of all available planes in the game, with their default configuration.
    private HashMap<String, Insignia> gameInsignias;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);



    private GameManagerImpl(){

        this.gameUsers = new HashMap<>();
        this.gamePlanes = new HashMap<>();
        this.gameInsignias = new HashMap<>();
    }

    public static GameManagerImpl getInstance(){
        if(manager==null){
            logger.info("New instance edu.upc.dsa.GameManagerImpl");
            manager = new GameManagerImpl();
        }
        return manager;
    }

    @Override
    public User updateUser(User user, String oldUsername) {
        return null;
    }

    public static void delete(){
        manager = null;
        logger.info("Instance GameManagerImpl deleted");
    }

    public void clear(){
        gameUsers.clear();
        logger.info("Instance GameManagerImpl clear");
    }



    @Override
    public User addUser(User user) {
        logger.info("New user " +user.getUserName()+" -> "+ user);
        if(gameUsers.containsKey(user.getUserName())){
            logger.info("Username is already used");
            return null;
        }
        this.gameUsers.put(user.getUserName(), user);
        logger.info("New user added");
        return user;
    }

    @Override
    public User loginUser(String userName,String password){
        if(gameUsers.containsKey(userName)) {
            if(gameUsers.get(userName).getPassword().equals(password)){
                logger.info("Correct login "+userName);
                gameUsers.get(userName).setStatus(true);
                return gameUsers.get(userName);
            }
            logger.warn("Incorrect password");
        }
        logger.warn("Incorrect user name");
        return null;
    }

    @Override
    public void deleteUser(String userName) {

        if (!gameUsers.containsKey(userName)) {
            logger.warn("Not found " + userName);
        }
        else {
            logger.info(userName+" deleted ");
            this.gameUsers.remove(userName);
        }

    }

    @Override
    public void logoutUser(String userName) {

        if (!gameUsers.containsKey(userName)) {
            logger.warn("Not found " + userName);
        }
        else {
            logger.info(userName+" logout ");
            this.gameUsers.get(userName).setStatus(false);
        }

    }

    @Override
    public User getUser(String userName){
        existUser(userName);
        return gameUsers.get(userName);
    }

    @Override
    public List<User> getAll(){
        logger.info("Sort all users alphabetically by name");
        List<User> userList = Arrays.asList(gameUsers.values().stream().sorted(
                (s1,s2)->s1.getUserName().compareToIgnoreCase(s2.getUserName())).toArray(User[]::new));
        return userList;
    }

    @Override
    public List<User> getAllActive(){
        logger.info("Sort all active users alphabetically by name");
        List<User> userList = Arrays.asList(gameUsers.values().stream().filter(s->s.getStatus().equals(true)).sorted(
                (s1,s2)->s1.getUserName().compareToIgnoreCase(s2.getUserName())).toArray(User[]::new));
        return userList;

    }

    @Override
    public Boolean existUser(String userName){
        if(gameUsers.containsKey(userName)){
            logger.info(userName+" found");
            return true;
        }
        logger.info(userName+" not found");
        return false;
    }
/*
    @Override
    public List<Plane> getAllPlanes() {
        logger.info("Get all planes in the system.");
        return new ArrayList<Plane>(this.gamePlanes.values());
    }

    @Override
    public List<Insignia> getAllInsignias() {
        logger.info("Get all insignias in the system.");
        return new ArrayList<Insignia>(this.gameInsignias.values());
    }

    @Override
    public void addPlane(Plane plane) {
        logger.info("Adding a new plane to the system.");
        this.gamePlanes.put(plane.getModel(), plane);
        logger.info("Plane "+ plane.getModel() + " added successfully to the system.");
    }

    @Override
    public void addInsignia(Insignia insignia) {
        logger.info("Adding a new insignia to the system.");
        this.gameInsignias.put(insignia.getName(), insignia);
        logger.info("Insignia " + insignia.getName() + "addes successfully to the system.");
    }

    @Override
    public Plane getPlaneByModel(String model) {
        logger.info("Get plane which model is: ." + model);
        return this.gamePlanes.get(model);
    }

    @Override
    public Insignia getInsigniaByName(String nameInsignia) {
        return this.gameInsignias.get(nameInsignia);
    }

    @Override
    public void addPlaneToUser (String username, String planeModel) {
        logger.info("Add a new plane to the user.");
        User user = this.gameUsers.get(username);
        Plane plane = this.gamePlanes.get(planeModel);
        user.getPlayer().addPlane(plane);
        logger.info("Plane " + planeModel + " added successfully to player " + username);
    }

    @Override
    public void addInsigniaToUser (String username, String insigniaName) {
        logger.info("Add a new insignia to the user.");
        User user = this.gameUsers.get(username);
        Insignia insignia = this.gameInsignias.get(insigniaName);
        user.getPlayer().addInsignia(insignia);
        logger.info("Insignia " + insigniaName+ " added successfully to player " + username);
    }

    @Override
    public List<Plane> getListPlanesUser (String username){
        logger.info("Get list of planes of the user " + username);
        return this.gameUsers.get(username).getPlayer().getListPlanes();
    }

    @Override
    public List<Insignia> getListInsigniasUser(String username) {
        logger.info("Get list of insignias of the user " + username);
        return this.gameUsers.get(username).getPlayer().getListInsignias();
    }

<<<<<<< HEAD


    @Override
    public Boolean existPlane (String planeModel){
        if(this.gamePlanes.containsKey(planeModel)){
            logger.info(planeModel+" found");
            return true;
        }
        logger.info(planeModel+" not found");
        return false;
    }

    @Override
    public Boolean existInsignia (String insigniaName){
        if(this.gamePlanes.containsKey(insigniaName)){
            logger.info(insigniaName+" found");
            return true;
        }
        logger.info(insigniaName+" not found");
        return false;
    }

 */


}
