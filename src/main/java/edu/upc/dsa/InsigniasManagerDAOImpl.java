package edu.upc.dsa;

import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.InsigniaModel;
import edu.upc.dsa.models.Player;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InsigniasManagerDAOImpl implements InsigniasManager{
    private static InsigniasManagerDAOImpl manager;
    private SessionImpl session;
    final static Logger logger = Logger.getLogger(InsigniasManagerDAOImpl.class);

    public InsigniasManagerDAOImpl() {
        session = SessionImpl.getInstance();
    }

    public static InsigniasManagerDAOImpl getInstance() {
        if(manager==null){
            logger.info("New instance edu.upc.dsa.InsigniasManagerDAOImpl");
            manager = new InsigniasManagerDAOImpl();
        }
        return manager;
    }

    @Override
    public List<InsigniaModel> getAll() {
        logger.info("Get all insignias");
        List<InsigniaModel> l = new LinkedList<>();
        session.findAll(InsigniaModel.class).forEach(el -> l.add((InsigniaModel) el));
        return l;
    }

    @Override
    public List<Insignia> getAllFromPlayer(String playerName) {
        logger.info("Get all insignias from " + playerName);
        Player p = (Player) session.getByUsername(Player.class, playerName);
        return p.getInsigniaList();
    }

    @Override
    public void addInsigniaToPlayer(String insigniaName, String playerName) {
        logger.info("Add insignia "+ insigniaName +" to " + playerName);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Player p = (Player) session.getByUsername(Player.class, playerName);

        Insignia insignia = new Insignia(dtf.format(now).toString(), insigniaName, p.getId());

        session.save(insignia);


    }

    public Boolean playerHasInsignia(String playerName, String insigniaName){
        List<Insignia> p = getAllFromPlayer(playerName);
        int index = 0;
        for (int i=0; i<p.size(); i++){
            if(p.get(i).getName().equals(insigniaName)){
                index = 1;
            }
        }
        if(index == 0){ return false;}
        else{ return true;}
    }

    public Boolean existPlayer(String playerName){
        if(session.getByUsername(Player.class, playerName)!=null){
            logger.info(playerName+" found");
            return true;
        }
        logger.info(playerName+" not found");
        return false;
    }

    public Boolean existInsigniaModel(String insigniaModelName){
        List<Object> l =session.findAll(InsigniaModel.class, new HashMap<String, Object>(){{put("name", insigniaModelName);}});
        if(l!=null && l.size()>0){
            logger.info(insigniaModelName+" found");
            return true;
        }
        logger.info(insigniaModelName+" not found");
        return false;
    }


}
