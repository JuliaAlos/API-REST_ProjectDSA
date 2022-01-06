package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PlanesManagerDAOImpl implements PlanesManager{
    private static PlanesManagerDAOImpl manager;
    private SessionImpl session;
    final static Logger logger = Logger.getLogger(PlanesManagerDAOImpl.class);

    public PlanesManagerDAOImpl() {
        session = SessionImpl.getInstance();
    }

    public static PlanesManagerDAOImpl getInstance() {
        if(manager==null){
            logger.info("New instance edu.upc.dsa.InsigniasManagerDAOImpl");
            manager = new PlanesManagerDAOImpl();
        }
        return manager;
    }

    @Override
    public PlaneModel getPlaneByModel(String model) {
        List<Object> l =session.findAll(PlaneModel.class, new HashMap<String, Object>(){{put("model", model);}});
        return (PlaneModel) l.get(0);
    }

    @Override
    public List<PlaneModel> getAll() {
        logger.info("Get all planes");
        List<PlaneModel> l = new LinkedList<>();
        session.findAll(PlaneModel.class).forEach(el -> l.add((PlaneModel) el));
        return l;
    }

    @Override
    public List<Plane> getAllFromPlayer(String playerName) {
        logger.info("Get all planes from " + playerName);
        Player p = (Player) session.getByUsername(Player.class, playerName);
        return p.getPlaneList();
    }

    @Override
    public void addPlaneToPlayer(String planeModel, String playerName) {
        logger.info("Add Plane "+ planeModel+" to " + playerName);

        Player p = (Player) session.getByUsername(Player.class, playerName);
        Plane plane = new Plane(planeModel, p.getId());

        session.save(plane);

    }

    @Override
    public Boolean existPlaneModel(String planeModel) {
        List<Object> l =session.findAll(PlaneModel.class, new HashMap<String, Object>(){{put("model", planeModel);}});
        if(l!=null && l.size()>0){
            logger.info(planeModel+" found");
            return true;
        }
        logger.info(planeModel+" not found");
        return false;
    }

    @Override
    public Boolean existPlayer(String playerName) {
        if(session.getByUsername(Player.class, playerName)!=null){
            logger.info(playerName+" found");
            return true;
        }
        logger.info(playerName+" not found");
        return false;
    }

    @Override
    public void addUpgradeToPlayer (String modificationCode, String playerName, String planeModel){
        logger.info("Add upgrade with code "+ modificationCode+" to " + "plane: " + planeModel + "from player: " + playerName);

        Player p = (Player) session.getByUsername(Player.class, playerName);

        Upgrade upgrade = new Upgrade(modificationCode, playerName, planeModel);

        session.save(upgrade);
    }

    @Override
    public List<Upgrade> getAllUpgradesFromPlayer(String playerName) {
        logger.info("Get all upgrades from " + playerName);
        Player p = (Player) session.getByUsername(Player.class, playerName);
        return p.getUpgradesList();
    }
}
