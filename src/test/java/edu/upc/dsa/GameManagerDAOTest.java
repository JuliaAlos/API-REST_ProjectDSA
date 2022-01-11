package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.transferObjects.RegisterUserTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class GameManagerDAOTest {
    @Test
    public void testExists(){
        GameManagerDAOImpl gameManagerDAO = GameManagerDAOImpl.getInstance();

        Boolean b = gameManagerDAO.existUser("Pau");
        Assert.assertTrue(b);

        Assert.assertFalse(gameManagerDAO.existUser("blabla"));
    }

    @Test
    public void testAddUser(){
        GameManagerDAOImpl gameManagerDAO = GameManagerDAOImpl.getInstance();

        Random r = new Random();
        Integer rand = r.nextInt(100-0)+0;

        RegisterUserTO registerUserTO = new RegisterUserTO("P"+ rand.toString(), "Pau", "P fullname", "pau@pau.pau","");
        User u = gameManagerDAO.addUser(registerUserTO.toUser());

    }

    @Test
    public void testLogin(){
        GameManagerDAOImpl gameManagerDAO = GameManagerDAOImpl.getInstance();

        gameManagerDAO.loginUser("Pau", "Pau");
        User u = gameManagerDAO.getUser("Pau");
        Assert.assertTrue(u.getStatus());
    }

    @Test
    public void testLogout(){
        GameManagerDAOImpl gameManagerDAO = GameManagerDAOImpl.getInstance();

        gameManagerDAO.logoutUser("Pau");
        User u = gameManagerDAO.getUser("Pau");
        Assert.assertFalse(u.getStatus());

    }

    @Test
    public void testDeleteUser(){
        GameManagerDAOImpl gameManagerDAO = GameManagerDAOImpl.getInstance();
        RegisterUserTO registerUserTO = new RegisterUserTO("toBeRemoved", "Pau", "P fullname", "pau@pau.pau","");
        User u = gameManagerDAO.addUser(registerUserTO.toUser());
        gameManagerDAO.deleteUser("toBeRemoved");

    }

    @Test
    public void testGetAll(){
        GameManagerDAOImpl gameManagerDAO = GameManagerDAOImpl.getInstance();
        List<User> l =gameManagerDAO.getAll();

    }
}
