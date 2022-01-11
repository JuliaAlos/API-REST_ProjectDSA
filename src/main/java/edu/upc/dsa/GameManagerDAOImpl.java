package edu.upc.dsa;

import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameManagerDAOImpl implements GameManager{

    private static GameManagerDAOImpl manager;
    private SessionImpl session;
    final static Logger logger = Logger.getLogger(GameManagerDAOImpl.class);



    private GameManagerDAOImpl(){
        session  = SessionImpl.getInstance();
    }


    @Override
    public User addUser(String userName, String password, String fullName, String email, String image_url) {
        return null;
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
    public User addUser(User user) {
        logger.info("New user " +user.getUserName()+" -> "+ user);
        Player player = new Player(user.getUserName()); // by default player name = username
        String playerId = session.save(player);
        user.setPlayerId(playerId);
        session.saveNewUser(user);
        logger.info("New user added");
        return user;
    }

    @Override
    public User loginUser(String userName,String password){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userName", userName);
        hashMap.put("password", password);
        List<Object> l = session.findAll(User.class, hashMap);
        if(l.size()==1){
            logger.info("Correct login "+userName);

            User u = (User) l.get(0);
            u.setStatus(true);
            session.update(u);
            return u;
        }else if(l.size()>1){
            logger.warn("Duplicated user");
            return null;
        }

        logger.warn("Incorrect user name or password");
        return null;
    }

    @Override
    public void deleteUser(String userName) {
        if(!this.existUser(userName)){
            logger.info(userName+" does not exist");
            return;
        }
        User u = (User) session.getByUsername(User.class, userName);
        session.delete(u);
        session.delete(session.get(Player.class, u.getPlayerId()));
        logger.info(userName+" deleted ");


    }

    @Override
    public void logoutUser(String userName) {

        if (!existUser(userName)) {
            logger.warn("Not found " + userName);
        }
        else {
            User u = (User) session.getByUsername(User.class, userName);
            u.setStatus(false);
            session.update(u);
            logger.info(userName+" logout ");
        }
    }

    @Override
    public User getUser(String userName){
        if(!existUser(userName)){
            logger.info(userName + "Not found");
            return null;
        }
        logger.info("Get " + userName);
        return (User) session.getByUsername(User.class,userName);
    }

    @Override
    public List<User> getAll(){
        logger.info("Sort all users alphabetically by name");
        List<User> userList = new LinkedList<>();
        session.findAll(User.class).forEach(user -> {
            userList.add((User) user);
        });

        userList.sort((s1, s2) -> s1.getUserName().compareTo(s2.getUserName()));

        return userList;
    }

    @Override
    public List<User> getAllActive(){
        logger.info("Sort all active users alphabetically by name");
        List<User> userList = new LinkedList<>();
        HashMap<String, Object> hashMap =new HashMap<>();
        hashMap.put("status", true);
        session.findAll(User.class,hashMap).forEach(user -> {
            userList.add((User) user);
        });

        userList.sort((s1, s2) -> s1.getUserName().compareTo(s2.getUserName()));


        return userList;

    }

    //implemented and tested
    @Override
    public Boolean existUser(String userName){
        if(session.getByUsername(User.class, userName) != null){
            logger.info(userName+" found");
            return true;
        }
        logger.info(userName+" not found");
        return false;
    }


}
