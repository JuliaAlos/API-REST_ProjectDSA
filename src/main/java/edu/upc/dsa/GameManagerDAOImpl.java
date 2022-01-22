package edu.upc.dsa;

import edu.upc.dsa.models.GameResults;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

public class GameManagerDAOImpl implements GameManager{

    private static GameManagerDAOImpl manager;
    private SessionImpl session;
    private InsigniasManagerDAOImpl insignias;
    final static Logger logger = Logger.getLogger(GameManagerDAOImpl.class);



    private GameManagerDAOImpl(){
        session  = SessionImpl.getInstance();
        insignias = InsigniasManagerDAOImpl.getInstance();
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
    public User updateUser(User user, String oldUsername) {
        logger.info("Update user " +oldUsername+" -> "+ user);
        User oldUser = (User) session.getByUsername(User.class, oldUsername);
        user.setId(oldUser.getId());
        user.setPlayerId(oldUser.getPlayerId());

        session.updateNewPassword(user);

        logger.info("User updated");
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

    @Override
    public List<User> sortByDistance() {

        logger.info("Sort users by distance");
        List<User> userList = new LinkedList<>();

        session.findAll(User.class).forEach(user -> {
            userList.add((User) user);
        });

        userList.sort((s1, s2) -> s2.getPlayer().getMaxDistance().compareTo(s1.getPlayer().getMaxDistance()));
        return userList;
    }

    @Override
    public List<User> sortByTime() {
        logger.info("Sort users by time");
        List<User> userList = new LinkedList<>();

        session.findAll(User.class).forEach(user -> {
            userList.add((User) user);
        });

        userList.sort((s1, s2) -> s2.getPlayer().getTimeOfFlight().compareTo(s1.getPlayer().getTimeOfFlight()));
        return userList;
    }

    @Override
    public List<User> sortByMoney() {
        logger.info("Sort users by money");
        List<User> userList = new LinkedList<>();

        session.findAll(User.class).forEach(user -> {
            userList.add((User) user);
        });

        userList.sort((s1, s2) -> s2.getPlayer().getBitcoins().compareTo(s1.getPlayer().getBitcoins()));
        return userList;
    }

    @Override
    public List<User> sortByRol() {
        logger.info("Sort users by rol");
        List<User> userList = new LinkedList<>();

        session.findAll(User.class).forEach(user -> {
            userList.add((User) user);
        });

        Collections.sort(userList);
        return userList;
    }

    @Override
    public void syncGameResults(GameResults gameResults, String userName) {
        logger.info("Sync new game results");
        User user = (User) session.getByUsername(User.class,userName);
        Player player = user.getPlayer();
        player.setBitcoins(player.getBitcoins() + gameResults.getCollectedBitcoins());
        player.setTimeOfFlight(player.getTimeOfFlight() + gameResults.getTimeOfFlight()/3600f);
        if(player.getMaxDistance() < gameResults.getDistance()) player.setMaxDistance(gameResults.getDistance());
        if(gameResults.getDistance() > 50 && gameResults.getDistance() < 1000) {
            if (!insignias.playerHasInsignia(userName, "Centimetre")) {
                insignias.addInsigniaToPlayer("Centimetre", user.getUserName());
            }
        }
        if(gameResults.getDistance() > 1000) {
            if (!insignias.playerHasInsignia(userName, "World")) {
                insignias.addInsigniaToPlayer("World", user.getUserName());
            }
        }
        double fiveMin = 5/3600;
        if(player.getTimeOfFlight() > fiveMin && player.getTimeOfFlight() < 1) {
            if (!insignias.playerHasInsignia(userName, "5min")) {
                insignias.addInsigniaToPlayer("5min", user.getUserName());
            }
        }
        if(player.getTimeOfFlight() > 1 && player.getTimeOfFlight() < 24) {
            if (!insignias.playerHasInsignia(userName, "1hour")) {
                insignias.addInsigniaToPlayer("1hour", user.getUserName());
            }
        }
        if(player.getTimeOfFlight() >  24) {
            if (!insignias.playerHasInsignia(userName, "24/7playing")) {
                insignias.addInsigniaToPlayer("24/7playing", user.getUserName());
            }
        }
        if(player.getBitcoins() > 500 && player.getBitcoins() < 2000){
            if (!insignias.playerHasInsignia(userName, "Wealth")) {
                insignias.addInsigniaToPlayer("Wealth", user.getUserName());
            }
        }
        if(player.getBitcoins() > 2000){
            if (!insignias.playerHasInsignia(userName, "Diamond")) {
                insignias.addInsigniaToPlayer("Diamond", user.getUserName());
            }
        }



        session.update(player);

    }

    @Override
    public void setMoney(Integer bitcoins, String userName) {
        logger.info("Set money");
        User user = (User) session.getByUsername(User.class,userName);
        Player player = user.getPlayer();
        player.setBitcoins(bitcoins);

        session.update(player);
    }
}
