package edu.upc.dsa;

import edu.upc.dsa.models.ForumEntry;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class ForumManagerDAOImpl implements ForumManager{

    private static ForumManagerDAOImpl manager;
    private SessionImpl session;
    final static Logger logger = Logger.getLogger(GameManagerDAOImpl.class);

    private ForumManagerDAOImpl(){
        session  = SessionImpl.getInstance();
    }

    public static ForumManagerDAOImpl getInstance(){
        if(manager==null){
            logger.info("New instance edu.upc.dsa.GameManagerDAOImpl");
            manager = new ForumManagerDAOImpl();
        }
        return manager;
    }

    public static void delete(){
        manager = null;
        logger.info("Instance ForumManagerDAOImpl deleted");
    }

    @Override
    public void addEntry(String playerName, String Message) {
        logger.info("New entry by user " + playerName + " added");
        List<Object> allEntries = session.findAll(ForumEntry.class);
        Integer current_seq = allEntries.size();
        ForumEntry entry = new ForumEntry(playerName, Message, current_seq);
        session.save(entry);
        logger.info("Entry added successfully.");
    }

    @Override
    public List<ForumEntry> getAllEntries(){
        logger.info("Get all forum entries in the system.");
        List<ForumEntry> listEntries = new LinkedList<>();
        session.findAll(ForumEntry.class).forEach(entry -> {
            listEntries.add((ForumEntry) entry);
        });
        return listEntries;
    }


}
