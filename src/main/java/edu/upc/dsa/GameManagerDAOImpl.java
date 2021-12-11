package edu.upc.dsa;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameManagerDAOImpl implements GameManager{

    private static GameManagerDAOImpl manager;
    private SessionImpl session = SessionImpl.getInstance();
   // private HashMap<String, User> gameUsers;
    final static Logger logger = Logger.getLogger(GameManagerDAOImpl.class);



    private GameManagerDAOImpl(){
        //this.gameUsers = new HashMap<>();
    }

    public static GameManagerDAOImpl getInstance(){
        if(manager==null){
            logger.info("New instance edu.upc.dsa.GameManagerDAOImpl");
            manager = new GameManagerDAOImpl();
        }
        return manager;
    }

    public static void delete(){
        manager = null;
        logger.info("Instance GameManagerDAOImpl deleted");
    }

    @Override
    public User addUser(String userName, String password, String fullName, String email) {
        User u = new User(userName,password,fullName,email);
        session.save(u);
        return u;
    }

    @Override
    public User addUser(User user) {
        logger.info("New user " +user.getUserName()+" -> "+ user);
        User u = (User) session.get(User.class, user.getId());
        if(u == null){
            logger.info("Username is already used");
            return null;
        }
        session.save(u);
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


}
